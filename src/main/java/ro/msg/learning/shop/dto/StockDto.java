package ro.msg.learning.shop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"productId", "locationId", "quantity"})
public class StockDto {

    @JsonIgnore
    private Integer id;

    private Integer productId;
    private Integer locationId;
    private Integer quantity;

}
