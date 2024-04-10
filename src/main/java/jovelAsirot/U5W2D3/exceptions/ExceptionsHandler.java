package jovelAsirot.U5W2D3.exceptions;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsPayLoad handleBadRequest(BadRequestException ex) {
        return new ErrorsPayLoad(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404

    public ErrorsPayLoad handleNotFound(NotFoundException ex) {
        return new ErrorsPayLoad(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    public ErrorsPayLoad handleGenericErrors(Exception ex) {
        ex.printStackTrace();
        return new ErrorsPayLoad("Error server side", LocalDateTime.now());
    }
}
