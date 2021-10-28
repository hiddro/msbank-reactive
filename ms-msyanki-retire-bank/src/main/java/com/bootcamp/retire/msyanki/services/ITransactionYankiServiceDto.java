package com.bootcamp.retire.msyanki.services;

import com.bootcamp.retire.msyanki.documents.dto.TransactionYankiDto;
import reactor.core.publisher.Mono;

public interface ITransactionYankiServiceDto {

    Mono<TransactionYankiDto> saveTransactionYanki(TransactionYankiDto transaction);
}
