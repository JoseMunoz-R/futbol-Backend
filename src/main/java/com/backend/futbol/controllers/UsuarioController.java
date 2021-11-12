package com.backend.futbol.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.backend.futbol.models.UsuarioModel;
import com.backend.futbol.services.UsuarioService;
import com.backend.futbol.utils.Autorizacion;
import com.backend.futbol.utils.BCrypt;
import com.backend.futbol.exceptions.CustomExpeption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api")
public class UsuarioController {
    
    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/usuarios")
    public ResponseEntity<Map<String, String>> guardarUsuario(@RequestBody UsuarioModel usuario, Errors error){

        if(error.hasErrors()){
            throwError(error);
        }
        Map<String, String> respuesta = new HashMap<>();
        usuario.setPassword(BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt()));

        UsuarioModel u = usuarioService.buscarPorUsername(usuario.getUsername());

        if(u.getId()==null){
            usuarioService.guardarUsuario(usuario);
            respuesta.put("mensaje", "Se registró el usuario correctamente");
        }else{
            respuesta.put("mensaje", "El usuario ya se encuentra registrado");
        }
        return ResponseEntity.ok(respuesta);
        
    }

    @PostMapping("/usuarios/login")
    public ResponseEntity<UsuarioModel> login(@RequestBody UsuarioModel usuario){
        UsuarioModel u= usuarioService.buscarPorUsername(usuario.getUsername());
        if(u.getNombre()== null){
            throw new CustomExpeption("Usuario o contraseña incorrectos");
        }
        if(!BCrypt.checkpw(usuario.getPassword(), u.getPassword())){
            throw new CustomExpeption("Usuario o contraseña incorrectos");
        }
        String hash="";
        long tiempo= System.currentTimeMillis();

        if(u.getId()!=""){
            hash=Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, Autorizacion.KEY)
            .setSubject(u.getNombre())
            .setIssuedAt(new Date (tiempo))
            .setExpiration(new Date (tiempo+9000000))
            .claim("username", u.getUsername())
            .claim("correo", u.getCorreo())
            .compact();
        }
        u.setHash(hash);
        return ResponseEntity.ok(u);
    }

    private void throwError(Errors error) {
        String message="";
        int index=0;
        for(ObjectError e: error.getAllErrors()){
            if(index>0){
                message += " | ";
            }
            message += String.format("Parametro: %s - Mensaje: %s", e.getObjectName(),e.getDefaultMessage());
        }
        throw new CustomExpeption(message);
    }
}
