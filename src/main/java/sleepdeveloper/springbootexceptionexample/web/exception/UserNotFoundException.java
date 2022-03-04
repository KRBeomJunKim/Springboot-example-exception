package sleepdeveloper.springbootexceptionexample.web.exception;

import sleepdeveloper.springbootexceptionexample.domain.exception.BaseBusinessException;
import sleepdeveloper.springbootexceptionexample.domain.exception.ErrorCode;

public class UserNotFoundException extends BaseBusinessException {
    public UserNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
