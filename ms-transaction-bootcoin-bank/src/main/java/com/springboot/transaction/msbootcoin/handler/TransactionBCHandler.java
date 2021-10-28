package com.springboot.transaction.msbootcoin.handler;

import com.springboot.transaction.msbootcoin.documents.TransactionBCDocument;
import com.springboot.transaction.msbootcoin.services.ITransactionBCService;
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
public class TransactionBCHandler {

    private static final Logger log = LoggerFactory.getLogger(TransactionBCHandler.class);

    @Autowired
    private ITransactionBCService transactionBCService;

    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(transactionBCService.findAll(), TransactionBCDocument.class);
    }

    public Mono<ServerResponse> createTransactionBC(ServerRequest request){

        Mono<TransactionBCDocument> exchangeDocumentMono = request.bodyToMono(TransactionBCDocument.class);

        return exchangeDocumentMono.flatMap(exchangeRequest -> transactionBCService.createTransaction(exchangeRequest))
                .flatMap( c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> aceptRequestTransacionBC(ServerRequest request){
        Mono<TransactionBCDocument> transactionBCDocumentMono = request.bodyToMono(TransactionBCDocument.class);
        String id = request.pathVariable("id");

        return transactionBCDocumentMono.flatMap(tbc -> transactionBCService.aceptRequest(id, tbc))
                .flatMap( c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.badRequest().build());

    }

}
