package com.bootcamp.msyanki.services.Impl;

import com.bootcamp.msyanki.documents.entities.YankiDocument;
import com.bootcamp.msyanki.repositories.YankiRepository;
import com.bootcamp.msyanki.services.IYankiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class YankiServiceImpl implements IYankiService {

    public static final Logger log = LoggerFactory.getLogger(YankiServiceImpl.class);

    @Autowired
    private YankiRepository yankiRepository;

//    private ReactiveValueOperations<String, YankiDocument> yankiOps;
//
//    public YankiServiceImpl(ReactiveValueOperations<String, YankiDocument> yankiOps) {
//        this.yankiOps = yankiOps;
//    }

    @Override
    public Mono<YankiDocument> validateFields(YankiDocument accountYankie) {
        return yankiRepository.validate(accountYankie.getCustomerIdentityNumber(),
                accountYankie.getNroPhone(),
                accountYankie.getImeiPhone(),
                accountYankie.getEmail()).switchIfEmpty(Mono.just(YankiDocument.builder().
                customerIdentityNumber(null).nroPhone(null).imeiPhone(null).email(null).build()));
    }

    @Override
    public Mono<YankiDocument> findByCustomerIdentityNumber(String customerIdentityNumber) {
        return yankiRepository.findByCustomerIdentityNumber(customerIdentityNumber);
    }

    @Override
    public Mono<YankiDocument> findByNroPhone(String nroPhone) {
        return yankiRepository.findByNroPhone(nroPhone);
    }

    @Override
    public Mono<YankiDocument> findByImeiPhone(String imeiPhone) {
        return yankiRepository.findByImeiPhone(imeiPhone);
    }

    @Override
    public Mono<YankiDocument> findByEmail(String email) {
        return yankiRepository.findByEmail(email);
    }

    @Override
    public Mono<YankiDocument> create(YankiDocument yankiDocument) {
        return yankiRepository.save(yankiDocument);
    }

    @Override
    public Flux<YankiDocument> findAll() {
        return yankiRepository.findAll();
    }

    @Override
    public Mono<YankiDocument> findById(String s) {
        return yankiRepository.findById(s);
    }

    @Override
    public Mono<YankiDocument> update(YankiDocument o) {
        return yankiRepository.save(o);
    }

    @Override
    public Mono<Void> delete(YankiDocument o) {
        return yankiRepository.delete(o);
    }
}
