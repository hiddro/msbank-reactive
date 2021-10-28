package com.bootcamp.msyanki.repositories;

import com.bootcamp.msyanki.documents.entities.YankiDocument;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RedisRepository {
    Flux<YankiDocument> findAll();
    Mono<YankiDocument> findByCustomerIdentityNumber(String customerIdentityNumber);
    Mono<YankiDocument> findByNroPhone(String nroPhone);
}
