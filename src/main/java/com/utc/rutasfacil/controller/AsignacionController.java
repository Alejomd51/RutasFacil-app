package com.utc.rutasfacil.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.utc.rutasfacil.entity.Asignacion;
import com.utc.rutasfacil.service.AsignacionService;

@RestController
@RequestMapping("/asignaciones")
@CrossOrigin(origins = "*")
public class AsignacionController {

    private final AsignacionService asignacionService;

    public AsignacionController(AsignacionService asignacionService) {
        this.asignacionService = asignacionService;
    }


    @GetMapping
    public List<Asignacion> listarAsignaciones() {
        return asignacionService.listarAsignaciones();
    }


    @PostMapping
    public Asignacion guardarAsignacion(@RequestBody Asignacion asignacion) {
        return asignacionService.guardarAsignacion(asignacion);
    }


    @GetMapping("/{id}")
    public Asignacion buscarAsignacion(@PathVariable Long id) {
        return asignacionService.buscarAsignacion(id);
    }


    @DeleteMapping("/{id}")
    public void eliminarAsignacion(@PathVariable Long id) {
        asignacionService.eliminarAsignacion(id);
    }
}