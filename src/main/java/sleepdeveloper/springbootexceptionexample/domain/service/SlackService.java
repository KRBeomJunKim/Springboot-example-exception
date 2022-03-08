package sleepdeveloper.springbootexceptionexample.domain.service;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class SlackService {

    private final String slackUrl;

    public SlackService(String slackUrl) {
        this.slackUrl = slackUrl;
    }

    public void sendMessage(String message) {
        WebClient.create(slackUrl)
                .post()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new SlackRequest(message))
                .retrieve()
                .bodyToMono(String.class)
                .subscribe();
    }

    static class SlackRequest {
        private final String text;

        public SlackRequest(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

    public String getSlackUrl() {
        return slackUrl;
    }
}
