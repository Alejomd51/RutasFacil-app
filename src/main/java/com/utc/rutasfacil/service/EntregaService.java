package com.utc.rutasfacil.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.utc.rutasfacil.entity.Entrega;
import com.utc.rutasfacil.repository.EntregaRepository;

@Service
public class EntregaService {

    private final EntregaRepository entregaRepository;

    public EntregaService(EntregaRepository entregaRepository) {
        this.entregaRepository = entregaRepository;
    }


    // Listar todas las entregas
    public List<Entrega> listarEntregas() {
        return entregaRepository.findAll();
    }


    // Guardar entrega con número de guía automático
    public Entrega guardarEntrega(Entrega entrega) {

        // Generar número de guía automáticamente
        if (entrega.getNumeroGuia() == null || entrega.getNumeroGuia().isEmpty()) {

            long numero = entregaRepository.count() + 1;

            entrega.setNumeroGuia(
                String.format("RF-%04d", numero)
            );
        }


        // Estado inicial
        if (entrega.getEstado() == null || entrega.getEstado().isEmpty()) {
            entrega.setEstado("REGISTRADA");
        }


        return entregaRepository.save(entrega);
    }


    // Buscar entrega por ID
    public Entrega buscarEntrega(Long id) {
        return entregaRepository.findById(id)
                .orElse(null);
    }


    // Eliminar entrega
    public void eliminarEntrega(Long id) {
        entregaRepository.deleteById(id);
    }


    // Buscar por número de guía
    public Entrega buscarPorNumeroGuia(String numeroGuia) {
        return entregaRepository.findByNumeroGuia(numeroGuia);
    }
    
    public List<Entrega> listarPorCliente(Long clienteId) {
        return entregaRepository.findByClienteId(clienteId);
    }
}