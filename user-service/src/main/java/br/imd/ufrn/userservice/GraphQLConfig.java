package br.imd.ufrn.userservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GraphQLConfig {

    @Bean
    @org.springframework.cloud.client.loadbalancer.LoadBalanced
    WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    HttpGraphQlClient aiGraphQlClient( WebClient.Builder webClientBuilder ) {

        WebClient webClient = webClientBuilder
                // .baseUrl("http://localhost:8090/graphql")
                .baseUrl("http://AI-SERVICE/graphql")
                .build();

        return HttpGraphQlClient.create(webClient);
    }
}
