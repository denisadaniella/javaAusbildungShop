package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class LocationOrderDto {

    private Integer locationId;
    private BigDecimal sum;

}
