package com.bootcamp.deposit.msyanki.services;

import com.bootcamp.deposit.msyanki.documents.entities.Deposit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IDepositYankiService extends ICrudService<Deposit, String>{

    Mono<Deposit> createDeposit (Deposit deposit);

    Flux<Deposit> findByCustomerIdentityNumber(String id);
}
