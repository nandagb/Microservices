package br.imd.ufrn.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	// @Bean
	// public RouteLocator myRoutes(RouteLocatorBuilder builder) {
	// 	return builder.routes()
	// 		.route(p -> p
	// 			.path("/user/**")
	// 			.uri("lb://USER-SERVICE")
	// 		)
	// 		.route(p -> p
	// 			.path("/chat/**")
	// 			.uri("lb://USER-SERVICE")
	// 		)
	// 		.build();
	// }

}