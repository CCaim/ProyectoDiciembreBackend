package com.casalibro.principal.CasaLibroBack.service;

import com.casalibro.principal.CasaLibroBack.model.Genero;

import java.util.List;



public interface GeneroService {

    public Genero insertarGenero(Genero gene);
    public Genero actualizarGenero(Genero generoUpdate, Integer idIA);
    public List<Genero> listarGeneros();
    public Genero obtenerGeneroPorId(Integer id);
    public Genero obtenerGeneroPorNombre(String nombreGenero);
    public void eliminarGenero(Genero gene);
    public void eliminarGeneroPorId(Integer id);

}
