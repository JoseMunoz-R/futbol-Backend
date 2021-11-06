package com.backend.futbol.repositories;

import com.backend.futbol.models.EquipoModel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository extends MongoRepository<EquipoModel, String> {
    
}
