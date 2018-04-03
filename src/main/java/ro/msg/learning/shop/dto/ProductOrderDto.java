package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductOrderDto {

    private Integer productId;
    private Integer quantity;

}
