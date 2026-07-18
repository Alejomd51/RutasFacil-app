package com.utc.rutasfacil.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.utc.rutasfacil.entity.Ciudad;
import com.utc.rutasfacil.repository.CiudadRepository;

@Service
public class CiudadService {

    private final CiudadRepository ciudadRepository;

    public CiudadService(CiudadRepository ciudadRepository) {
        this.ciudadRepository = ciudadRepository;
    }

    public List<Ciudad> listarCiudades() {
        return ciudadRepository.findAll();
    }

    public Ciudad guardarCiudad(Ciudad ciudad) {
        return ciudadRepository.save(ciudad);
    }

    public Ciudad buscarCiudad(Long id) {
        return ciudadRepository.findById(id).orElse(null);
    }

    public void eliminarCiudad(Long id) {
        ciudadRepository.deleteById(id);
    }
}