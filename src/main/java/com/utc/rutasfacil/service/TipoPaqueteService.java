package com.utc.rutasfacil.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.utc.rutasfacil.entity.TipoPaquete;
import com.utc.rutasfacil.repository.TipoPaqueteRepository;

@Service
public class TipoPaqueteService {

    private final TipoPaqueteRepository tipoPaqueteRepository;

    public TipoPaqueteService(TipoPaqueteRepository tipoPaqueteRepository) {
        this.tipoPaqueteRepository = tipoPaqueteRepository;
    }

    public List<TipoPaquete> listarTipos() {
        return tipoPaqueteRepository.findAll();
    }

    public TipoPaquete guardarTipo(TipoPaquete tipoPaquete) {
        return tipoPaqueteRepository.save(tipoPaquete);
    }

    public TipoPaquete buscarTipo(Long id) {
        return tipoPaqueteRepository.findById(id).orElse(null);
    }

    public void eliminarTipo(Long id) {
        tipoPaqueteRepository.deleteById(id);
    }
}