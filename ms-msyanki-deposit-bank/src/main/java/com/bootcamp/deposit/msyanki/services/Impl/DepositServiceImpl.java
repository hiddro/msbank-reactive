package com.bootcamp.deposit.msyanki.services.Impl;

import com.bootcamp.deposit.msyanki.documents.dto.TransactionYankiDto;
import com.bootcamp.deposit.msyanki.documents.entities.Deposit;
import com.bootcamp.deposit.msyanki.repositories.DepositYankiRepository;
import com.bootcamp.deposit.msyanki.services.IDepositYankiService;
import com.bootcamp.deposit.msyanki.services.ITransictionYankiServiceDto;
import com.bootcamp.deposit.msyanki.services.IYankiServiceDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class DepositServiceImpl implements IDepositYankiService {

    private static final Logger log = LoggerFactory.getLogger(DepositServiceImpl.class);

    @Autowired
    private DepositYankiRepository depositYankiRepository;

    @Autowired
    private IYankiServiceDto yankiServiceDto;

    @Autowired
    private ITransictionYankiServiceDto transactionService;


    @Override
    public Mono<Deposit> create(Deposit o) {
        return depositYankiRepository.save(o);
    }

    @Override
    public Flux<Deposit> findAll() {
        return depositYankiRepository.findAll();
    }

    @Override
    public Mono<Deposit> findById(String s) {
        return depositYankiRepository.findById(s);
    }

    @Override
    public Mono<Deposit> update(Deposit o) {
        return depositYankiRepository.findById(o.getId()).flatMap(c -> {
            return depositYankiRepository.save(o);
        });
    }

    @Override
    public Mono<Void> delete(Deposit o) {
        return depositYankiRepository.delete(o);
    }

    @Override
    public Mono<Deposit> createDeposit(Deposit deposit) {
        return yankiServiceDto.findByCustomerIdentityNumber(deposit.getCustomerIdentityNumber()).flatMap(c ->{
            System.out.println("account" + c);
            if(c.getId() == null){
                log.info("No se puede hacer un deposito, no existe la cuenta yanki");
                return Mono.just(Deposit.builder().build());
            }

            TransactionYankiDto transactionYankiDto = new TransactionYankiDto();
            transactionYankiDto.setTypeoftransaction("DEPOSIT");
            transactionYankiDto.setTypeOfAccount("COIN_PURSE");
            transactionYankiDto.setCustomerIdentityNumber(deposit.getCustomerIdentityNumber());
            transactionYankiDto.setTransactionAmount(deposit.getAmount());
            transactionYankiDto.setOwnerYanki(deposit.getOwnerYankie());
            transactionYankiDto.setDateOperation(new Date());
            transactionYankiDto.setTransactionDescription("DEPOSIT COIN PURSE");

            return transactionService.saveTransactionYanki(transactionYankiDto).flatMap(d -> {
                c.setAmountYanki(c.getAmountYanki() + deposit.getAmount());
                deposit.setNroPhone(c.getNroPhone());

                return yankiServiceDto.updateYanki(c).flatMap(e -> {
                    return depositYankiRepository.save(deposit);
                });
            });
        });
    }

    @Override
    public Flux<Deposit> findByCustomerIdentityNumber(String id) {
        return depositYankiRepository.findAll().filter(c -> {
            return c.getCustomerIdentityNumber().equals(id);
        });
    }
}
