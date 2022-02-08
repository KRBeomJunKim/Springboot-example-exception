package sleepdeveloper.springbootexceptionexample.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sleepdeveloper.springbootexceptionexample.common.exception.BaseBusinessException;
import sleepdeveloper.springbootexceptionexample.common.exception.ErrorCode;

@RestController
public class MainController {

    @GetMapping("/ex")
    public String ex(){
        throw new BaseBusinessException(ErrorCode.USER_NOT_FOUND);
    }
}
