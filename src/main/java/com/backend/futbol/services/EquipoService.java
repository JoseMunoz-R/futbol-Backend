package com.backend.futbol.services;

import java.util.List;
import java.util.Optional;

import com.backend.futbol.models.EquipoModel;
import com.backend.futbol.repositories.EquipoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipoService {
    
    @Autowired
    EquipoRepository equipoRepository;

    public void guardar(EquipoModel equipo){
        this.equipoRepository.save(equipo);
    }

    public List<EquipoModel> obtenerEquipos(){
        return equipoRepository.findAll();
    }

    public Boolean existById(String id){
        return equipoRepository.existsById(id);
    }

    public Optional<EquipoModel> buscarPorId(String id){
        return equipoRepository.findById(id);
    }

    public void eliminar(String id){
        equipoRepository.deleteById(id);
    }
}
