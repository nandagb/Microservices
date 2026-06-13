package br.imd.ufrn.userservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("chat")
public class AIController {
    private final AIServiceClient aiServiceClient;

    public AIController(AIServiceClient aiServiceClient) {
        this.aiServiceClient = aiServiceClient;
    }

    @GetMapping("details-user")
    public String detailsUserChat(@RequestParam String prompt) {
        System.out.println("Executando chat details user no user-service");
        return aiServiceClient.detailsUserChat(prompt);
    }

    @GetMapping("user")
    public String userChat(@RequestParam String prompt) {
        System.out.println("Executando chat user no user-service");
        return aiServiceClient.userChat(prompt);
    }
}
