package ro.msg.learning.shop.strategy;

import ro.msg.learning.shop.dto.OrderCreationDto;
import ro.msg.learning.shop.dto.RequiredProductDto;

import java.util.List;

public interface LocationStrategy {
    List<RequiredProductDto> findRequiredProductsByLocation(OrderCreationDto orderCreationDto);
}
