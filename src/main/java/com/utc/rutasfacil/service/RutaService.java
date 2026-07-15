package com.utc.rutasfacil.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.utc.rutasfacil.entity.Ruta;
import com.utc.rutasfacil.repository.RutaRepository;

@Service
public class RutaService {

    private final RutaRepository rutaRepository;

    public RutaService(RutaRepository rutaRepository) {
        this.rutaRepository = rutaRepository;
    }

    public List<Ruta> listarRutas() {
        return rutaRepository.findAll();
    }

    public Ruta guardarRuta(Ruta ruta) {
        return rutaRepository.save(ruta);
    }

    public Ruta buscarRuta(Long id) {
        return rutaRepository.findById(id).orElse(null);
    }

    public void eliminarRuta(Long id) {
        rutaRepository.deleteById(id);
    }
}