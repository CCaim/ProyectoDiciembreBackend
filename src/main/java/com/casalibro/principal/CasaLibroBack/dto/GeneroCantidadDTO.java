package com.casalibro.principal.CasaLibroBack.dto;

import com.casalibro.principal.CasaLibroBack.model.Genero;

public class GeneroCantidadDTO {

    private Genero genero;

    private int cantidad;

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
