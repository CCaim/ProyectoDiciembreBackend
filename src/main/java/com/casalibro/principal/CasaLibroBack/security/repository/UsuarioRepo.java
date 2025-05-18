package com.casalibro.principal.CasaLibroBack.security.repository;

import com.casalibro.principal.CasaLibroBack.security.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {

    public Optional<Usuario> findByUsername(String name);
    boolean existsByUsername(String name);
    boolean existsByEmail(String email);
}
