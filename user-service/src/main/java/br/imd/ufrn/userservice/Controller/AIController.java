package br.imd.ufrn.userservice.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.imd.ufrn.userservice.Client.AIServiceClient;
import br.imd.ufrn.userservice.Model.SearchResult;

@RestController
@RequestMapping("chat")
public class AIController {
    private final AIServiceClient aiServiceClient;

    public AIController(AIServiceClient aiServiceClient) {
        this.aiServiceClient = aiServiceClient;
    }

    @GetMapping("regular")
    public String Chat(@RequestParam String prompt) {
        return aiServiceClient.chat(prompt);
    }

    @GetMapping("details-user")
    public SearchResult detailsUserChat(@RequestParam String prompt) {
        return aiServiceClient.detailsUserChat(prompt);
    }

    @GetMapping("context-user")
    public String promptUserChat(@RequestParam String prompt) {
        return aiServiceClient.contextUserChat(prompt);
    }

    @GetMapping("user")
    public String userChat(@RequestParam String prompt) {
        return aiServiceClient.userChat(prompt);
    }
}
