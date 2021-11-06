package com.backend.futbol.services;

import com.backend.futbol.models.UsuarioModel;
import com.backend.futbol.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public void guardarUsuario(UsuarioModel usuario){
        usuarioRepository.save(usuario);
    }

    public UsuarioModel buscarPorUsername(String username){
        return usuarioRepository.findByUsername(username).orElse(new UsuarioModel());
    }
}
