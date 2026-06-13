package br.imd.ufrn.ai_service.Controller.Rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.imd.ufrn.ai_service.Context.UserData;
import br.imd.ufrn.ai_service.Service.ChatService;
import br.imd.ufrn.ai_service.Service.RagUserService;

@RestController
@RequestMapping("chat")
public class RestChatController {
    private final ChatService chatService;
    private final RagUserService ragUserService;

    public RestChatController(ChatService chatService, RagUserService ragUserService) {
        this.chatService = chatService;
        this.ragUserService = ragUserService;
    }

    @GetMapping()
    public String getChatAnswer(@RequestParam String prompt) {
        return chatService.getChatAnswer(prompt);
    }

    @GetMapping("users")
    public String getEmbbedUserAnswer(@RequestParam String prompt) {
        return ragUserService.getChatAnswer(prompt);
    }

    @GetMapping("add-users")
    public void addUsers() {
        ragUserService.saveUsers(UserData.USERS);
    }

    @PostMapping("add-users")
    public void addAllUsers() {
        ragUserService.saveUsers(UserData.USERS);
    }
}
