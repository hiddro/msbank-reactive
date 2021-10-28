package com.bootcamp.msyanki.config;

import com.bootcamp.msyanki.handler.YankiHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(YankiHandler yankiHandler){
        return route(GET("/api/currentYanki"), yankiHandler::findAll)
                .andRoute(GET("/api/currentYanki/number/{customerIdentityNumber}"), yankiHandler::findByCustomerIdentityNumber)
                .andRoute(GET("/api/currentYanki/phone/{nroPhone}"), yankiHandler::findByNroPhone)
                .andRoute(GET("/api/currentYanki/imei/{imeiPhone}"), yankiHandler::findByImeiPhone)
                .andRoute(GET("/api/currentYanki/email/{email}"),yankiHandler::findByEmail)
                .andRoute(POST("/api/currentYanki"), yankiHandler::newCurrentYanki)
                .andRoute(PUT("/api/currentYanki/{id}"), yankiHandler::updateYankiAccount)
                .andRoute(DELETE("/api/currentYanki/{id}"), yankiHandler::deleteYankiAccount);
    }
}
