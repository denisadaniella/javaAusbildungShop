package ro.msg.learning.shop.util.distancematrix;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DistanceMatrix {

    @JsonProperty("origin_addresses")
    private List<String> originAddresses;

    @JsonProperty("destination_addresses")
    private List<String> destinationAddresses;

    private List<DistanceMatrixRow> rows;

}
