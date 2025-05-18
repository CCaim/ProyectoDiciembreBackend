package com.casalibro.principal.CasaLibroBack.service.impl;

import com.casalibro.principal.CasaLibroBack.model.Libro;
import com.casalibro.principal.CasaLibroBack.repository.LibroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService implements com.casalibro.principal.CasaLibroBack.service.LibroService{

    @Autowired
    LibroRepo libroRepo;

    @Override
    public Libro insertarLibro(Libro libro) {
        return libroRepo.save(libro);
    }

    @Override
    public List<Libro> listarLibro() {
        return libroRepo.findAll();
    }

    @Override
    public Libro obtenerLibroPorId(Integer id) {
        return libroRepo.findById(id).get();
    }

    @Override
    public Libro obtenerLibroPorNombre(String nombreLibro) {
        return libroRepo.findByNombre(nombreLibro).get();
    }

    @Override
    public void eliminarLibro(Libro libro) {
        libroRepo.delete(libro);
    }

    @Override
    public void eliminarLibroPorId(Integer id) {
        libroRepo.delete(libroRepo.findById(id).get());
    }

    @Override
    public Libro actualizarLibro(Libro libroUpdate, Integer idRA) {
        Libro libroAntiguo = obtenerLibroPorId(idRA);
        return insertarLibro(libroAntiguo);
    }

}
