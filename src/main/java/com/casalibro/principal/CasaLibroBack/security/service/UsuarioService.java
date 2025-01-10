package com.casalibro.principal.CasaLibroBack.security.service;

import com.casalibro.principal.CasaLibroBack.security.model.Usuario;
import com.casalibro.principal.CasaLibroBack.security.repository.UsuarioRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepo usuarioRepo;

    public Optional<Usuario> getByUsername(String nombreUsu){
        return usuarioRepo.findByUsername(nombreUsu);
    }

    public boolean existByUsername(String nombre){
        return usuarioRepo.existsByUsername(nombre);
    }

    public boolean existByEmail(String correo){
        return usuarioRepo.existsByEmail(correo);
    }

    public Usuario guardar(Usuario usu) {
        Usuario savedUsuario = usuarioRepo.save(usu);
        return savedUsuario;
    }


    public List<Usuario> listarUsuario(){
        return usuarioRepo.findAll();
    }

    public Usuario obtenerUsuarioPorId(Integer id) {
        return usuarioRepo.findById(id).get();
    }

    public Usuario obtenerUsuarioPorUsername(String nombreUsuario){
        return usuarioRepo.findByUsername(nombreUsuario).get();
    }

    public void deletePorId(int id){
        usuarioRepo.delete(usuarioRepo.findById(id).get());
    }

    public void eliminarUsuario(Usuario user){
        usuarioRepo.delete(user);
    }

    public Usuario actualizarUsuario(Usuario usuUpdate, Integer idUA){
        Usuario usuAntiguo = obtenerUsuarioPorId(idUA);
        return guardar(usuAntiguo);
    }
}
