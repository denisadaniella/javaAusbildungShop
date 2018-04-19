package ro.msg.learning.shop.mapper;

import lombok.NoArgsConstructor;
import org.apache.olingo.odata2.core.ep.feed.ODataDeltaFeedImpl;
import ro.msg.learning.shop.dto.OrderCreationDto;
import ro.msg.learning.shop.dto.ProductOrderDto;
import ro.msg.learning.shop.exception.AddressNotConsistentException;
import ro.msg.learning.shop.model.Address;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
public class OrderCreationMapper {

    public OrderCreationDto toOrderCreationDto(Map<String, Object> data) {

        OrderCreationDto orderCreationDto = new OrderCreationDto();
        Address address = new Address();

        @SuppressWarnings("unchecked")
        Map<String, Object> deliveryAddress = (Map<String, Object>) data.get("deliveryAddress");
        if (!deliveryAddress.isEmpty()) {
            try {
                address.setCountry((String) deliveryAddress.get("country"));
                address.setCounty((String) deliveryAddress.get("county"));
                address.setCity((String) deliveryAddress.get("city"));
                address.setStreet((String) deliveryAddress.get("street"));
            } catch (NullPointerException exc) {
                throw new AddressNotConsistentException();
            }
        }
        orderCreationDto.setDeliveryAddress(address);

        GregorianCalendar gc = (GregorianCalendar) data.get("created");
        if (gc != null) {
            LocalDateTime localDateTime = LocalDateTime.ofInstant(gc.getTime().toInstant(), ZoneId.systemDefault());
            orderCreationDto.setLocalDateTime(localDateTime);
        } else {
            orderCreationDto.setLocalDateTime(LocalDateTime.now());
        }

        List<ProductOrderDto> productOrderDtoList = new ArrayList<>();
        ODataDeltaFeedImpl products = (ODataDeltaFeedImpl) data.get("products");
        products.getEntries().forEach(p -> {
            Integer productId = (Integer) p.getProperties().get("productId");
            Integer quantity = (Integer) p.getProperties().get("quantity");
            ProductOrderDto productOrderDto = new ProductOrderDto(productId, quantity);
            productOrderDtoList.add(productOrderDto);
        });
        orderCreationDto.setProducts((ArrayList<ProductOrderDto>) productOrderDtoList);

        return orderCreationDto;
    }

}
