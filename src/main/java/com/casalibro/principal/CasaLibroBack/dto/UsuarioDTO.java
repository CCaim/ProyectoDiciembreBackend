package com.casalibro.principal.CasaLibroBack.dto;

import com.casalibro.principal.CasaLibroBack.security.model.Rol;

import java.util.HashSet;
import java.util.Set;

public class UsuarioDTO {
    private String username;

    private String email;

    private String password;

    private Set<Rol> roles = new HashSet<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

}
