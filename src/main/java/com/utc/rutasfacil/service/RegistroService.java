package com.utc.rutasfacil.service;

import org.springframework.stereotype.Service;

import com.utc.rutasfacil.config.PasswordEncoder;
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


    /**
     * Registra un nuevo cliente con su usuario y contraseña encriptada
     */
    public Usuario registrarCliente(
            Cliente cliente,
            String username,
            String password) {

        // Validaciones básicas
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("El usuario no puede estar vacío");
        }

        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres");
        }

        // Verificar si el usuario ya existe
        if (usuarioRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("El usuario ya existe");
        }

        // Verificar si la cédula ya existe
        if (clienteRepository.findByCedula(cliente.getCedula()) != null) {
            throw new IllegalArgumentException("La cédula ya está registrada");
        }

        // Guardar cliente primero
        Cliente clienteGuardado = clienteRepository.save(cliente);

        // Crear usuario con contraseña encriptada
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(PasswordEncoder.encode(password)); // Encriptar contraseña
        usuario.setRol("CLIENTE");
        usuario.setCliente(clienteGuardado);

        return usuarioRepository.save(usuario);
    }
}
