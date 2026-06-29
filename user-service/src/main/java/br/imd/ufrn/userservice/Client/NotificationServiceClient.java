package br.imd.ufrn.userservice.Client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class NotificationServiceClient {
    private final WebClient webClient;

    public NotificationServiceClient(WebClient.Builder builder) {

        this.webClient = builder
                .baseUrl("http://FUNCTION")
                .build();
    }

    public void notifyUserCreated(String name) {

        webClient.post()
                .uri("/notifyUserCreated")
                .bodyValue(name)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
