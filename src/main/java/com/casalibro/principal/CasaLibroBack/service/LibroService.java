package com.casalibro.principal.CasaLibroBack.service;

import com.casalibro.principal.CasaLibroBack.model.Libro;

import java.util.List;

public interface LibroService {

    public Libro insertarLibro(Libro libro);
    public Libro actualizarLibro(Libro libroUpd, Integer idLA);
    public List<Libro> listarLibro();
    public Libro obtenerLibroPorId(Integer id);
    public Libro obtenerLibroPorNombre(String nombrelibro);
    public void eliminarLibro(Libro libro);
    public void eliminarLibroPorId(Integer id);
}