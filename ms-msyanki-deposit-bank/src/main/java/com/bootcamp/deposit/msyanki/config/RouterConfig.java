package com.bootcamp.deposit.msyanki.config;

import com.bootcamp.deposit.msyanki.handler.DepositYankiHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(DepositYankiHandler depositYankiHandler){
        return route(GET("/api/deposit-yanki"), depositYankiHandler::findAll).
                andRoute(GET("/api/deposit-yanki/{id}"), depositYankiHandler::findById).
                andRoute(GET("/api/deposit-yanki/number/{customerIdentityNumber}"), depositYankiHandler::findByCustomerIdentityNumber).
                andRoute(POST("/api/deposit-yanki"), depositYankiHandler::createDepositYanki);
    }
}
