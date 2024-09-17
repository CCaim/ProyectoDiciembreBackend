package com.casalibro.principal.CasaLibroBack.model;
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

    /*
    Aqui hay que poner los many to many y many to one cuando el resto de clases esten generadas
     */

    public Comentario(){

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
}
