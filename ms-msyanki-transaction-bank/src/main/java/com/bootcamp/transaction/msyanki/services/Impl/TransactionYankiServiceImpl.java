package com.bootcamp.transaction.msyanki.services.Impl;

import com.bootcamp.transaction.msyanki.documents.TransactionYanki;
import com.bootcamp.transaction.msyanki.repository.TransactionYankieRepository;
import com.bootcamp.transaction.msyanki.services.ITransactionYankiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionYankiServiceImpl implements ITransactionYankiService {

    @Autowired
    private TransactionYankieRepository transactionYankieRepository;

    @Override
    public Mono<TransactionYanki> create(TransactionYanki o) {
        return transactionYankieRepository.save(o);
    }

    @Override
    public Flux<TransactionYanki> findAll() {
        return transactionYankieRepository.findAll();
    }

    @Override
    public Mono<TransactionYanki> findById(String s) {
        return transactionYankieRepository.findById(s);
    }

    @Override
    public Mono<Void> delete(TransactionYanki o) {
        return transactionYankieRepository.delete(o);
    }

    @Override
    public Mono<TransactionYanki> update(TransactionYanki o) {
        return transactionYankieRepository.save(o);
    }

    @Override
    public Flux<TransactionYanki> findByCustomerIdentityNumber(String id) {
        return transactionYankieRepository.findAll().filter(c -> {
            return c.getCustomerIdentityNumber().equals(id);
        });
    }
}
