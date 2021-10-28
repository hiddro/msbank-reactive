package com.springboot.transaction.msbootcoin.services;

import com.springboot.transaction.msbootcoin.documents.dto.YankiDtoDocument;
import reactor.core.publisher.Mono;

public interface IYankiServiceDto {

    Mono<YankiDtoDocument> findByCustomerIdentityNumber(String customerIdentityNumber);

    Mono<YankiDtoDocument> updateYanki(YankiDtoDocument yankiDocument);

}
