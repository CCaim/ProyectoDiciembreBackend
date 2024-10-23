package com.casalibro.principal.CasaLibroBack.controller;

import com.casalibro.principal.CasaLibroBack.model.Comentario;
import com.casalibro.principal.CasaLibroBack.service.ComentarioService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentario")
public class ComentarioController {

    @Autowired
    private HttpServletResponse httpServletResponse;

    private void addCORSHeaders(){
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Autorization");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
    }

    @Autowired
    private ComentarioService comentarioService;

    @RequestMapping(value="/getAll", method = RequestMethod.GET)
    public List<Comentario> listadoRecetas(){
        return comentarioService.listarComentarios();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Comentario obtenerComentarioPorID(@PathVariable(name = "id") Integer id){
        return comentarioService.obtenerComentarioPorId(id);
    }
}
