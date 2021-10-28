package com.bootcamp.msTransaction.services.Impl;

import com.bootcamp.msTransaction.models.entities.Transaction;
import com.bootcamp.msTransaction.repositories.TransactionRepository;
import com.bootcamp.msTransaction.services.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * The type Transaction service.
 */
@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private TransactionRepository repository;


    @Override
    public Mono<Transaction> create(Transaction o) {
        return repository.save(o);
    }

    @Override
    public Flux<Transaction> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Transaction> findById(String s) {
        return repository.findById(s);
    }

    @Override
    public Mono<Void> delete(Transaction o) {
        return repository.delete(o);
    }

    @Override
    public Mono<Transaction> update(Transaction o) {
        return repository.save(o);
    }

    @Override
    public Flux<Transaction> findAllByIdentityNumber(String identityNumber) {
        return repository.findAllByIdentityNumber(identityNumber);
    }

    @Override
    public Flux<Transaction> findFirst10ByIdentityNumberOrderByDateOperationDesc(String identityNumber) {
        return repository.findFirst10ByIdentityNumberOrderByDateOperationDesc(identityNumber);
    }

    @Override
    public Flux<Transaction> findAllByIdentityNumberAndDateOperationBetween(String identityNumber
            , LocalDateTime fromDate, LocalDateTime toDate) {
        return repository.findAllByIdentityNumberAndDateOperationBetween(identityNumber
                , fromDate, toDate);
    }
}
