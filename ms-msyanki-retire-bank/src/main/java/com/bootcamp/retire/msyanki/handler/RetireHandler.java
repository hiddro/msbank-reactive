package com.bootcamp.retire.msyanki.handler;

import com.bootcamp.retire.msyanki.documents.entities.Retire;
import com.bootcamp.retire.msyanki.services.IRetireService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class RetireHandler {

    @Autowired
    private IRetireService retireService;

    private static final Logger log = LoggerFactory.getLogger(RetireHandler.class);

    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(retireService.findAll(), Retire.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request){
        String yankiNumber = request.pathVariable("id");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(retireService.findById(yankiNumber), Retire.class);
    }

    public Mono<ServerResponse> findByCustomerIdentityNumber(ServerRequest request) {
        String yankiNumber = request.pathVariable("customerIdentityNumber");
        log.info("El CustomerAccountNumber es " + yankiNumber);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(retireService.findByCustomerIdentityNumber(yankiNumber), Retire.class);
    }

    public Mono<ServerResponse> createRetireYanki(ServerRequest request){

        Mono<Retire> retireMono = request.bodyToMono(Retire.class);

        return retireMono.flatMap(retireRequest -> retireService.createRetire(retireRequest))
                .flatMap(c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.notFound().build());

    }
}
