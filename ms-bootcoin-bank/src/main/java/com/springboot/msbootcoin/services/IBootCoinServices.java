package com.springboot.msbootcoin.services;

import com.springboot.msbootcoin.documents.entities.BootCoinDocument;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IBootCoinServices extends ICrudService<BootCoinDocument, String> {

    Mono<BootCoinDocument> createBootCoin (BootCoinDocument bootCoinDocument);

    Mono<BootCoinDocument> updateBootCoin (String id, BootCoinDocument bootCoinDocument);

    Mono<BootCoinDocument> findByCustomerIdentityNumber(String id);
}
