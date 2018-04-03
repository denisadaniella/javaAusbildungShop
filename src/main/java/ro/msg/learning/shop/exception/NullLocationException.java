package ro.msg.learning.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NullLocationException extends RuntimeException {

    public NullLocationException(Integer i) {
        super("Location with id " + i + " does not exist");
    }
}
