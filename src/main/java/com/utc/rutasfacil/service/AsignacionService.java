package com.utc.rutasfacil.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.utc.rutasfacil.entity.Asignacion;
import com.utc.rutasfacil.repository.AsignacionRepository;

@Service
public class AsignacionService {

    private final AsignacionRepository asignacionRepository;

    public AsignacionService(AsignacionRepository asignacionRepository) {
        this.asignacionRepository = asignacionRepository;
    }

    public List<Asignacion> listarAsignaciones() {
        return asignacionRepository.findAll();
    }

    public Asignacion guardarAsignacion(Asignacion asignacion) {
        return asignacionRepository.save(asignacion);
    }

    public Asignacion buscarAsignacion(Long id) {
        return asignacionRepository.findById(id).orElse(null);
    }

    public void eliminarAsignacion(Long id) {
        asignacionRepository.deleteById(id);
    }
}