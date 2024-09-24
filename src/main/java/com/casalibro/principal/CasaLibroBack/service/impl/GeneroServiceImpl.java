package com.casalibro.principal.CasaLibroBack.service.impl;

import com.casalibro.principal.CasaLibroBack.model.Genero;
import com.casalibro.principal.CasaLibroBack.repository.GeneroRepo;
import com.casalibro.principal.CasaLibroBack.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroServiceImpl implements GeneroService {

    @Autowired
    GeneroRepo generoRepo;

    @Override
    public Genero insertarGenero(Genero genero){
        return generoRepo.save(genero);
    }

    @Override
    public List<Genero> listarGeneros(){
        return generoRepo.findAll();
    }

    @Override
    public Genero obtenerGeneroPorId(Integer id){
        return generoRepo.findById(id).get();
    }

    @Override
    public Genero obtenerGeneroPorNombre(String nombreGenero){
        return generoRepo.findByNombre(nombreGenero).get();
    }

    @Override
    public void eliminarGenero(Genero genero){
        generoRepo.delete(genero);
    }

    @Override
    public void eliminarGeneroPorId(Integer id){
        generoRepo.delete(generoRepo.findById(id).get());
    }

    @Override
    public Genero actualizarGenero(Genero GeneroUpdate, Integer idGU){
        Genero generoAntiguo = obtenerGeneroPorId(idGU);
        return insertarGenero(generoAntiguo);
    }
}
