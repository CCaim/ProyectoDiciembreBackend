package com.casalibro.principal.CasaLibroBack.controller;

import com.casalibro.principal.CasaLibroBack.dto.LibroDTO;
import com.casalibro.principal.CasaLibroBack.dto.LibroEditDTO;
import com.casalibro.principal.CasaLibroBack.model.Genero;
import com.casalibro.principal.CasaLibroBack.model.Libro;
import com.casalibro.principal.CasaLibroBack.repository.GeneroRepo;
import com.casalibro.principal.CasaLibroBack.security.model.Usuario;
import com.casalibro.principal.CasaLibroBack.service.LibroService;
import jakarta.servlet.http.HttpServletResponse;
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
    private HttpServletResponse httpServletResponse;

    private void addCORSHeaders(){
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Autorization");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
    }

    @Autowired
    private LibroService libroService;
    @Autowired
    private GeneroRepo generoRepo;

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
        Libro libroNuevo = new Libro(libroDTO.getNombre(),libroDTO.getTipo(), libroDTO.getUrlImagen());
        Usuario usuarioLibro = libroDTO.getUsuario();

        usuarioLibro.getLibros().add(libroNuevo);
        libroNuevo.setUsuario(usuarioLibro);
        libroService.insertarLibro(libroNuevo);
        for (int i = 0; i < libroDTO.getGeneros().size(); i++){
            Genero genero = libroDTO.getGeneros().get(i).getGenero();
            int cantidad = libroDTO.getGeneros().get(i).getCantidad();

            libroNuevo.addGenero(genero, cantidad);
        }
        libroService.insertarLibro(libroNuevo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> actualizarLibro(@PathVariable(name = "id") Integer idLibroAntiguo, @RequestBody LibroEditDTO libroEditDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Libro libroAntiguo = libroService.obtenerLibroPorId(idLibroAntiguo);
        libroAntiguo.removeGeneros();
        libroAntiguo.setNombre(libroEditDTO.getNombre());
        libroAntiguo.setFecha(new Date());
        libroAntiguo.setTipo(libroEditDTO.getTipo());
        libroAntiguo.setValoracion(libroEditDTO.getValoracion());
        libroAntiguo.setUrlImagen(libroEditDTO.getUrlImagen());
        libroAntiguo.setComentarios(libroEditDTO.getComentarios());

        libroService.insertarLibro(libroAntiguo);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
    @RequestMapping(value="/remove/{id}", method = RequestMethod.DELETE)

    public ResponseEntity<HttpStatus> eliminarRecetaPorID(@PathVariable(name = "id") Integer id){
        try{
            libroService.eliminarLibroPorId(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
