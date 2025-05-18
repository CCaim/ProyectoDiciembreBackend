package com.casalibro.principal.CasaLibroBack.controller;


import com.casalibro.principal.CasaLibroBack.dto.UsuarioDTO;
import com.casalibro.principal.CasaLibroBack.security.model.Rol;
import com.casalibro.principal.CasaLibroBack.security.model.Usuario;
import com.casalibro.principal.CasaLibroBack.security.service.RolService;
import com.casalibro.principal.CasaLibroBack.security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuService;

    @Autowired
    private RolService rolService;

    @RequestMapping(value="/getAll", method = RequestMethod.GET)
    public List<Usuario> listadoUsuarios(){
        return usuService.listarUsuarios();
    }

    @RequestMapping(value="/getRoles", method = RequestMethod.GET)
    public List<Rol> listadoRoles(){
        return rolService.listarRoles();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Usuario obtenerUsuarioPorID(@PathVariable(name = "id") Integer id){
        return usuService.obtenerUsuarioPorId(id);
    }

    @RequestMapping(value="/username/{username}", method = RequestMethod.GET)
    public Usuario obtenerUsuarioPorUsername(@PathVariable(name = "username") String username){
        return usuService.obtenerUsuarioPorUsername(username);
    }

    @RequestMapping(value="/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> actualizarUsuario(@PathVariable(name = "id") Integer idUsuAntiguo, @RequestBody UsuarioDTO usuActualizado, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Usuario usuarioAntiguo = usuService.obtenerUsuarioPorId(idUsuAntiguo);

        usuarioAntiguo.setUsername(usuActualizado.getUsername());
        usuarioAntiguo.setEmail(usuActualizado.getEmail());
        usuarioAntiguo.setRoles(usuActualizado.getRoles());

        usuService.guardar(usuarioAntiguo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value="/remove/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> eliminarUsuarioPorID(@PathVariable(name = "id") Integer id){
        try{
            Usuario usuEliminar = usuService.obtenerUsuarioPorId(id);
            usuEliminar.getRoles().clear();
            usuService.deletePorId(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
