package br.imd.ufrn.userservice.Client;

import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import br.imd.ufrn.userservice.Model.SearchResult;

@Service
public class AIServiceClient {
    private final HttpGraphQlClient graphQlClient;

    public AIServiceClient(HttpGraphQlClient graphQlClient){
        this.graphQlClient = graphQlClient;
    }

    // Regular

    // With memory
    public String chat(String prompt) {
        return graphQlClient
            .document("""
                query chat($prompt: String!) {
                    chat(prompt: $prompt)
                }
            """)
            .variable("prompt", prompt)
            .retrieve("chat")
            .toEntity(String.class)
            .block();
    }

    // RAG

    // RAG users and memory
    public String userChat(String prompt) {
        return graphQlClient
            .document("""
                query userChat($prompt: String!) {
                    userChat(prompt: $prompt)
                }
            """)
            .variable("prompt", prompt)
            .retrieve("userChat")
            .toEntity(String.class)
            .block();
    }

    // Get Search Results (prompt, answer and context)
    public SearchResult detailsUserChat(String prompt) {
        return graphQlClient
            .document("""
                query detailsUserChat($prompt: String!) {
                    detailsUserChat(prompt: $prompt) {
                        prompt
                        answer
                        context
                    }
                }
            """)
            .variable("prompt", prompt)
            .retrieve("detailsUserChat")
            .toEntity(SearchResult.class)
            .block();
    }

    // Get Search Results (only context)
    public String contextUserChat(String prompt) {
        return graphQlClient
            .document("""
                query detailsUserChat($prompt: String!) {
                    detailsUserChat(prompt: $prompt) {
                        prompt
                        answer
                        context
                    }
                }
            """)
            .variable("prompt", prompt)
            .retrieve("detailsUserChat.context")
            .toEntity(String.class)
            .block();
    }

    // Add users to RAG
    public Boolean addUsers() {
        return graphQlClient
            .document("""
                mutation {
                    addUsers
                }
            """)
            .retrieve("addUsers")
            .toEntity(Boolean.class)
            .block();
    }

    // MCP

    // MCP users no memory
    public String mcpChat(String prompt) {
        return graphQlClient
            .document("""
                query mcpChat($prompt: String!) {
                    mcpChat(prompt: $prompt)
                }
            """)
            .variable("prompt", prompt)
            .retrieve("mcpChat")
            .toEntity(String.class)
            .block();
    }
}
