package com.utc.rutasfacil.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.utc.rutasfacil.dto.ApiResponse;
import com.utc.rutasfacil.dto.RegistroDTO;
import com.utc.rutasfacil.entity.Cliente;
import com.utc.rutasfacil.entity.Usuario;
import com.utc.rutasfacil.service.RegistroService;

/**
 * Controlador para registro de nuevos usuarios
 */
@RestController
@RequestMapping("/registro")
@CrossOrigin(origins = "http://localhost:8081")
public class RegistroController {

    private final RegistroService registroService;

    public RegistroController(RegistroService registroService) {
        this.registroService = registroService;
    }

    /**
     * Registra un nuevo cliente
     */
    @PostMapping
    public ResponseEntity<ApiResponse> registrar(@RequestBody RegistroDTO datos) {
        try {
            // Validaciones básicas
            if (datos.getNombres() == null || datos.getNombres().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Los nombres son requeridos"));
            }

            if (datos.getApellidos() == null || datos.getApellidos().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Los apellidos son requeridos"));
            }

            if (datos.getCedula() == null || datos.getCedula().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "La cédula es requerida"));
            }

            if (datos.getEmail() == null || !datos.getEmail().contains("@")) {
                return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "El correo electrónico no es válido"));
            }

            if (datos.getTelefono() == null || datos.getTelefono().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "El teléfono es requerido"));
            }

            // Crear cliente
            Cliente cliente = new Cliente();
            cliente.setNombres(datos.getNombres().trim());
            cliente.setApellidos(datos.getApellidos().trim());
            cliente.setCedula(datos.getCedula().trim());
            cliente.setEmail(datos.getEmail().trim());
            cliente.setTelefono(datos.getTelefono().trim());

            // Registrar
            Usuario usuarioRegistrado = registroService.registrarCliente(
                cliente,
                datos.getUsername(),
                datos.getPassword()
            );

            return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(
                    true,
                    "Registro exitoso",
                    usuarioRegistrado
                ));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                .body(new ApiResponse(false, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse(false, "Error al registrar: " + e.getMessage()));
        }
    }
}
