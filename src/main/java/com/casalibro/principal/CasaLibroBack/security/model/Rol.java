package com.casalibro.principal.CasaLibroBack.security.model;

import com.casalibro.principal.CasaLibroBack.security.enums.RolNombre;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private RolNombre nombre;

    public Rol() {}

    public Rol(RolNombre nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RolNombre getNombre() {
        return nombre;
    }

    public void setNombre(RolNombre nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "ROLE_" + nombre.name(); // Necesario para Spring Security (Ej: ROLE_ADMIN)
    }
}
