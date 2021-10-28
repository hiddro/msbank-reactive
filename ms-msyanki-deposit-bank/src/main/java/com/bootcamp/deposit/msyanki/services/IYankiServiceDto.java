package com.bootcamp.deposit.msyanki.services;

import com.bootcamp.deposit.msyanki.documents.dto.YankiDocumentDto;
import reactor.core.publisher.Mono;

public interface IYankiServiceDto {

    Mono<YankiDocumentDto> findByCustomerIdentityNumber(String customerIdentityNumber);

    Mono<YankiDocumentDto> updateYanki(YankiDocumentDto yankiDocument);
}
