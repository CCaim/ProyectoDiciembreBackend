package com.casalibro.principal.CasaLibroBack.model;

import javax.persistence.Column;

import java.io.Serializable;
import java.util.Objects;



public class LibroGenerosId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "libro_id")
    private Integer libroId;

    @Column(name = "genero_id")
    private Integer generoId;

    public LibroGenerosId() {
    }

    public LibroGenerosId(Integer libroId, Integer generoId) {
        this.libroId = libroId;
        this.generoId = generoId;
    }

    @Override
    public boolean equals(Object o){
        if(o==null || getClass() != o.getClass() ) {
            return false;
        }
        LibroGenerosId lgi = (LibroGenerosId) o;
        return Objects.equals(this.generoId, lgi.generoId)&&Objects.equals(this.libroId, lgi.libroId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libroId, generoId);
    }

    public Integer getLibroId() {
        return libroId;
    }

    public void setLibroId(Integer libroId) {
        this.libroId = libroId;
    }

    public Integer getGeneroId() {
        return generoId;
    }

    public void setGeneroId(Integer generoId) {
        this.generoId = generoId;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
