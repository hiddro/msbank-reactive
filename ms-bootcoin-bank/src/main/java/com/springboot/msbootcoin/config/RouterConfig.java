package com.springboot.msbootcoin.config;

import com.springboot.msbootcoin.handler.BootCoinHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(BootCoinHandler bootCoinHandler){
        return route(GET("/api/bootcoin"), bootCoinHandler::findAll)
                .andRoute(GET("/api/bootcoin/{id}"), bootCoinHandler::findById)
                .andRoute(GET("/api/bootcoin/number/{customerIdentityNumber}"), bootCoinHandler::findByCustomerIdentityNumber)
                .andRoute(PUT("/api/bootcoin/{id}"), bootCoinHandler::updateAccountBootCoin)
                .andRoute(POST("/api/bootcoin"), bootCoinHandler::newAccountBootCoin);
    }
}
