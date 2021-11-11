package com.backend.futbol.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "partidos")
public class PartidoModel {
    @Id
    private String id;
    private UsuarioModel usuario;
    private EquipoModel local;
    private EquipoModel visitante;
    private String fecha;
    private int golesVisitante;
    private int golesLocal;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public UsuarioModel getUsuario() {
        return usuario;
    }
    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }
    public EquipoModel getLocal() {
        return local;
    }
    public void setLocal(EquipoModel local) {
        this.local = local;
    }
    public EquipoModel getVisitante() {
        return visitante;
    }
    public void setVisitante(EquipoModel visitante) {
        this.visitante = visitante;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public int getGolesVisitante() {
        return golesVisitante;
    }
    public void setGolesVisitante(int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }
    public int getGolesLocal() {
        return golesLocal;
    }
    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    
}
