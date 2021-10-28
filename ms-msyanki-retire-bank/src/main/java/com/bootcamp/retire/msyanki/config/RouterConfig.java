package com.bootcamp.retire.msyanki.config;

import com.bootcamp.retire.msyanki.handler.RetireHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(RetireHandler handler){
        return route(GET("/api/retire-yanki"), handler::findAll).
                andRoute(GET("/api/retire-yanki/{id}"), handler::findById).
                andRoute(GET("/api/retire-yanki/number/{customerIdentityNumber}"), handler::findByCustomerIdentityNumber).
                andRoute(POST("/api/retire-yanki"), handler::createRetireYanki);
    }
}
