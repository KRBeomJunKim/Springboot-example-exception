package sleepdeveloper.springbootexceptionexample.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BaseBusinessException.class)
    public ResponseEntity<BasicErrorResponse> handleBaseBusinessException(BaseBusinessException e) {
        BasicErrorResponse errorResponse = new BasicErrorResponse(e.getErrorCode());
        return ResponseEntity.status(HttpStatus.valueOf(e.getErrorCode().getStatus())).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleServerError(Exception e) {
        log.error("server internal error", e);
        return ResponseEntity.internalServerError().body("Server Internal Error");
    }
}
