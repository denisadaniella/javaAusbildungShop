package ro.msg.learning.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException(String s) {
        super("Exception thrown by Distance Matrix API. Destination address not found: " + s);
    }

    public AddressNotFoundException() {
        super("Exception thrown by Distance Matrix API. Addresses not found.");
    }
}
