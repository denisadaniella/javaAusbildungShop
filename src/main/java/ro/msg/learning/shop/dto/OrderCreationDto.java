package ro.msg.learning.shop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.model.Address;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@NoArgsConstructor
public class OrderCreationDto {

    private LocalDateTime localDateTime;
    private Address deliveryAddress;
    private ArrayList<ProductOrderDto> products;

}
