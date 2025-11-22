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
                        .uri("http://localhost:8083"))
                .build();
    }
}
