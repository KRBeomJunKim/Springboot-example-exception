package sleepdeveloper.springbootexceptionexample.web.dto;

import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import sleepdeveloper.springbootexceptionexample.domain.exception.ErrorCode;

import java.util.List;
import java.util.Locale;

public class ExceptionFieldResponse extends ExceptionResponse {

    private List<FieldError> fieldErrors;

    public ExceptionFieldResponse(BindingResult bindingResult, MessageSource messageSource, Locale locale) {
        super(ErrorCode.COMMON_VALIDATION_FAIL);
        this.fieldErrors = FieldError.of(bindingResult, messageSource, locale);
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }
}
