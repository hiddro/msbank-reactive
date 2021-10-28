package com.springboot.msbootcoin.services.Impl;

import com.springboot.msbootcoin.documents.entities.BootCoinDocument;
import com.springboot.msbootcoin.repositories.BootCoinRepository;
import com.springboot.msbootcoin.services.IBootCoinServices;
import com.springboot.msbootcoin.services.IYankiServiceDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class BootCoinServicesImpl implements IBootCoinServices {

    private static final Logger log = LoggerFactory.getLogger(BootCoinServicesImpl.class);

    private static final double COMPRA = 5.5;

    private static final double VENTA = 6.1;

    @Autowired
    private BootCoinRepository bootCoinRepository;

    @Autowired
    private IYankiServiceDto yankiServiceDto;

    @Override
    public Mono<BootCoinDocument> createBootCoin(BootCoinDocument bootCoinDocument) {

        return yankiServiceDto.findByCustomerIdentityNumber(bootCoinDocument.getCustomerIdentityNumber()).flatMap(c -> {
            if(c.getCustomerIdentityNumber() == null){
                log.info("No puede crearse una cuenta BootCoin sin tener una cuenta de Yanki");
                return Mono.just(BootCoinDocument.builder().build());
            }

            bootCoinDocument.setTypeOfAccount("BOOT_COIN");
            bootCoinDocument.setAccountPayment(c.getTypeOfAccount());
            bootCoinDocument.setCreateBootCoin(new Date());

            c.setAmountYanki(c.getAmountYanki() - bootCoinDocument.getAmountBitCoin()*COMPRA);

            return yankiServiceDto.updateYanki(c).flatMap(d -> {
                return bootCoinRepository.save(bootCoinDocument);
            });
        });
    }

    @Override
    public Mono<BootCoinDocument> updateBootCoin(String id, BootCoinDocument bootCoinDocument) {
        return bootCoinRepository.findById(id).flatMap(c -> {
            c.setAmountBitCoin(bootCoinDocument.getAmountBitCoin());
            return bootCoinRepository.save(c);
        });
    }

    @Override
    public Mono<BootCoinDocument> findByCustomerIdentityNumber(String id) {
        return bootCoinRepository.findByCustomerIdentityNumber(id);
    }

    @Override
    public Mono<BootCoinDocument> create(BootCoinDocument o) {
        return bootCoinRepository.save(o);
    }

    @Override
    public Flux<BootCoinDocument> findAll() {
        return bootCoinRepository.findAll();
    }

    @Override
    public Mono<BootCoinDocument> findById(String s) {
        return bootCoinRepository.findById(s);
    }

    @Override
    public Mono<BootCoinDocument> update(BootCoinDocument o) {
        return bootCoinRepository.findById(o.getId()).flatMap(c -> {
            return bootCoinRepository.save(c);
        });
    }

    @Override
    public Mono<Void> delete(BootCoinDocument o) {
        return bootCoinRepository.findById(o.getId()).flatMap(c -> {
            return bootCoinRepository.delete(c);
        });
    }
}
