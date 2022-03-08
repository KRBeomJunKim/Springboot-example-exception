package sleepdeveloper.springbootexceptionexample.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sleepdeveloper.springbootexceptionexample.domain.exception.BaseBusinessException;
import sleepdeveloper.springbootexceptionexample.domain.service.ErrorMessageSender;
import sleepdeveloper.springbootexceptionexample.domain.service.SlackService;
import sleepdeveloper.springbootexceptionexample.web.dto.ExceptionCodeResponse;
import sleepdeveloper.springbootexceptionexample.web.dto.ExceptionCodeAndFieldResponse;
import sleepdeveloper.springbootexceptionexample.web.dto.ExceptionMessageResponse;

import java.util.Arrays;
import java.util.Locale;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final MessageSource messageSource;

    private final ErrorMessageSender errorMessageSender;

    public GlobalExceptionHandler(MessageSource messageSource, ErrorMessageSender errorMessageSender) {
        this.messageSource = messageSource;
        this.errorMessageSender = errorMessageSender;
    }

    @ExceptionHandler(BaseBusinessException.class)
    public ResponseEntity<ExceptionCodeResponse> handleBaseBusinessException(BaseBusinessException e) {
        ExceptionCodeResponse errorResponse = new ExceptionCodeResponse(e.getErrorCode());
        return ResponseEntity.status(HttpStatus.valueOf(e.getErrorCode().getStatus())).body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionMessageResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) throws JsonProcessingException {
        return ResponseEntity
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ExceptionMessageResponse("Message Parsing Error, please send right value"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionCodeAndFieldResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, Locale locale) throws JsonProcessingException {
        return ResponseEntity
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        new ExceptionCodeAndFieldResponse(
                                e.getBindingResult(),
                                messageSource,
                                locale
                        )
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleServerError(Exception e) throws JsonProcessingException {

        errorMessageSender.sendErrorMessage("=========================================\n" +
                e.toString() + "\n" + Arrays.toString(Arrays.copyOfRange(e.getStackTrace(), 0, 10)));

        return ResponseEntity
                .badRequest()
                .body(objectMapper.writeValueAsString(
                                new ExceptionMessageResponse("Server Internal Error")
                        )
                );
    }
}
