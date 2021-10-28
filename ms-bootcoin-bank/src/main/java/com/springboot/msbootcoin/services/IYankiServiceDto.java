package com.springboot.msbootcoin.services;

import com.springboot.msbootcoin.documents.dto.YankiDocumentDto;
import reactor.core.publisher.Mono;

public interface IYankiServiceDto {

    Mono<YankiDocumentDto> findByCustomerIdentityNumber(String customerIdentityNumber);

    Mono<YankiDocumentDto> updateYanki(YankiDocumentDto yankiDocument);
}
