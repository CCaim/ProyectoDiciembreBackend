package com.casalibro.principal.CasaLibroBack.security.repository;

import com.casalibro.principal.CasaLibroBack.security.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {

    public Optional<Usuario> findByUsername(String name);
    boolean existsByUsername(String name);
    boolean existsByEmail(String email);
}
