package com.casalibro.principal.CasaLibroBack.model;

import com.casalibro.principal.CasaLibroBack.security.model.Usuario;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Libro")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "valoracion")
    private float valoracion;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "urlImagen")
    private String urlImagen;

    @ManyToOne
    @JoinColumn(name = "id_usuario",nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "libro", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Comentario> comentarios;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LibroGeneros> generos;

    public Libro(Set<LibroGeneros> generos, Set<Comentario> comentarios, Usuario usuario, String urlImagen, String tipo, Date fecha, float valoracion, String nombre) {
        this.generos = generos;
        this.comentarios = comentarios;
        this.usuario = usuario;
        this.urlImagen = urlImagen;
        this.tipo = tipo;
        this.fecha = fecha;
        this.valoracion = valoracion;
        this.nombre = nombre;
    }

    public Libro() {
        this.fecha = new Date();
        this.valoracion = 0f;
        this.usuario = new Usuario();
        this.comentarios = new HashSet<Comentario>();
        this.generos = new HashSet<LibroGeneros>();

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

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
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
}
