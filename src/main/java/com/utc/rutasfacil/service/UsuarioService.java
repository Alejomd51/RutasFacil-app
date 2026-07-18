package com.utc.rutasfacil.service;

import org.springframework.stereotype.Service;

import com.utc.rutasfacil.entity.Usuario;
import com.utc.rutasfacil.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;


    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }


    public Usuario buscarPorUsername(String username) {
        return usuarioRepository.findByUsernameIgnoreCase(username);
    
    }

}