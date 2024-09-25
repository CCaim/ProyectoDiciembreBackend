package com.casalibro.principal.CasaLibroBack.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Genero")
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "tipo")
    private String tipo;

    @OneToMany(mappedBy = "genero", cascade = CascadeType.MERGE, orphanRemoval = true)
    @JsonIgnore
    private Set<LibroGeneros> libros;

    public Genero(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.libros = new HashSet<LibroGeneros>();
    }

    public Genero() {
        this.libros = new HashSet<LibroGeneros>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Set<LibroGeneros> getLibros() {
        return libros;
    }

    public void setLibros(Set<LibroGeneros> libros) {
        this.libros = libros;
    }
}
