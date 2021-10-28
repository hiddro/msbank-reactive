package com.springboot.msbootcoin.handler;

import com.springboot.msbootcoin.documents.entities.BootCoinDocument;
import com.springboot.msbootcoin.services.IBootCoinServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Date;

@Component
public class BootCoinHandler {

    private static final Logger log = LoggerFactory.getLogger(BootCoinHandler.class);

    @Autowired
    private IBootCoinServices bootCoinServices;

    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(bootCoinServices.findAll(), BootCoinDocument.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request){
        String bootCoinNumber = request.pathVariable("id");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(bootCoinServices.findById(bootCoinNumber), BootCoinDocument.class);
    }

    public Mono<ServerResponse> findByCustomerIdentityNumber(ServerRequest request) {
        String bootCoinNumber = request.pathVariable("customerIdentityNumber");
        log.info("El CustomerAccountNumber es " + bootCoinNumber);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(bootCoinServices.findByCustomerIdentityNumber(bootCoinNumber), BootCoinDocument.class);
    }

    public Mono<ServerResponse> newAccountBootCoin(ServerRequest request){

        Mono<BootCoinDocument> bootCoinMono = request.bodyToMono(BootCoinDocument.class);

        return bootCoinMono.flatMap(bootCoin -> bootCoinServices.createBootCoin(bootCoin))
                .flatMap( c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c))
                ).switchIfEmpty(ServerResponse.badRequest().build());
    }

    public Mono<ServerResponse> updateAccountBootCoin(ServerRequest request){
        Mono<BootCoinDocument> bootCoinMono = request.bodyToMono(BootCoinDocument.class);
        String id = request.pathVariable("id");

        return bootCoinMono.flatMap(bc -> bootCoinServices.updateBootCoin(id, bc))
                .flatMap( c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c))
                ).switchIfEmpty(ServerResponse.badRequest().build());

    }

}
