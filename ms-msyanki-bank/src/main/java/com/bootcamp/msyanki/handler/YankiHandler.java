package com.bootcamp.msyanki.handler;

import com.bootcamp.msyanki.documents.entities.YankiDocument;
import com.bootcamp.msyanki.services.IYankiService;
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
public class YankiHandler {

    private static final Logger log = LoggerFactory.getLogger(YankiHandler.class);

    @Autowired
    private IYankiService yankiService;

    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(yankiService.findAll(), YankiDocument.class);
    }

    public Mono<ServerResponse> newCurrentYanki(ServerRequest request){

        Mono<YankiDocument> yankiMono = request.bodyToMono(YankiDocument.class);

        return yankiMono.flatMap(account -> {
                    Mono<YankiDocument> existYanki = yankiService.validateFields(account);
                    return existYanki.flatMap(c -> {
                        if(c.getId() != null){
                            log.info("La cuenta le pertenece : " + c.getOwnerYankie() + " con numero " + c.getNroPhone());
                            return Mono.error(new RuntimeException("THERE IS AN YANKI ACCOUNT WITH THIS OWNER ALREADY"));
                        }
                        account.setTypeOfAccount("COIN_PURSE");
                        account.setCreateYanki(new Date());
                        return yankiService.create(account);
                    });
                })
                .flatMap( c -> ServerResponse
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(c))
                ).switchIfEmpty(ServerResponse.badRequest().build());
    }

    public Mono<ServerResponse> findByCustomerIdentityNumber(ServerRequest request) {
        String yankiNumber = request.pathVariable("customerIdentityNumber");
        log.info("El CustomerAccountNumber es " + yankiNumber);
        return yankiService.findByCustomerIdentityNumber(yankiNumber).flatMap(c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> findByNroPhone(ServerRequest request) {
        String yankiNroPhone = request.pathVariable("nroPhone");
        log.info("El NroPhone es " + yankiNroPhone);
        return yankiService.findByNroPhone(yankiNroPhone).flatMap(c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> findByImeiPhone(ServerRequest request) {
        String yankiImeiPhone = request.pathVariable("imeiPhone");
        log.info("El CustomerAccountNumber es " + yankiImeiPhone);
        return yankiService.findByImeiPhone(yankiImeiPhone).flatMap(c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> findByEmail(ServerRequest request) {
        String yankiEmail = request.pathVariable("email");
        log.info("El Email es " + yankiEmail);
        return yankiService.findByEmail(yankiEmail).flatMap(c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> updateYankiAccount(ServerRequest request){
        Mono<YankiDocument> accountMono = request.bodyToMono(YankiDocument.class);
        String id = request.pathVariable("id");

        return yankiService.findById(id).zipWith(accountMono, (db,req) -> {
                    db.setEmail(req.getEmail());
                    db.setNroPhone(req.getNroPhone());
                    db.setImeiPhone((req.getImeiPhone()));
                    db.setAmountYanki(req.getAmountYanki());
                    return db;
                }).flatMap( c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(yankiService.update(c),YankiDocument.class))
                .switchIfEmpty(ServerResponse.notFound().build());

    }

    public Mono<ServerResponse> deleteYankiAccount(ServerRequest request){

        String id = request.pathVariable("id");

        Mono<YankiDocument> yankiMono = yankiService.findById(id);

        return yankiMono
                .doOnNext(c -> log.info("delete account yanki: ", c.getId()))
                .flatMap(c -> yankiService.delete(c).then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
