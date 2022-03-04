package sleepdeveloper.springbootexceptionexample.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sleepdeveloper.springbootexceptionexample.domain.exception.BaseBusinessException;
import sleepdeveloper.springbootexceptionexample.domain.exception.ErrorCode;

@RestController
public class MainController {

    @GetMapping("/ex")
    public String ex(){
        throw new BaseBusinessException(ErrorCode.USER_NOT_FOUND);
    }
}
