package com.bootcamp.retire.msyanki.services.Impl;

import com.bootcamp.retire.msyanki.documents.dto.TransactionYankiDto;
import com.bootcamp.retire.msyanki.services.ITransactionYankiServiceDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class TransactionYankiServiceDtoImpl implements ITransactionYankiServiceDto {

    private static final Logger log = LoggerFactory.getLogger(YankiServiceDtoImpl.class);

    @Autowired
    private WebClient.Builder clientBuilder;


    @Override
    public Mono<TransactionYankiDto> saveTransactionYanki(TransactionYankiDto transaction) {
        log.info("initializing Transaction create");

        return clientBuilder
                .build()
                .post()
                .uri("http://localhost:8077/api/transaction-yanki")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(transaction)
                .retrieve()
                .bodyToMono(TransactionYankiDto.class);
    }
}
