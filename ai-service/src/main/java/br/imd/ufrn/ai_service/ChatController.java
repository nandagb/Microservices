package br.imd.ufrn.ai_service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    private final ChatService chatService;
    
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("chat")
    public String getAnswer(@RequestParam String prompt) {
        System.out.println("Testando o chat service");
        return chatService.getAnswer(prompt);
    }
}
