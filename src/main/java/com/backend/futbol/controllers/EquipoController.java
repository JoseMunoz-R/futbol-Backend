package com.backend.futbol.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.backend.futbol.models.EquipoModel;
import com.backend.futbol.services.EquipoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EquipoController {

    @Autowired
    EquipoService equipoService;

    @PostMapping("/equipos")
    public ResponseEntity<Map<String, String>> guardar(@Valid @RequestBody EquipoModel equipo, Errors error) {
        if (error.hasErrors()) {
            throwErrors(error);
        }

        equipoService.guardar(equipo);
        Map<String, String> response = new HashMap<>();
        response.put("Mensaje", "El equipo se agregó correctamente");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/equipos")
    public List<EquipoModel> obtenerEquipos() {
        return equipoService.obtenerEquipos();
    }

    @GetMapping("/equipos/{id}")
    public EquipoModel buscarPorId(@PathVariable String id) {
        return equipoService.buscarPorId(id).get();
    }

    public void throwErrors(Errors error) {
        String message = "";
        int index = 0;

        for (ObjectError e : error.getAllErrors()) {
            if (index > 0) {
                message += " | ";
            }

            message += String.format("Parametro: %s - Mensaje: %s", e.getObjectName(), e.getDefaultMessage());

        }
    }
}
