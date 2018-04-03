package ro.msg.learning.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StockConsistencyException extends RuntimeException {

    public StockConsistencyException(String s, Throwable throwable) {
        super("Stock " + s + " is not consistent. ", throwable);
    }
}
