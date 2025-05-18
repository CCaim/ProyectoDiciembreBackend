package com.casalibro.principal.CasaLibroBack.controller;

import com.casalibro.principal.CasaLibroBack.model.Comentario;
import com.casalibro.principal.CasaLibroBack.security.service.UsuarioService;
import com.casalibro.principal.CasaLibroBack.service.ComentarioService;
import com.casalibro.principal.CasaLibroBack.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/comentario")
public class ComentarioController {

    @Autowired
    private ComentarioService comenService;

    @Autowired
    private LibroService libroService;

    @Autowired
    private UsuarioService usuService;

    @RequestMapping(value="/getAll", method = RequestMethod.GET)
    public List<Comentario> listadoRecetas(){
        return comenService.listarComentarios();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)

    public Comentario obtenerComentarioPorID(@PathVariable(name = "id") Integer id){
        return comenService.obtenerComentarioPorId(id);
    }

//	@RequestMapping(value="/new", method = RequestMethod.POST)
//	@ApiResponses(value = {
//	        @ApiResponse(code = 200, message = "Se ha insertado el comentario")
//	})
//	public ResponseEntity<?> insertarComentario(@RequestBody ComentarioDTO comenDTO, BindingResult bindingResult){
//	    if(bindingResult.hasErrors())
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	    Libro recetaComentada = receService.obtenerLibroPorId(comenDTO.getIdLibro());
//	    Usuario usuarioComentador = usuService.obtenerUsuarioPorUsername(comenDTO.getUsername());
//		Comentario comenNuevo = new Comentario(comenDTO.getMensaje(), recetaComentada, usuarioComentador);
//
//		comenService.insertarComentario(comenNuevo);
//
//		return new ResponseEntity<>(HttpStatus.CREATED);
//	}

    @RequestMapping(value="/remove/{id}", method = RequestMethod.DELETE)

    public ResponseEntity<HttpStatus> eliminarComentarioPorID(@PathVariable(name = "id") Integer id){
        try{
            comenService.eliminarComentarioPorId(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
