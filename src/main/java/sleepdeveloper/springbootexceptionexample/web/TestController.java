package sleepdeveloper.springbootexceptionexample.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sleepdeveloper.springbootexceptionexample.domain.exception.BaseBusinessException;
import sleepdeveloper.springbootexceptionexample.domain.exception.ErrorCode;
import sleepdeveloper.springbootexceptionexample.web.exception.TestException;

@RestController
public class TestController {

    @GetMapping("/business-exception")
    public String businessException(){
        throw new TestException(ErrorCode.FOR_TEST_DO_NOT_USE);
    }

    @GetMapping("/exception")
    public String exception() throws Exception {
        throw new Exception("exception");
    }
}
