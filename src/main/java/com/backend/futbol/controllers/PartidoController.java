package com.backend.futbol.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.backend.futbol.exceptions.CustomExpeption;
import com.backend.futbol.models.PartidoModel;
import com.backend.futbol.services.PartidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PartidoController {
    @Autowired
    PartidoService partidoService;

    @PostMapping("/partidos")
    public ResponseEntity<Map<String, String>> guardar(@Valid @RequestBody PartidoModel partido, Errors error){


        if(error.hasErrors()){
            throwError(error);
        }

        partidoService.registrarPartido(partido);
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mennsaje", "Se agregó correctamente el partido");
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/partidos")
    public List<PartidoModel> traerPartidos(){
        return partidoService.traerPartidos();
    }

    @PutMapping("/partidos")
    public ResponseEntity<Map<String, String>> actualizarPartido(@RequestBody PartidoModel partido, Errors error){
        if(error.hasErrors()){
            throwError(error);
        }

        partidoService.registrarPartido(partido);
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Se actualizó correctamente");
        return ResponseEntity.ok(respuesta);
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
