package ro.msg.learning.shop.exception;

public class NegativeQuantityException extends RuntimeException {
    public NegativeQuantityException(Integer quantity, Integer productID) {
        super("Negative Quantity " + quantity + " for product id " + productID);
    }

    public NegativeQuantityException(String stock, Integer quantity) {
        super(stock + " did not have the required quantity " + quantity);
    }

}
