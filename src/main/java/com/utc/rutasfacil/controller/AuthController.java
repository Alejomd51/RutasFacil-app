package com.utc.rutasfacil.controller;

import org.springframework.web.bind.annotation.*;

import com.utc.rutasfacil.config.PasswordEncoder;
import com.utc.rutasfacil.entity.Usuario;
import com.utc.rutasfacil.repository.UsuarioRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Controlador para autenticación de usuarios
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:8081")
public class AuthController {

    private final UsuarioRepository usuarioRepository;

    public AuthController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Endpoint de login
     */
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> credenciales) {
        Map<String, Object> response = new HashMap<>();

        String username = credenciales.get("username");
        String password = credenciales.get("password");

        // Validaciones
        if (username == null || username.trim().isEmpty()) {
            response.put("success", false);
            response.put("message", "El usuario es requerido");
            return response;
        }

        if (password == null || password.isEmpty()) {
            response.put("success", false);
            response.put("message", "La contraseña es requerida");
            return response;
        }

        try {
            // Buscar usuario
            Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username);

            if (usuarioOpt.isEmpty()) {
                response.put("success", false);
                response.put("message", "Usuario o contraseña incorrectos");
                return response;
            }

            Usuario usuario = usuarioOpt.get();

            // Verificar contraseña
            if (!PasswordEncoder.matches(password, usuario.getPassword())) {
                response.put("success", false);
                response.put("message", "Usuario o contraseña incorrectos");
                return response;
            }

            // Login exitoso
            response.put("success", true);
            response.put("message", "Autenticación exitosa");
            response.put("usuario", usuario);

            return response;

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error al autenticar: " + e.getMessage());
            return response;
        }
    }

    /**
     * Endpoint para obtener datos del usuario (requiere autenticación)
     */
    @GetMapping("/usuario/{id}")
    public Usuario obtenerUsuario(@PathVariable Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }
}
