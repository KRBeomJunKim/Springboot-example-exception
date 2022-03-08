package sleepdeveloper.springbootexceptionexample.domain.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ErrorMessageSender {

    private final SlackService slackService;

    public ErrorMessageSender(@Value("${slack.url:#{null}}") String slackUrl) {
        this.slackService = new SlackService(slackUrl);
    }

    public void sendErrorMessage(String errorMessage) {
        if(slackService.getSlackUrl() == null) {
            System.out.println(errorMessage);
        } else{
            slackService.sendMessage(errorMessage);
        }
    }
}
