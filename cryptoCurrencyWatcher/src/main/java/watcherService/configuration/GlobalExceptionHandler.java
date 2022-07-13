package watcherService.configuration;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import watcherService.exception.CryptoCurrencyNotFoundException;
import watcherService.exception.UserNoticeAlreadyExistException;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CryptoCurrencyNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerCryptoCurrencyNotFoundException(CryptoCurrencyNotFoundException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(UserNoticeAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handlerUserNoticeAlreadyExistException(UserNoticeAlreadyExistException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(ex.getMessage()));
    }

    @Value
    public static class ErrorResponse {
        String message;
    }
}
