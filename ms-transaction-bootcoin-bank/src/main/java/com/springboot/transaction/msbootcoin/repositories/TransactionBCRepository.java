package com.springboot.transaction.msbootcoin.repositories;

import com.springboot.transaction.msbootcoin.documents.TransactionBCDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TransactionBCRepository extends ReactiveMongoRepository<TransactionBCDocument, String> {
}
