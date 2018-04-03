package ro.msg.learning.shop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.model.Address;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@NoArgsConstructor
public class OrderCreationDto {

    LocalDateTime localDateTime;
    Address deliveryAddress;
    ArrayList<ProductOrderDto> products;

}
