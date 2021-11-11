package com.backend.futbol.repositories;

import com.backend.futbol.models.PartidoModel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidoRepository extends MongoRepository<PartidoModel, String>{
    
}
