package br.imd.ufrn.ai_service.Controller.GraphQL;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import br.imd.ufrn.ai_service.Context.UserData;
import br.imd.ufrn.ai_service.Model.SearchResult;
import br.imd.ufrn.ai_service.Service.ChatService;
import br.imd.ufrn.ai_service.Service.McpChatService;
import br.imd.ufrn.ai_service.Service.RagUserService;

@Controller
public class GraphQLChatController {
    private final ChatService chatService;
    private final RagUserService ragUserService;
    private final McpChatService mcpChatService;

    public GraphQLChatController(ChatService chatService, RagUserService ragUserService, McpChatService mcpChatService) {
        this.chatService = chatService;
        this.ragUserService = ragUserService;
        this.mcpChatService = mcpChatService;
    }

    // Regular

    // With memory
    @QueryMapping
    public String chat(@Argument String prompt) {
        return chatService.getChatAnswer(prompt);
    }

    // RAG

    // RAG users and memory
    @QueryMapping
    public String userChat(@Argument String prompt) {
        return ragUserService.getChatAnswer(prompt);
    }

    // Get Search Results (prompt, answer and context)
    @QueryMapping
    public SearchResult detailsUserChat(@Argument String prompt) {
        return ragUserService.getDetailsChatAnswer(prompt);
    }

    // Add users to RAG
    @MutationMapping
    public Boolean addUsers() {
        ragUserService.saveUsers(UserData.USERS);
        return true;
    }

    // MCP

    // MCP users no memory
    @QueryMapping
    public String mcpChat(@Argument String prompt) {
        return mcpChatService.getChatAnswer(prompt);
    }
}
