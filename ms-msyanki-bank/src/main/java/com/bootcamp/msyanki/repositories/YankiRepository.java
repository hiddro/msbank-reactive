package com.bootcamp.msyanki.repositories;

import com.bootcamp.msyanki.documents.entities.YankiDocument;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface YankiRepository extends ReactiveMongoRepository<YankiDocument, String> {

    @Query(value = "{'customerIdentityNumber': ?0, 'nroPhone': ?1, 'imeiPhone': ?2, 'email': ?3}")
    Mono<YankiDocument> validate(String type, String phone, String imei, String email);

    Mono<YankiDocument> findByCustomerIdentityNumber(String customerIdentityNumber);

    Mono<YankiDocument> findByNroPhone(String nroPhone);

    Mono<YankiDocument> findByImeiPhone(String imeiPhone);

    Mono<YankiDocument> findByEmail(String email);
}
