package sleepdeveloper.springbootexceptionexample.common.exception;

import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

public class BasicErrorResponse {

    private int status;
    private String statusCode;
    private String message;
    private List<BasicFieldError> fieldErrors;

    public BasicErrorResponse(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.statusCode = errorCode.getStatusCode();
        this.message = errorCode.getMessage();
        this.fieldErrors = new ArrayList<>();
    }

    public BasicErrorResponse(ErrorCode errorCode, BindingResult bindingResult) {
        this.status = errorCode.getStatus();
        this.statusCode = errorCode.getStatusCode();
        this.message = errorCode.getMessage();
        this.fieldErrors = BasicFieldError.of(bindingResult);
    }

    public int getStatus() {
        return status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public List<BasicFieldError> getFieldErrors() {
        return fieldErrors;
    }
}
