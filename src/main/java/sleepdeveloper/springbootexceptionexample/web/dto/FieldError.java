package sleepdeveloper.springbootexceptionexample.web.dto;

import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class FieldError {

    private String field;
    private String value;
    private String reason;

    public FieldError(String field, String value, String reason) {
        this.field = field;
        this.value = value;
        this.reason = reason;
    }

    public static List<FieldError> of(BindingResult bindingResult, MessageSource messageSource, Locale locale) {
        List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
        return fieldErrors.stream()
                .map(error -> new FieldError(
                        error.getField(),
                        error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                        getMessage(error, messageSource, locale)))
                .collect(Collectors.toList());
    }

    private static String getMessage(org.springframework.validation.FieldError error, MessageSource messageSource, Locale locale) {
        String result = error.getDefaultMessage();
        for (String code : error.getCodes()) {
            String messageNotFound = "NotFound";
            String message = messageSource.getMessage(code, error.getArguments(), messageNotFound, locale);
            if(!message.equals(messageNotFound)) {
                result = message;
            }
        }
        return result;
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

