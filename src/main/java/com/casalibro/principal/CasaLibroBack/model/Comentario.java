package com.casalibro.principal.CasaLibroBack.model;
import com.casalibro.principal.CasaLibroBack.security.model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table (name = "Comentario")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "mensaje")
    private String mensaje;

    @ManyToOne
    @JoinColumn(name = "id_libro", nullable = false)
    @JsonIgnore
    private Libro libro;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    public Comentario(){

    }

    public Comentario(String mensaje, Libro libro, Usuario usuario) {
        this.mensaje = mensaje;
        this.libro = libro;
        this.usuario = usuario;
    }

    public Comentario(String mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
