package com.casalibro.principal.CasaLibroBack.security.controller;

import com.casalibro.principal.CasaLibroBack.dto.Mensaje;
import com.casalibro.principal.CasaLibroBack.security.dto.LoginUsuario;
import com.casalibro.principal.CasaLibroBack.security.dto.NuevoUsuario;
import com.casalibro.principal.CasaLibroBack.security.enums.RolNombre;
import com.casalibro.principal.CasaLibroBack.security.model.Rol;
import com.casalibro.principal.CasaLibroBack.security.model.Usuario;
import com.casalibro.principal.CasaLibroBack.security.service.RolService;
import com.casalibro.principal.CasaLibroBack.security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(new Mensaje("Campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existByUsername(nuevoUsuario.getUsername()))
            return new ResponseEntity<>(new Mensaje("Ese nombre de Usuario ya existe"),HttpStatus.BAD_REQUEST);
        if(usuarioService.existByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity<>(new Mensaje("Ese email ya está asignado a un Usuario"),HttpStatus.BAD_REQUEST);
        Usuario usuario =
                new Usuario(nuevoUsuario.getUsername(), passwordEncoder.encode(nuevoUsuario.getPassword())
                        , nuevoUsuario.getEmail());
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByNombre(RolNombre.ROLE_USER).get());
        if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByNombre(RolNombre.ROLE_ADMIN).get());
        usuario.setRoles(roles);
        usuarioService.guardar(usuario);
        return new ResponseEntity<>(new Mensaje("Usuario guardado"),HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getUsername(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>(new Mensaje("Login exitoso"), HttpStatus.OK);
    }

    @GetMapping("/session")
    public ResponseEntity<?> checkSession() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
            // Si la sesión está activa
            return new ResponseEntity<>(new Mensaje("Sesión activa"), HttpStatus.OK);
        } else {
            // Si la sesión no está activa, devolver un estado 401 (No autorizado)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new Mensaje("No hay sesión activa, redirigiendo a login: http://localhost:4200/login"));
        }
    }
}