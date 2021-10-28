package com.bootcamp.deposit.msyanki.repositories;

import com.bootcamp.deposit.msyanki.documents.entities.Deposit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DepositYankiRepository extends ReactiveMongoRepository<Deposit, String> {
}
