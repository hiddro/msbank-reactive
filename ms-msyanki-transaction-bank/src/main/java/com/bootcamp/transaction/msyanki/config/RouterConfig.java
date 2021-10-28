package com.bootcamp.transaction.msyanki.config;

import com.bootcamp.transaction.msyanki.handler.TransactionYankiHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(TransactionYankiHandler transactionYankiHandler){

        return route(GET("/api/transaction-yanki"), transactionYankiHandler::findAll).
                andRoute(GET("/api/transaction-yanki/{id}"), transactionYankiHandler::findById).
                andRoute(GET("/api/transaction-yanki/number/{customerIdentityNumber}"), transactionYankiHandler::findByCustomerIdentityNumber).
                andRoute(POST("/api/transaction-yanki"), transactionYankiHandler::newTransactionYanki);
    }
}
