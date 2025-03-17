package com.casalibro.principal.CasaLibroBack.security.service;

import com.casalibro.principal.CasaLibroBack.security.model.Usuario;
import com.casalibro.principal.CasaLibroBack.security.model.UsuarioPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioService usuarioService;

    @Autowired
    public UserDetailsServiceImpl(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscar usuario en la base de datos
        Usuario usuario = usuarioService.getByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // Convertir Usuario a UsuarioPrincipal para que implemente UserDetails
        return UsuarioPrincipal.build(usuario);
    }
}
