package com.bootcamp.transaction.msyanki.repository;

import com.bootcamp.transaction.msyanki.documents.TransactionYanki;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TransactionYankieRepository extends ReactiveMongoRepository<TransactionYanki, String> {
}
