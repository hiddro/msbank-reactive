package com.bootcamp.retire.msyanki.repositories;

import com.bootcamp.retire.msyanki.documents.entities.Retire;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RetireRepository extends ReactiveMongoRepository<Retire, String> {
}
