package com.utc.rutasfacil.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.utc.rutasfacil.entity.Ruta;
import com.utc.rutasfacil.service.RutaService;

@RestController
@RequestMapping("/rutas")
@CrossOrigin(origins = "*")
public class RutaController {

    private final RutaService rutaService;

    public RutaController(RutaService rutaService) {
        this.rutaService = rutaService;
    }


    @GetMapping
    public List<Ruta> listarRutas() {
        return rutaService.listarRutas();
    }


    @PostMapping
    public Ruta guardarRuta(@RequestBody Ruta ruta) {
        return rutaService.guardarRuta(ruta);
    }


    @GetMapping("/{id}")
    public Ruta buscarRuta(@PathVariable Long id) {
        return rutaService.buscarRuta(id);
    }


    @DeleteMapping("/{id}")
    public void eliminarRuta(@PathVariable Long id) {
        rutaService.eliminarRuta(id);
    }
}