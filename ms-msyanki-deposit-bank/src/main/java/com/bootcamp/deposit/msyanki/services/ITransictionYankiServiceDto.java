package com.bootcamp.deposit.msyanki.services;

import com.bootcamp.deposit.msyanki.documents.dto.TransactionYankiDto;
import reactor.core.publisher.Mono;

public interface ITransictionYankiServiceDto {

    Mono<TransactionYankiDto> saveTransactionYanki(TransactionYankiDto transaction);
}
