package com.bootcamp.transaction.msyanki.handler;

import com.bootcamp.transaction.msyanki.documents.TransactionYanki;
import com.bootcamp.transaction.msyanki.services.ITransactionYankiService;
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
public class TransactionYankiHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionYankiHandler.class);

    @Autowired
    private ITransactionYankiService transactionYankiService;

    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(transactionYankiService.findAll(), TransactionYanki.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request){
        String yankiNumber = request.pathVariable("id");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(transactionYankiService.findById(yankiNumber), TransactionYanki.class);
    }

    public Mono<ServerResponse> findByCustomerIdentityNumber(ServerRequest request) {
        String yankiNumber = request.pathVariable("customerIdentityNumber");
        LOGGER.info("El CustomerAccountNumber es " + yankiNumber);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(transactionYankiService.findByCustomerIdentityNumber(yankiNumber), TransactionYanki.class);
    }

    public Mono<ServerResponse> newTransactionYanki(ServerRequest request){

        Mono<TransactionYanki> transactionMono = request.bodyToMono(TransactionYanki.class);

        return transactionMono.flatMap( Request -> transactionYankiService.create(Request))
                .flatMap( c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)));
    }

}
