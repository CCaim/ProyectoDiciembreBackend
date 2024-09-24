package com.casalibro.principal.CasaLibroBack.controller;

import com.casalibro.principal.CasaLibroBack.dto.LibroDTO;
import com.casalibro.principal.CasaLibroBack.model.Libro;
import com.casalibro.principal.CasaLibroBack.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libro")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Libro> listadoLibros(){
        return libroService.listarLibro();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Libro obtenerLibroPorId(@PathVariable(name = "id") Integer id){
        return libroService.obtenerLibroPorId(id);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<?> insertarLibro(@RequestBody LibroDTO libroDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Libro libroNuevo = new Libro(libroDTO.getNombre(),libroDTO.getUrlImagen());
        Libro usuarioLibro = libroDTO.get
    }
}
