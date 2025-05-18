package com.casalibro.principal.CasaLibroBack.service.impl;

import com.casalibro.principal.CasaLibroBack.model.Libro;
import com.casalibro.principal.CasaLibroBack.repository.LibroRepo;
import com.casalibro.principal.CasaLibroBack.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    LibroRepo libroRepo;

    @Override
    public Libro insertarLibro(Libro libro){
        return libroRepo.save(libro);
    }

    @Override
    public Libro actualizarLibro(Libro libroUpd, Integer idLA) {
        Libro libroAntiguo = obtenerLibroPorId(idLA);
        return insertarLibro(libroAntiguo);
    }

    @Override
    public List<Libro> listarLibro(){
        return libroRepo.findAll();
    }

    @Override
    public Libro obtenerLibroPorId(Integer id) {
        return libroRepo.findById(id).get();
    }

    @Override
    public Libro obtenerLibroPorNombre(String nombrelibro) {
        return libroRepo.findByNombre(nombrelibro).get();
    }

    @Override
    public void eliminarLibro(Libro libro) {
        libroRepo.delete(libro);
    }

    @Override
    public void eliminarLibroPorId(Integer id) {
        libroRepo.delete(libroRepo.findById(id).get());
    }
}

