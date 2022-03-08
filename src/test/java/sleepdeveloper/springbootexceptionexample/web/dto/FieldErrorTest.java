package sleepdeveloper.springbootexceptionexample.web.dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.validation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class FieldErrorTest {

    @Autowired
    MessageSource messageSource;

    @Test
    void of_code찾을수있을때_code에관련한message() {
        // Given
        BindingResult bindingResult = new MapBindingResult(new HashMap<>(), "testObject");
        bindingResult.rejectValue("testField", "testErrorCode", "testDefaultMessage");

        // When
        List<ExceptionFieldResponse> of = ExceptionFieldResponse.of(bindingResult, messageSource, Locale.KOREAN);

        // Then
        assertThat(of).extracting("reason").containsExactly("메시지코드에저장됨");
    }


    @Test
    void of_code찾을수없을때_defaultMessage() {
        // Given
        String defaultMessage = "failDefaultMessage";
        BindingResult bindingResult = new MapBindingResult(new HashMap<>(), "failObject");
        bindingResult.rejectValue("failField", "failErrorCode", defaultMessage);

        // When
        List<ExceptionFieldResponse> of = ExceptionFieldResponse.of(bindingResult, messageSource, Locale.KOREAN);

        // Then
        assertThat(of).extracting("reason").containsExactly(defaultMessage);
    }

}