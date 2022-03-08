package sleepdeveloper.springbootexceptionexample.web.exception;

import sleepdeveloper.springbootexceptionexample.domain.exception.BaseBusinessException;
import sleepdeveloper.springbootexceptionexample.domain.exception.ErrorCode;

public class TestException extends BaseBusinessException {
    public TestException(ErrorCode errorCode) {
        super(errorCode);
    }
}
