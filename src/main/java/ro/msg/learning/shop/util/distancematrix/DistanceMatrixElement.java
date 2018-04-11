package ro.msg.learning.shop.util.distancematrix;

import lombok.Data;

@Data
public class DistanceMatrixElement {

    private DistanceMatrixElementStatus status;

    private Duration duration;

    private Distance distance;

}
