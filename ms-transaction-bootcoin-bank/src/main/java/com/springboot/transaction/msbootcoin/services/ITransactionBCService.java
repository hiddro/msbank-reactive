package com.springboot.transaction.msbootcoin.services;

import com.springboot.transaction.msbootcoin.documents.TransactionBCDocument;
import reactor.core.publisher.Mono;

public interface ITransactionBCService extends ICrudService<TransactionBCDocument, String> {

    Mono<TransactionBCDocument> createTransaction (TransactionBCDocument transactionBCDocument);

    Mono<TransactionBCDocument> aceptRequest(String id, TransactionBCDocument transactionBCDocument);
}
