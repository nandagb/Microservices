package br.imd.ufrn.userservice;

import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AIServiceClient {
    private final HttpGraphQlClient graphQlClient;

    public AIServiceClient(HttpGraphQlClient graphQlClient){
        this.graphQlClient = graphQlClient;
    }

    public String detailsUserChat(String prompt) {
        return graphQlClient
            .document("""
                query detailsUserChat($prompt: String!) {
                    detailsUserChat(prompt: $prompt) {
                        resposta
                    }
                }
            """)
            .variable("prompt", prompt)
            .retrieve("detailsUserChat.resposta")
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
