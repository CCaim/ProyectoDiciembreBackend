package com.casalibro.principal.CasaLibroBack.security.service;

import com.casalibro.principal.CasaLibroBack.security.model.Usuario;
import com.casalibro.principal.CasaLibroBack.security.model.UsuarioPrincipal;
import com.casalibro.principal.CasaLibroBack.security.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioRepo usuarioRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con nombre: " + username));
        return UsuarioPrincipal.build(usuario);
    }
}