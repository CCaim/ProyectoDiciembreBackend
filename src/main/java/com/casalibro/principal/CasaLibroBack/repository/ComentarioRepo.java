package com.casalibro.principal.CasaLibroBack.repository;

import com.casalibro.principal.CasaLibroBack.model.Comentario;
import com.casalibro.principal.CasaLibroBack.security.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ComentarioRepo extends JpaRepository<Comentario, Integer> {

    public Optional<Comentario> findByUsuario(Usuario usuario);

}
