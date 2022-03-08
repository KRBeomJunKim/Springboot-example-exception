package sleepdeveloper.springbootexceptionexample.web.dto;

import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ExceptionFieldResponse {

    private String field;
    private String value;
    private String reason;

    public ExceptionFieldResponse(String field, String value, String reason) {
        this.field = field;
        this.value = value;
        this.reason = reason;
    }

    public static List<ExceptionFieldResponse> of(BindingResult bindingResult, MessageSource messageSource, Locale locale) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        return fieldErrors.stream()
                .map(error -> new ExceptionFieldResponse(
                        error.getField(),
                        error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                        getMessage(error, messageSource, locale)))
                .collect(Collectors.toList());
    }

    private static String getMessage(FieldError error, MessageSource messageSource, Locale locale) {
        for (String code : error.getCodes()) {
            String message = messageSource.getMessage(code, error.getArguments(), error.getDefaultMessage(), locale);
            if(!message.equals(error.getDefaultMessage())) {
                return message;
            }
        }
        return error.getDefaultMessage();
    }


    public String getField() {
        return field;
    }

    public String getValue() {
        return value;
    }

    public String getReason() {
        return reason;
    }
}

