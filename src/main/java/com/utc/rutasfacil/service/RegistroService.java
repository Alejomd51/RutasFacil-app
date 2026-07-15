package com.utc.rutasfacil.service;

import org.springframework.stereotype.Service;

import com.utc.rutasfacil.entity.Cliente;
import com.utc.rutasfacil.entity.Usuario;
import com.utc.rutasfacil.repository.ClienteRepository;
import com.utc.rutasfacil.repository.UsuarioRepository;

@Service
public class RegistroService {

    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;


    public RegistroService(
            ClienteRepository clienteRepository,
            UsuarioRepository usuarioRepository) {

        this.clienteRepository = clienteRepository;
        this.usuarioRepository = usuarioRepository;
    }


    public Usuario registrarCliente(
            Cliente cliente,
            String username,
            String password) {


        // Guardar cliente primero
        Cliente clienteGuardado = clienteRepository.save(cliente);


        // Crear usuario relacionado
        Usuario usuario = new Usuario();

        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setRol("CLIENTE");
        usuario.setCliente(clienteGuardado);


        return usuarioRepository.save(usuario);
    }
}