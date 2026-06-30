package br.imd.ufrn.userservice.Client;

import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import br.imd.ufrn.userservice.Model.SearchResult;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class AIServiceClient {
    private final HttpGraphQlClient graphQlClient;

    public AIServiceClient(HttpGraphQlClient graphQlClient){
        this.graphQlClient = graphQlClient;
    }

    // Regular

    // With memory
    @CircuitBreaker(name="regular-chat", fallbackMethod="regularChatFallback")
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

    public String regularChatFallback(String prompt, Throwable t) {
        return "Fallback do Circuit Breaker do Regular Chat";
    }

    // RAG

    // RAG users and memory
    @CircuitBreaker(name="rag-chat", fallbackMethod="ragChatFallback")
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

    public String ragChatFallback(String prompt, Throwable t) {
        return "Fallback do Circuit Breaker do Rag Chat";
    }

    // Get Search Results (prompt, answer and context)
    @CircuitBreaker(name="details-chat", fallbackMethod="detailsChatFallback")
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

    public String detailsChatFallback(String prompt, Throwable t) {
        return "Fallback do Circuit Breaker do Details Chat";
    }

    // Get Search Results (only context)
    @CircuitBreaker(name="context-chat", fallbackMethod="contextChatFallback")
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

    public String contextChatFallback(String prompt, Throwable t) {
        return "Fallback do Circuit Breaker do Context Chat";
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
    @CircuitBreaker(name="mcp-chat", fallbackMethod="mcpChatFallback")
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

    public String mcpChatFallback(String prompt, Throwable t) {
        return "Fallback do Circuit Breaker do MCP Chat";
    }
}
