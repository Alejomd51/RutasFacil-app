package com.utc.rutasfacil.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.utc.rutasfacil.entity.Vehiculo;
import com.utc.rutasfacil.service.VehiculoService;

@RestController
@RequestMapping("/vehiculos")
@CrossOrigin(origins = "*")
public class VehiculoController {

    private final VehiculoService vehiculoService;

    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @GetMapping
    public List<Vehiculo> listarVehiculos() {
        return vehiculoService.listarVehiculos();
    }

    @PostMapping
    public Vehiculo guardarVehiculo(@RequestBody Vehiculo vehiculo) {
        return vehiculoService.guardarVehiculo(vehiculo);
    }

    @GetMapping("/{id}")
    public Vehiculo buscarVehiculo(@PathVariable Long id) {
        return vehiculoService.buscarVehiculo(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarVehiculo(@PathVariable Long id) {
        vehiculoService.eliminarVehiculo(id);
    }
}