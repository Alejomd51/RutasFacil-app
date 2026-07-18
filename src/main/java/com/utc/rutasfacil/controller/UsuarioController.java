package com.utc.rutasfacil.controller;

import org.springframework.web.bind.annotation.*;

import com.utc.rutasfacil.entity.Usuario;
import com.utc.rutasfacil.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;


    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @PostMapping
    public Usuario registrarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.guardarUsuario(usuario);
    }


    @GetMapping("/{username}")
    public Usuario buscarUsuario(@PathVariable String username) {
        return usuarioService.buscarPorUsername(username);
    }

}