package com.bootcamp.msyanki.services;

import com.bootcamp.msyanki.documents.entities.YankiDocument;
import reactor.core.publisher.Mono;

public interface IYankiService extends ICrudService<YankiDocument, String>{

    Mono<YankiDocument> validateFields(YankiDocument accountYankie);

    Mono<YankiDocument> findByCustomerIdentityNumber(String customerIdentityNumber);

    Mono<YankiDocument> findByNroPhone(String nroPhone);

    Mono<YankiDocument> findByImeiPhone(String imeiPhone);

    Mono<YankiDocument> findByEmail(String email);
}
