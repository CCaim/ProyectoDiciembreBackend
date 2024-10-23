package com.casalibro.principal.CasaLibroBack.controller;

import com.casalibro.principal.CasaLibroBack.model.Comentario;
import com.casalibro.principal.CasaLibroBack.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentario")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/getAll", method = RequestMethod.GET)
    public List<Comentario> listadoRecetas(){
        return comentarioService.listarComentarios();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Comentario obtenerComentarioPorID(@PathVariable(name = "id") Integer id){
        return comentarioService.obtenerComentarioPorId(id);
    }
}
