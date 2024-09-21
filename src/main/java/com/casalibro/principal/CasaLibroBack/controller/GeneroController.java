package com.casalibro.principal.CasaLibroBack.controller;

import com.casalibro.principal.CasaLibroBack.model.Genero;
import com.casalibro.principal.CasaLibroBack.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genero")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Genero> listadoGeneros(){
        return generoService.listarGeneros();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Genero obtenerGeneroPorId(@PathVariable(name = "id") Integer id){
        return generoService.obtenerGeneroPorId(id);
    }


}
