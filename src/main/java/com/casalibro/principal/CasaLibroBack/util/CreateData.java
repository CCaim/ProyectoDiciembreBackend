package com.casalibro.principal.CasaLibroBack.util;

import com.casalibro.principal.CasaLibroBack.security.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class CreateData implements CommandLineRunner {

    @Autowired
    RolService rolService;

    @Override
    public void run(String... args) throws Exception{
        /**Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
         Rol rolUser = new Rol(RolNombre.ROLE_USER);
         Rol rolBan = new Rol(RolNombre.ROLE_BAN);
         rolService.save(rolAdmin);
         rolService.save(rolUser);
         rolService.save(rolBan);*/
    }
}
