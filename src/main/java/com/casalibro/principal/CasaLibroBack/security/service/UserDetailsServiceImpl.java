package com.casalibro.principal.CasaLibroBack.security.service;

import com.casalibro.principal.CasaLibroBack.security.model.Usuario;
import com.casalibro.principal.CasaLibroBack.security.model.UsuarioPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
     UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Usuario usuario = usuarioService.getByUsername(username).get();
        return UsuarioPrincipal.build(usuario);
    }
}
