package sleepdeveloper.springbootexceptionexample.domain.exception;

public class BaseBusinessException extends RuntimeException{

    private ErrorCode errorCode;

    public BaseBusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
