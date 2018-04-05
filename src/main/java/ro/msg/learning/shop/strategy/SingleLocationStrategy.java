package ro.msg.learning.shop.strategy;

import lombok.AllArgsConstructor;
import ro.msg.learning.shop.dto.OrderCreationDto;
import ro.msg.learning.shop.dto.ProductOrderDto;
import ro.msg.learning.shop.dto.RequiredProductDto;
import ro.msg.learning.shop.exception.LocationNotFoundException;
import ro.msg.learning.shop.exception.NegativeQuantityException;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class SingleLocationStrategy implements LocationStrategy {

    private final StockRepository stockRepository;
    private final ProductRepository productRepository;

    @Override
    public List<RequiredProductDto> findRequiredProductsByLocation(OrderCreationDto orderCreationDto) {

        List<Location> commonLocations = null;

        //For each product in the orderCreationDto, get the stocks and the corresponding locations
        for (ProductOrderDto po : orderCreationDto.getProducts()) {

            if (po.getQuantity() < 0) {
                throw new NegativeQuantityException(po.getQuantity(), po.getProductId());
            }

            List<Location> locations = stockRepository.findLocationByProductIdAndQuantityGreaterThanEqual(po.getProductId(), po.getQuantity());

            //Find the common locations for all the products
            if (commonLocations == null) commonLocations = locations;
            commonLocations.retainAll(locations);

            if (commonLocations.isEmpty()) {
                //No specific location for the products, so far => exception
                throw new LocationNotFoundException();
            }

        }

        //Get first Location found
        if (commonLocations != null) {
            Location singleLocation = commonLocations.get(0);
            List<RequiredProductDto> requiredProductDtos = new ArrayList<>();
            orderCreationDto.getProducts().forEach(p -> requiredProductDtos.add(new RequiredProductDto(singleLocation, productRepository.findOne(p.getProductId()), p.getQuantity())));
            return requiredProductDtos;
        } else {
            throw new LocationNotFoundException();
        }

    }
}
