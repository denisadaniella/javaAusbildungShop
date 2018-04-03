package ro.msg.learning.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class LocationNotFoundException extends RuntimeException {

    public LocationNotFoundException(int i) {
        super("Location for Id " + i + " not found");
    }

    public LocationNotFoundException() {
        super("No location found for the required Products");
    }
}
