package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.dto.OrderCreationDto;
import ro.msg.learning.shop.dto.RequiredProductDto;
import ro.msg.learning.shop.exception.NegativeQuantityException;
import ro.msg.learning.shop.exception.StockNotFoundException;
import ro.msg.learning.shop.model.Address;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.security.CustomUserDetails;
import ro.msg.learning.shop.strategy.LocationStrategy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class OrderCreationService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final StockRepository stockRepository;
    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;
    private final LocationStrategy strategy;


    @Transactional
    public Order createOrder(OrderCreationDto orderCreationDto) {

        List<RequiredProductDto> requiredProductDtoList = strategy.findRequiredProductsByLocation(orderCreationDto);

        updateStocks(requiredProductDtoList);

        //Create & Save Order
        return createOrder(orderCreationDto, requiredProductDtoList);

    }

    private void updateStocks(List<RequiredProductDto> requiredProductDtos) {

        for (RequiredProductDto requiredProductDto : requiredProductDtos) {
            Stock stock = stockRepository.findByProductIdAndLocationId(requiredProductDto.getProduct().getId(), requiredProductDto.getLocation().getId());

            if (stock == null) {
                throw new StockNotFoundException(requiredProductDto.toString());
            }

            Integer newQuantity = stock.getQuantity() - requiredProductDto.getQuantity();
            if (newQuantity < 0) {
                throw new NegativeQuantityException(stock.toString(), requiredProductDto.getQuantity());
            }
            stock.setQuantity(newQuantity);

            stockRepository.save(stock);
        }
    }

    private Order createOrder(OrderCreationDto orderCreationDto, List<RequiredProductDto> requiredProductDtos) {

        //Create Order
        Order order = new Order();
        order.setCreateTime(orderCreationDto.getLocalDateTime());

        Address address;
        List<Address> addresses = addressRepository.findByCountryAndCityAndCountyAndStreet(orderCreationDto.getDeliveryAddress().getCountry(),
                orderCreationDto.getDeliveryAddress().getCity(), orderCreationDto.getDeliveryAddress().getCounty(), orderCreationDto.getDeliveryAddress().getStreet());

        if (addresses.isEmpty()) {
            address = addressRepository.save(orderCreationDto.getDeliveryAddress());
        } else {
            address = addresses.get(0);
        }
        order.setAddress(address);
        order.setLocation(requiredProductDtos.get(0).getLocation());

        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        order.setCustomer(customerRepository.findByUserId(customUserDetails.getId()));

        //Save Order
        Order savedOrder = orderRepository.save(order);

        //Create Order Details
        return createOrderDetails(savedOrder, requiredProductDtos);

    }

    private Order createOrderDetails(Order order, List<RequiredProductDto> requiredProductDtos) {

        Set<OrderDetail> orderDetails = new HashSet<>();

        for (RequiredProductDto requiredProductDto : requiredProductDtos) {
            OrderDetail savedOrderDetail = orderDetailRepository.save(new OrderDetail(order, requiredProductDto.getProduct(), requiredProductDto.getQuantity()));
            orderDetails.add(savedOrderDetail);
        }

        order.setOrderDetails(orderDetails);
        return order;
    }
}
