package com.f1.api_gateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter {

    public Mono<Void> filter(ServerWebExchange serverWebExchange, GatewayFilterChain gatewayFilterChain) {
        String apiKey = serverWebExchange.getRequest().getHeaders().getFirst("x-api-key");

        if (apiKey == null || !apiKey.equals("validKey123")) {
            serverWebExchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return serverWebExchange.getResponse().setComplete();
        }

        return gatewayFilterChain.filter(serverWebExchange);
    }
}
