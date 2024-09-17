package com.casalibro.principal.CasaLibroBack.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "libros_generos")
public class LibroGeneros {

    @EmbeddedId
    private LibroGenerosId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("libro_id")
    @JsonIgnore
    private Libro libro;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("genero_id")
    private  Genero genero;

    private String nombre;

    public LibroGeneros() {
    }

    public LibroGeneros( Libro l, Genero g, String n) {
        this.libro = l;
        this.genero = g;
        this.nombre = n;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LibroGeneros lu = (LibroGeneros) o;
        return Objects.equals(this.genero, lu.genero) && Objects.equals(this.libro, lu.libro);
    }

    @Override
    public int hashCode(){
        return Objects.hash(libro, genero);
    }

    public LibroGenerosId getId() {
        return id;
    }

    public void setId(LibroGenerosId id) {
        this.id = id;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
