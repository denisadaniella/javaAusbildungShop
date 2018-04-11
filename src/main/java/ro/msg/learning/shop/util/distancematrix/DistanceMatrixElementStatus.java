package ro.msg.learning.shop.util.distancematrix;

public enum DistanceMatrixElementStatus {
    /**
     * Indicates that the response contains a valid result.
     */
    OK,

    /**
     * Indicates that the origin and/or destination of this pairing could not be geocoded.
     */
    NOT_FOUND,

    /**
     * Indicates that no route could be found between the origin and destination.
     */
    ZERO_RESULTS
}
