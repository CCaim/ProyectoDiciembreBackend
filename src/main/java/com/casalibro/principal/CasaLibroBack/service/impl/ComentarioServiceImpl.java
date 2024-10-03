package com.casalibro.principal.CasaLibroBack.service.impl;

import com.casalibro.principal.CasaLibroBack.model.Comentario;
import com.casalibro.principal.CasaLibroBack.repository.ComentarioRepo;
import com.casalibro.principal.CasaLibroBack.security.model.Usuario;
import com.casalibro.principal.CasaLibroBack.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    @Autowired
    ComentarioRepo comentarioRepo;

    @Override
    public Comentario insertarComentario(Comentario comen){
        return comentarioRepo.save(comen);
    }
    @Override
    public List<Comentario> listarComentarios() {
        return comentarioRepo.findAll();
    }

    @Override
    public Comentario obtenerComentarioPorId(Integer id) {
        return comentarioRepo.findById(id).get();
    }

    @Override
    public List<Comentario> obtenerComentariosPorUsuario(Usuario usu) {
        List<Comentario> listaComentarios = new ArrayList<Comentario>();
        for (Comentario comentario : listarComentarios()) {
            if(comentario.getUsuario().equals(usu)) {
                listaComentarios.add(comentario);
            };
        }
        return listaComentarios;
    }

    @Override
    public void eliminarComentario(Comentario comen) {
        comentarioRepo.delete(comen);
    }

    @Override
    public void eliminarComentarioPorId(Integer id) {
        comentarioRepo.delete(comentarioRepo.findById(id).get());
    }

    @Override
    public Comentario actualizarComentario(Comentario comenU, Integer idCA) {
        Comentario comenAntiguo = obtenerComentarioPorId(idCA);
        return insertarComentario(comenAntiguo);
    }
}
