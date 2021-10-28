package com.bootcamp.deposit.msyanki.handler;

import com.bootcamp.deposit.msyanki.documents.entities.Deposit;
import com.bootcamp.deposit.msyanki.services.IDepositYankiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class DepositYankiHandler {

    private static final Logger log = LoggerFactory.getLogger(DepositYankiHandler.class);

    @Autowired
    private IDepositYankiService depositYankiService;

    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(depositYankiService.findAll(), Deposit.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request){
        String yankiNumber = request.pathVariable("id");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(depositYankiService.findById(yankiNumber), Deposit.class);
    }

    public Mono<ServerResponse> findByCustomerIdentityNumber(ServerRequest request) {
        String yankiNumber = request.pathVariable("customerIdentityNumber");
        log.info("El CustomerAccountNumber es " + yankiNumber);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(depositYankiService.findByCustomerIdentityNumber(yankiNumber), Deposit.class);
    }

    public Mono<ServerResponse> createDepositYanki(ServerRequest request){

        Mono<Deposit> depositMono = request.bodyToMono(Deposit.class);

        return depositMono.flatMap(depositRequest -> depositYankiService.createDeposit(depositRequest))
                .flatMap( c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
