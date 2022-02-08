package sleepdeveloper.springbootexceptionexample.example;

import sleepdeveloper.springbootexceptionexample.common.exception.BaseBusinessException;
import sleepdeveloper.springbootexceptionexample.common.exception.ErrorCode;

public class UserNotFoundException extends BaseBusinessException {
    public UserNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
