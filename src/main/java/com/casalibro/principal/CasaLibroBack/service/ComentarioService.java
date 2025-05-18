package com.casalibro.principal.CasaLibroBack.service;

import com.casalibro.principal.CasaLibroBack.model.Comentario;
import com.casalibro.principal.CasaLibroBack.security.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ComentarioService {

    public Comentario insertarComentario(Comentario comen);
    public Comentario actualizarComentario(Comentario comenU, Integer idCA);
    public List<Comentario> listarComentarios();
    public Comentario obtenerComentarioPorId(Integer id);
    public List<Comentario> obtenerComentariosPorUsuario(Usuario usu);
    public void eliminarComentario(Comentario comen);
    public void eliminarComentarioPorId(Integer id);

}
