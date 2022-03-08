package sleepdeveloper.springbootexceptionexample.web.dto;

public class ExceptionMessageResponse {

    private String message;

    public ExceptionMessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
