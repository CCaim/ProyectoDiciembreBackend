package com.casalibro.principal.CasaLibroBack.service.impl;

import com.casalibro.principal.CasaLibroBack.model.Genero;
import com.casalibro.principal.CasaLibroBack.repository.GeneroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroService implements com.casalibro.principal.CasaLibroBack.service.GeneroService {

    @Autowired
    GeneroRepo generoRepo;

    @Override
    public Genero insertarGenero(Genero gene) {
        return generoRepo.save(gene);
    }

    @Override
    public List<Genero> listarGeneros() {
        return generoRepo.findAll();
    }

    @Override
    public Genero obtenerGeneroPorId(Integer id) {
        return null;
    }

    @Override
    public Genero obtenerGeneroPorNombre(String nombreGenero) {
        return generoRepo.findByNombre(nombreGenero).get();
    }

    @Override
    public void eliminarGenero(Genero gene) {
        generoRepo.delete(gene);
    }

    @Override
    public void eliminarGeneroPorId(Integer id) {
        generoRepo.delete(generoRepo.findById(id).get());
    }

    @Override
    public Genero actualizarGenero(Genero GeneroUpdate, Integer idIA) {
        Genero generoAntiguo = obtenerGeneroPorId(idIA);
        return insertarGenero(generoAntiguo);
    }

}
