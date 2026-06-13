package br.imd.ufrn.userservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GraphQLConfig {
    @Bean
    HttpGraphQlClient aiGraphQlClient() {

        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8090/graphql")
                .build();

        return HttpGraphQlClient.create(webClient);
    }
}
