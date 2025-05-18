package com.casalibro.principal.CasaLibroBack.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "generos")
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany( mappedBy = "genero", cascade = CascadeType.MERGE, orphanRemoval = true)
    @JsonIgnore
    private Set<LibroGeneros> libros;

    public Genero() {
        this.libros = libros;
    }

    public Genero(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<LibroGeneros> getLibros() {
        return libros;
    }

    public void setLibros(Set<LibroGeneros> libros) {
        this.libros = libros;
    }
}
