package com.casalibro.principal.CasaLibroBack.controller;

import com.casalibro.principal.CasaLibroBack.dto.GeneroDTO;
import com.casalibro.principal.CasaLibroBack.model.Genero;
import com.casalibro.principal.CasaLibroBack.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<?> insertarGenero(@RequestBody GeneroDTO generoNuevoDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Genero generoNuevo = new Genero(generoNuevoDTO.getNombre(), generoNuevoDTO.getTipo());
        generoService.insertarGenero(generoNuevo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> actualizarGenero(@PathVariable(name = "id") Integer idAntiguo, @RequestBody GeneroDTO generoActualizado, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Genero generoAntiguo = generoService.obtenerGeneroPorId(idAntiguo);
        generoAntiguo.setNombre(generoActualizado.getNombre());
        generoAntiguo.setTipo(generoActualizado.getTipo());

        generoService.insertarGenero(generoAntiguo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @RequestMapping(value = "/{remove}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> eliminarGeneroPorID(@PathVariable(name = "id") Integer id){
        try {
            generoService.eliminarGeneroPorId(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
