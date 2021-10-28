package com.bootcamp.msTransfer.repositories;

import com.bootcamp.msTransfer.models.entities.Transfer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * The interface Transfer repository.
 */
public interface TransferRepository extends ReactiveMongoRepository<Transfer,String> {
}
