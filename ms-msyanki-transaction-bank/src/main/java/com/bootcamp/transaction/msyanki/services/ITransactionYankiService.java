package com.bootcamp.transaction.msyanki.services;

import com.bootcamp.transaction.msyanki.documents.TransactionYanki;
import reactor.core.publisher.Flux;

public interface ITransactionYankiService extends ICrudService<TransactionYanki,String>{

    Flux<TransactionYanki> findByCustomerIdentityNumber(String id);
}
