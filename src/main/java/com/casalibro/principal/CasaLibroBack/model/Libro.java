package com.casalibro.principal.CasaLibroBack.model;

import com.casalibro.principal.CasaLibroBack.security.model.Usuario;
import javax.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "instrucciones")
    private String instrucciones;

    @Column(name = "urlImagen")
    private String urlImagen;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy="libro", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE )
    private Set<Comentario> comentarios;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LibroGeneros> generos;

    public Libro() {
        this.fecha = new Date();
        this.usuario = new Usuario();
        this.comentarios = new HashSet<Comentario>();
        this.generos = new HashSet<LibroGeneros>();
    }

    public Libro(String nombre, String tipo, String instrucciones, String urlImg) {
        this.nombre = nombre;
        this.fecha = new Date();
        this.tipo = tipo;
        this.instrucciones = instrucciones;
        this.urlImagen = urlImagen;
        this.usuario = new Usuario();
        this.comentarios = new HashSet<Comentario>();
        this.generos = new HashSet<LibroGeneros>();
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Set<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Set<LibroGeneros> getGeneros() {
        return generos;
    }

    public void setGeneros(Set<LibroGeneros> generos) {
        this.generos = generos;
    }

    public void addGenero(Genero gene, int cantidad) {
        LibroGeneros ri = new LibroGeneros(this, gene, cantidad);
        if (this.generos.contains(ri)) {
            this.generos.remove(ri);
        }
        if (cantidad != 0) {
            this.generos.add(ri);
            gene.getLibros().add(ri);
        }
    }

    public void removeGenero(Genero gene) {
        for (LibroGeneros ri : this.generos) {
            if (ri.getGenero().equals(gene)) {
                this.generos.remove(ri);
            }
        }
        for (LibroGeneros ri : gene.getLibros()) {
            if (ri.getLibro().equals(this)) {
                gene.getLibros().remove(ri);
            }
        }
    }

    public void removeGeneros() {
        this.generos.removeAll(this.generos);
    }

}
