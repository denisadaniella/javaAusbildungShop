package ro.msg.learning.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NullProductException extends RuntimeException {

    public NullProductException(Integer i) {
        super("Product with id " + i + " does not exist");
    }
}
