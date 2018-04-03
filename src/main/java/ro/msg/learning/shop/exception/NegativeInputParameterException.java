package ro.msg.learning.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NegativeInputParameterException extends RuntimeException {

    public NegativeInputParameterException(Integer parameter) {

        super("Negative Input Parameter: " + parameter);

    }
}
