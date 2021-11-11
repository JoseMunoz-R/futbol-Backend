package com.backend.futbol.services;

import java.util.List;

import com.backend.futbol.models.PartidoModel;
import com.backend.futbol.repositories.PartidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartidoService {
    
    @Autowired
    PartidoRepository partidoRepository;

    public void registrarPartido(PartidoModel partido){
        partidoRepository.save(partido);
    }

    public List<PartidoModel> traerPartidos(){
        return partidoRepository.findAll();
    }
}
