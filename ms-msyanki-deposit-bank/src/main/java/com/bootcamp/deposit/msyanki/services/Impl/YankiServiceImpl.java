package com.bootcamp.deposit.msyanki.services.Impl;

import com.bootcamp.deposit.msyanki.documents.dto.YankiDocumentDto;
import com.bootcamp.deposit.msyanki.services.IYankiServiceDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class YankiServiceImpl implements IYankiServiceDto {

    private static final Logger log = LoggerFactory.getLogger(YankiServiceImpl.class);

    @Autowired
    private WebClient.Builder clientBuilder;

    @Override
    public Mono<YankiDocumentDto> findByCustomerIdentityNumber(String customerIdentityNumber) {
        log.info("Initializing Find Customer Id  Number");

        return clientBuilder
                .build()
                .get()
                .uri("http://localhost:8075/api/currentYanki/number/" + customerIdentityNumber)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(YankiDocumentDto.class))
                .doOnNext(c -> log.info("Account Yanki", c.getCustomerIdentityNumber()));
    }

    @Override
    public Mono<YankiDocumentDto> updateYanki(YankiDocumentDto yankiDocument) {
        YankiDocumentDto yankiDocumentDto = YankiDocumentDto.builder()
                .id(yankiDocument.getId())
                .email(yankiDocument.getEmail())
                .nroPhone(yankiDocument.getNroPhone())
                .imeiPhone(yankiDocument.getImeiPhone())
                .amountYanki(yankiDocument.getAmountYanki())
                .build();

        return clientBuilder
                .build()
                .put()
                .uri("http://localhost:8075/api/currentYanki/" + yankiDocumentDto.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(yankiDocumentDto)
                .retrieve()
                .bodyToMono(YankiDocumentDto.class);
    }
}
