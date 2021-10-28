package com.springboot.transaction.msbootcoin.config;

import com.springboot.transaction.msbootcoin.handler.TransactionBCHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(TransactionBCHandler transactionHandler){
        return route(GET("/api/transaction-bc"), transactionHandler::findAll).
                andRoute(PUT("/api/transaction-bc/{id}"), transactionHandler::aceptRequestTransacionBC).
                andRoute(POST("/api/transaction-bc"), transactionHandler::createTransactionBC);
    }
}
