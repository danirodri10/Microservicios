package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("comentarios",r -> r.path("/comentarios/**")
						.uri("lb://comentarios"))
				.route("reservas",r -> r.path("/reservas/**")
						.uri("lb://reservas"))
				.route("usuarios",r -> r.path("/usuarios/**")
						.uri("lb://usuarios"))
				.route("graphiql",r -> r.path("/graphiql/**")
						.uri("lb://comentarios"))
				.build();
	}

}
