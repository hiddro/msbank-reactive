package com.bootcamp.msTransfer.config;

import com.bootcamp.msTransfer.handler.TransferHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * The type Router config.
 */
@Configuration
public class RouterConfig {

    /**
     * Routes router function.
     *
     * @param transferHandler the transfer handler
     * @return the router function
     */
    @Bean
    public RouterFunction<ServerResponse> routes(TransferHandler transferHandler){

        return route(GET("/api/transfer"), transferHandler::findAll)
                .andRoute(GET("/api/transfer/{id}"),transferHandler::findTransfer)
                .andRoute(POST("/api/transfer"), transferHandler::createTransfer)
                .andRoute(PUT("/api/transfer/{id}"), transferHandler::updateTransfer)
                .andRoute(DELETE("/api/transfer/{id}"), transferHandler::deleteTransfer);
    }
}
