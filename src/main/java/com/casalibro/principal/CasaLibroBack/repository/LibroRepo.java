package com.casalibro.principal.CasaLibroBack.repository;

import com.casalibro.principal.CasaLibroBack.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibroRepo extends JpaRepository<Libro, Integer> {

    public Optional<Libro> findByNombre(String name);
}
