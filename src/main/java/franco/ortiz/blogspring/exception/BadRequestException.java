package franco.ortiz.blogspring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends IllegalArgumentException{
    @Serial
    private static final long serialVersionUID = 1L;
    public BadRequestException(String message) {
        super(message);
    }
}