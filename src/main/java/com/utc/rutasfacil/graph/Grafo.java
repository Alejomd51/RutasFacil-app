package com.utc.rutasfacil.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grafo {

    private Map<Long, List<Arista>> adyacencias;

    public Grafo() {
        this.adyacencias = new HashMap<>();
    }

    public void agregarVertice(Long ciudadId) {
        if (ciudadId == null) {
            throw new IllegalArgumentException(
                    "El ID de la ciudad no puede ser nulo."
            );
        }

        adyacencias.putIfAbsent(ciudadId, new ArrayList<>());
    }

    public void agregarArista(
            Long origenId,
            Long destinoId,
            double peso
    ) {
        agregarVertice(origenId);
        agregarVertice(destinoId);

        adyacencias.get(origenId).add(
                new Arista(destinoId, peso)
        );
    }

    public void agregarAristaBidireccional(
            Long origenId,
            Long destinoId,
            double peso
    ) {
        agregarArista(origenId, destinoId, peso);
        agregarArista(destinoId, origenId, peso);
    }

    public List<Arista> obtenerVecinos(Long ciudadId) {
        return adyacencias.getOrDefault(
                ciudadId,
                Collections.emptyList()
        );
    }

    public boolean contieneVertice(Long ciudadId) {
        return adyacencias.containsKey(ciudadId);
    }

    public Map<Long, List<Arista>> getAdyacencias() {
        return adyacencias;
    }

    public void setAdyacencias(
            Map<Long, List<Arista>> adyacencias
    ) {
        this.adyacencias = adyacencias;
    }
}