package com.bootcamp.retire.msyanki.services;

import com.bootcamp.retire.msyanki.documents.dto.YankiDocumentDto;
import reactor.core.publisher.Mono;

public interface IYankiServiceDto {

    Mono<YankiDocumentDto> findByCustomerIdentityNumber(String customerIdentityNumber);


    Mono<YankiDocumentDto> updateYanki(YankiDocumentDto yankiDocument);
}
