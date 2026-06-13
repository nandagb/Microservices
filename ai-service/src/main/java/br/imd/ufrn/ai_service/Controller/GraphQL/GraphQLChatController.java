package br.imd.ufrn.ai_service.Controller.GraphQL;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import br.imd.ufrn.ai_service.Context.UserData;
import br.imd.ufrn.ai_service.Model.SearchResult;
import br.imd.ufrn.ai_service.Service.ChatService;
import br.imd.ufrn.ai_service.Service.RagUserService;

@Controller
public class GraphQLChatController {
    private final ChatService chatService;
    private final RagUserService ragUserService;

    public GraphQLChatController(ChatService chatService, RagUserService ragUserService) {
        this.chatService = chatService;
        this.ragUserService = ragUserService;
    }

    @QueryMapping
    public String chat(@Argument String prompt) {
        return chatService.getChatAnswer(prompt);
    }

    @QueryMapping
    public String userChat(@Argument String prompt) {
        return ragUserService.getChatAnswer(prompt);
    }

    @QueryMapping
    public SearchResult detailsUserChat(@Argument String prompt) {
        return ragUserService.getDetailsChatAnswer(prompt);
    }

    @MutationMapping
    public Boolean addUsers() {
        ragUserService.saveUsers(UserData.USERS);
        return true;
    }
}
