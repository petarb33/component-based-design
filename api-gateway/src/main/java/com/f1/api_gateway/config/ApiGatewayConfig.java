package com.f1.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("home", r -> r.path("/home")
                        .filters(f -> f.rewritePath("/home", "/api/races")
                                .addRequestHeader("x-api-key", "validKey123"))
                        .uri("lb://races"))
                .route("tickets", r -> r.path("/tickets")
                        .filters(f -> f.rewritePath("/tickets", "/api/tickets")
                                .addRequestHeader("x-api-key", "validKey123"))
                        .uri("lb://tickets"))
                .route("login", r -> r.path("/login")
                        .filters(f -> f.rewritePath("/login", "/api/login")
                                .addRequestHeader("x-api-key", "validKey123"))
                        .uri("http://localhost:8080/login"))
                .route("signup", r -> r.path("/signup")
                        .filters(f -> f.rewritePath("/signup", "/api/signup")
                                .addRequestHeader("x-api-key", "validKey123"))
                        .uri("http://localhost:8080/login"))
                .build();
    }
}
