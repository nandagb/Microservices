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
    
}
