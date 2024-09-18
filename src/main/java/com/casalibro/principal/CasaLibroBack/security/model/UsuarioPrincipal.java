package com.casalibro.principal.CasaLibroBack.security.model;

import com.casalibro.principal.CasaLibroBack.model.Comentario;
import com.casalibro.principal.CasaLibroBack.model.Libro;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class UsuarioPrincipal implements UserDetails {

    private String username;
    private String password;
    private String email;
    private Set<Libro> libros;
    private Set<Comentario> comentarios;
    private Collection<? extends GrantedAuthority> authorities;


    public static UsuarioPrincipal build(Usuario usuario){
        List<GrantedAuthority> authorities = usuario.getRoles().steam().map(
                rol -> new SimpleGrantedAuthority(rol.getNombre().name())
        );
        return new UsuarioPrincipal(usuario.getUsername(), usuario.getPassword(), usuario.getEmail(), usuario.getLibros(), usuario.getComentarios(), authorities);

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getEmail() {
        return email;
    }

    public Set<Libro> getLibros(){
        return libros;
    }

    public Set<Comentario> getComentarios() {
        return comentarios;
    }

}
