package com.casalibro.principal.CasaLibroBack.controller;

import com.casalibro.principal.CasaLibroBack.dto.LibroDTO;
import com.casalibro.principal.CasaLibroBack.dto.LibroEditDTO;
import com.casalibro.principal.CasaLibroBack.model.Genero;
import com.casalibro.principal.CasaLibroBack.model.Libro;
import com.casalibro.principal.CasaLibroBack.security.model.Usuario;
import com.casalibro.principal.CasaLibroBack.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/libro")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @RequestMapping(value="/getAll", method = RequestMethod.GET)
    public List<Libro> listadoLibros(){
        return libroService.listarLibro();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Libro obtenerLibroPorID(@PathVariable(name = "id") Integer id){
        return libroService.obtenerLibroPorId(id);
    }

    @RequestMapping(value="/new", method = RequestMethod.POST)
    public ResponseEntity<?> insertarLibro(@RequestBody LibroDTO libDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Libro libroNuevo = new Libro(libDTO.getNombre(), libDTO.getInstrucciones(), libDTO.getTipo(), libDTO.getUrlImagen());
        Usuario usuarioLibro = libDTO.getUsuario();
        usuarioLibro.getLibros().add(libroNuevo);
        libroNuevo.setUsuario(usuarioLibro);
        libroService.insertarLibro(libroNuevo);
        for (int i = 0; i < libDTO.getGeneros().size(); i++) {
            Genero gene = libDTO.getGeneros().get(i).getGenero();
            int cantidad = libDTO.getGeneros().get(i).getCantidad();
            libroNuevo.addGenero(gene, cantidad);
        }
        libroService.insertarLibro(libroNuevo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value="/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> actualizarLibro(@PathVariable(name = "id") Integer idLibroAntiguo, @RequestBody LibroEditDTO libroEditDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Libro libroAntiguo = libroService.obtenerLibroPorId(idLibroAntiguo);
        libroAntiguo.removeGeneros();
        libroAntiguo.setNombre(libroEditDTO.getNombre());
        libroAntiguo.setFecha(new Date());
        libroAntiguo.setInstrucciones(libroEditDTO.getInstrucciones());
        libroAntiguo.setTipo(libroEditDTO.getTipo());
        libroAntiguo.setUrlImagen(libroEditDTO.getUrlImagen());
        libroAntiguo.setComentarios(libroEditDTO.getComentarios());

        for (int i = 0; i < libroEditDTO.getGeneros().size(); i++) {
            Genero gene = libroEditDTO.getGeneros().get(i).getGenero();
            int cantidad = libroEditDTO.getGeneros().get(i).getCantidad();
            libroAntiguo.addGenero(gene, cantidad);
        }

        libroService.insertarLibro(libroAntiguo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value="/remove/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> eliminarLibroPorID(@PathVariable(name = "id") Integer id){
        try{
            libroService.eliminarLibroPorId(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
