package com.casalibro.principal.CasaLibroBack.repository;

import com.casalibro.principal.CasaLibroBack.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GeneroRepo extends JpaRepository<Genero, Integer> {

    public Optional<Genero> findByNombre(String name);
}
