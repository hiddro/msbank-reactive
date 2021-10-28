package com.bootcamp.retire.msyanki.services;

import com.bootcamp.retire.msyanki.documents.entities.Retire;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IRetireService extends ICrudService<Retire, String>{

    Mono<Retire> createRetire (Retire retire);

    Flux<Retire> findByCustomerIdentityNumber(String id);
}
