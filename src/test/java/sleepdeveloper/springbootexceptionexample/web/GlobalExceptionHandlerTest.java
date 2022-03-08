package sleepdeveloper.springbootexceptionexample.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sleepdeveloper.springbootexceptionexample.domain.exception.ErrorCode;
import sleepdeveloper.springbootexceptionexample.web.codes.ExceptionHandlerTestHandler;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class GlobalExceptionHandlerTest {

    @Autowired
    MockMvc mockMvc;

    ErrorCode errorCode = ErrorCode.FOR_TEST_DO_NOT_USE;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void BaseBusinessException_오류발생() throws Exception {

        mockMvc.perform(get("/BaseBusinessException"))
                .andExpect(status().is(errorCode.getStatus()))
                .andExpect(jsonPath("status").value(errorCode.getStatus()))
                .andExpect(jsonPath("statusCode").value(errorCode.getStatusCode()))
                .andExpect(jsonPath("message").value(errorCode.getMessage()));
    }

    @Test
    void HttpMessageNotReadableException_오류발생() throws Exception {

        mockMvc.perform(post("/HttpMessageNotReadableException")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content("fail"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message").value("Message Parsing Error, please send right value"));
    }

    @Test
    void MethodArgumentNotValidException_오류발생() throws Exception {

        mockMvc.perform(post("/MethodArgumentNotValidException")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(new ExceptionHandlerTestHandler.JsonForTest(123, ""))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("status").exists())
                .andExpect(jsonPath("statusCode").exists())
                .andExpect(jsonPath("message").exists())
                .andExpect(jsonPath("fieldErrors[0].field").value("value"))
                .andExpect(jsonPath("fieldErrors[0].value").exists())
                .andExpect(jsonPath("fieldErrors[0].reason").exists())
                .andExpect(jsonPath("fieldErrors[1].field").value("value2"))
                .andExpect(jsonPath("fieldErrors[1].value").exists())
                .andExpect(jsonPath("fieldErrors[1].reason").exists());
    }

    @Test
    void Exception_오류발생() throws Exception {

        mockMvc.perform(get("/Exception")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message").exists());
    }

}