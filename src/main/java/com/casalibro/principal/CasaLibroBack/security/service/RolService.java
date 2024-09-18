package com.casalibro.principal.CasaLibroBack.security.service;

import com.casalibro.principal.CasaLibroBack.security.enums.RolNombre;
import com.casalibro.principal.CasaLibroBack.security.model.Rol;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepo rolRepo;


    public List<Rol> listarRoles() {
        return rolRepo.findAll();
    }

    public Optional<Rol> getByNombre(RolNombre rolNombre){
        return rolRepo.findByNombre(rolNombre);
    }

    public void save(Rol rol) {
        rolRepo.save(rol);
    }
}
