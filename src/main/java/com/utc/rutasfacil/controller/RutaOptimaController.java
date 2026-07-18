package com.utc.rutasfacil.controller;

import com.utc.rutasfacil.dto.RutaOptimaDTO;
import com.utc.rutasfacil.service.RutaOptimaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rutas")
public class RutaOptimaController {

    private final RutaOptimaService rutaOptimaService;

    public RutaOptimaController(
            RutaOptimaService rutaOptimaService
    ) {
        this.rutaOptimaService = rutaOptimaService;
    }

    @GetMapping("/optima/{origenId}/{destinoId}")
    public ResponseEntity<RutaOptimaDTO> calcularRutaOptima(
            @PathVariable Long origenId,
            @PathVariable Long destinoId
    ) {
        RutaOptimaDTO resultado = rutaOptimaService
                .calcularRutaOptima(origenId, destinoId);

        return ResponseEntity.ok(resultado);
    }
}