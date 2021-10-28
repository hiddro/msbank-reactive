package com.bootcamp.retire.msyanki.services.Impl;

import com.bootcamp.retire.msyanki.documents.dto.TransactionYankiDto;
import com.bootcamp.retire.msyanki.documents.dto.YankiDocumentDto;
import com.bootcamp.retire.msyanki.documents.entities.Retire;
import com.bootcamp.retire.msyanki.repositories.RetireRepository;
import com.bootcamp.retire.msyanki.services.IRetireService;
import com.bootcamp.retire.msyanki.services.ITransactionYankiServiceDto;
import com.bootcamp.retire.msyanki.services.IYankiServiceDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class RetireServiceImpl implements IRetireService {

    public static final Logger log = LoggerFactory.getLogger(RetireServiceImpl.class);

    @Autowired
    private RetireRepository retireRepository;

    @Autowired
    private IYankiServiceDto yankiServiceDto;

    @Autowired
    private ITransactionYankiServiceDto transactionService;

    @Override
    public Mono<Retire> create(Retire o) {
        return retireRepository.save(o);
    }

    @Override
    public Flux<Retire> findAll() {
        return retireRepository.findAll();
    }

    @Override
    public Mono<Retire> findById(String s) {
        return retireRepository.findById(s);
    }

    @Override
    public Mono<Retire> update(Retire o) {
        return retireRepository.save(o);
    }

    @Override
    public Mono<Void> delete(Retire o) {
        return retireRepository.delete(o);
    }

    @Override
    public Mono<Retire> createRetire(Retire retire) {

        // Mono<YankiDocumentDto> yankiAccount = yankiServiceDto.findByCustomerIdentityNumber(retire.getCustomerIdentityNumber());

        return yankiServiceDto.findByCustomerIdentityNumber(retire.getCustomerIdentityNumber()).flatMap(c -> {

            if(retire.getAmount() > c.getAmountYanki()){
                log.info("No se puede hacer un retiro, saldo insuficiente en Yanki");
                return Mono.just(Retire.builder().build());
            }

            TransactionYankiDto transactionYankiDto = new TransactionYankiDto();
            transactionYankiDto.setTypeoftransaction("RETIRE");
            transactionYankiDto.setTypeOfAccount("COIN_PURSE");
            transactionYankiDto.setCustomerIdentityNumber(retire.getCustomerIdentityNumber());
            transactionYankiDto.setTransactionAmount(retire.getAmount());
            transactionYankiDto.setOwnerYanki(retire.getOwnerYankie());
            transactionYankiDto.setDateOperation(new Date());
            transactionYankiDto.setTransactionDescription(retire.getDescription());

            return transactionService.saveTransactionYanki(transactionYankiDto).flatMap(d -> {
                c.setAmountYanki(c.getAmountYanki() - retire.getAmount());
                retire.setNroPhone(c.getNroPhone());
                return yankiServiceDto.updateYanki(c).flatMap(e -> {
                    return retireRepository.save(retire);
                });
            });
        });
    }

    @Override
    public Flux<Retire> findByCustomerIdentityNumber(String id) {
        return retireRepository.findAll().filter(c -> {
            return c.getCustomerIdentityNumber().equals(id);
        });
    }
}
