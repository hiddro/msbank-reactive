package com.springboot.msbootcoin.repositories;

import com.springboot.msbootcoin.documents.entities.BootCoinDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface BootCoinRepository extends ReactiveMongoRepository<BootCoinDocument, String> {

    Mono<BootCoinDocument> findByCustomerIdentityNumber(String customerIdentityNumber);

}
