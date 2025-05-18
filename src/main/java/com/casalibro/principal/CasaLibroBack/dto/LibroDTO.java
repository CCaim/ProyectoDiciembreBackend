package com.casalibro.principal.CasaLibroBack.dto;

import com.casalibro.principal.CasaLibroBack.security.model.Usuario;

import java.util.List;

public class LibroDTO {

    private String nombre;

    private String tipo;

    private String instrucciones;

    private String urlImagen;

    private Usuario usuario;

    private List<GeneroCantidadDTO> generos;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<GeneroCantidadDTO> getGeneros() {
        return generos;
    }

    public void setGenerosCantidad(List<GeneroCantidadDTO> generos) {
        this.generos = generos;
    }
}
