package br.imd.ufrn.ai_service.Controller.Rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.imd.ufrn.ai_service.Context.UserData;
import br.imd.ufrn.ai_service.Service.ChatService;
import br.imd.ufrn.ai_service.Service.McpChatService;
import br.imd.ufrn.ai_service.Service.RagUserService;

@RestController
@RequestMapping("chat")
public class RestChatController {
    private final ChatService chatService;
    private final RagUserService ragUserService;
    private final McpChatService mcpChatService;

    public RestChatController(ChatService chatService, RagUserService ragUserService, McpChatService mcpChatService) {
        this.chatService = chatService;
        this.ragUserService = ragUserService;
        this.mcpChatService = mcpChatService;
    }

    // Regular

    // No memory
    @GetMapping("dumb")
    public String getDumbChatAnswer(@RequestParam String prompt) {
        return chatService.getDumbChatAnswer(prompt);
    }

    // With memory
    @GetMapping()
    public String getChatAnswer(@RequestParam String prompt) {
        return chatService.getChatAnswer(prompt);
    }

    // MCP

    @GetMapping("mcp")
    public String getUserMcpChatAnswer(@RequestParam String prompt) {
        return mcpChatService.getChatAnswer(prompt);
    }

    // RAG

    // RAG users and no memory
    @GetMapping("dumb-users")
    public String getDumbEmbbedUserAnswer(@RequestParam String prompt) {
        return ragUserService.getDumbChatAnswer(prompt);
    }

    // RAG users and memory
    @GetMapping("users")
    public String getEmbbedUserAnswer(@RequestParam String prompt) {
        return ragUserService.getChatAnswer(prompt);
    }

    // Add users to RAG
    @GetMapping("add-users")
    public void addUsers() {
        ragUserService.saveUsers(UserData.USERS);
    }

    // Add users to RAG
    @PostMapping("add-users")
    public void addAllUsers() {
        ragUserService.saveUsers(UserData.USERS);
    }
}
