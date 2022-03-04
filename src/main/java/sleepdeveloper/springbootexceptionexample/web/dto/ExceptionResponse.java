package sleepdeveloper.springbootexceptionexample.web.dto;

import sleepdeveloper.springbootexceptionexample.domain.exception.ErrorCode;

public class ExceptionResponse {

    private int status;
    private String statusCode;
    private String message;


    public ExceptionResponse(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.statusCode = errorCode.getStatusCode();
        this.message = errorCode.getMessage();
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
}
