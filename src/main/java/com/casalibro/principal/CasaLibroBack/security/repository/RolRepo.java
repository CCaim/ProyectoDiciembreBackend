package com.casalibro.principal.CasaLibroBack.security.repository;

import com.casalibro.principal.CasaLibroBack.security.enums.RolNombre;
import com.casalibro.principal.CasaLibroBack.security.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepo extends JpaRepository<Rol, Integer> {

    public Optional<Rol> findByNombre(RolNombre name);
}
