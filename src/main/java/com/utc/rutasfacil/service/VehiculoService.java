package com.utc.rutasfacil.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.utc.rutasfacil.entity.Vehiculo;
import com.utc.rutasfacil.repository.VehiculoRepository;

@Service
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    public VehiculoService(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    public List<Vehiculo> listarVehiculos() {
        return vehiculoRepository.findAll();
    }

    public Vehiculo guardarVehiculo(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    public Vehiculo buscarVehiculo(Long id) {
        return vehiculoRepository.findById(id).orElse(null);
    }

    public void eliminarVehiculo(Long id) {
        vehiculoRepository.deleteById(id);
    }
}