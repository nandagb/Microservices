package br.imd.ufrn.userservice.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    // Regular
    
    // With memory
    @GetMapping("regular")
    public String Chat(@RequestParam String prompt) {
        System.out.println("Chamando AI Service Client");
        String answer = aiServiceClient.chat(prompt);
        System.out.println("resposta da IA: " + answer);
        return answer;
    }

    // RAG

    // RAG users and memory
    @GetMapping("user")
    public String userChat(@RequestParam String prompt) {
        return aiServiceClient.userChat(prompt);
    }

    // Get Search Results (prompt, answer and context)
    @GetMapping("details-user")
    public SearchResult detailsUserChat(@RequestParam String prompt) {
        return aiServiceClient.detailsUserChat(prompt);
    }

    // Get Search Results (only context)
    @GetMapping("context-user")
    public String promptUserChat(@RequestParam String prompt) {
        return aiServiceClient.contextUserChat(prompt);
    }

    // Add users to RAG
    @PostMapping("add-users")
    public void addAllUsers() {
        aiServiceClient.addUsers();
    }

    // MCP

    // MCP users no memory
    @GetMapping("mcp-users")
    public String mcpChat(@RequestParam String prompt) {
        return aiServiceClient.mcpChat(prompt);
    }
}
