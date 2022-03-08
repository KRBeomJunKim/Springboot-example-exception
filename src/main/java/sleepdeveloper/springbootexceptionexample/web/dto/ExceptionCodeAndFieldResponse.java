package sleepdeveloper.springbootexceptionexample.web.dto;

import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import sleepdeveloper.springbootexceptionexample.domain.exception.ErrorCode;

import java.util.List;
import java.util.Locale;

public class ExceptionCodeAndFieldResponse extends ExceptionCodeResponse {

    private List<ExceptionFieldResponse> fieldErrors;

    public ExceptionCodeAndFieldResponse(BindingResult bindingResult, MessageSource messageSource, Locale locale) {
        super(ErrorCode.COMMON_VALIDATION_FAIL);
        this.fieldErrors = ExceptionFieldResponse.of(bindingResult, messageSource, locale);
    }

    public List<ExceptionFieldResponse> getFieldErrors() {
        return fieldErrors;
    }
}
