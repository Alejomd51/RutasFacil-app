package com.utc.rutasfacil.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra {

    public ResultadoRuta calcular(
            Grafo grafo,
            Long origenId,
            Long destinoId
    ) {
        if (grafo == null) {
            throw new IllegalArgumentException(
                    "El grafo es obligatorio."
            );
        }

        if (origenId == null || destinoId == null) {
            throw new IllegalArgumentException(
                    "El origen y el destino son obligatorios."
            );
        }

        if (!grafo.contieneVertice(origenId)) {
            throw new IllegalArgumentException(
                    "La ciudad de origen no existe en el grafo."
            );
        }

        if (!grafo.contieneVertice(destinoId)) {
            throw new IllegalArgumentException(
                    "La ciudad de destino no existe en el grafo."
            );
        }

        if (origenId.equals(destinoId)) {
            List<Long> caminoMismoOrigen = new ArrayList<>();
            caminoMismoOrigen.add(origenId);

            return new ResultadoRuta(caminoMismoOrigen, 0.0);
        }

        Map<Long, Double> distancias = new HashMap<>();
        Map<Long, Long> anteriores = new HashMap<>();

        PriorityQueue<NodoDistancia> colaPrioridad =
                new PriorityQueue<>(
                        Comparator.comparingDouble(
                                NodoDistancia::getDistancia
                        )
                );

        distancias.put(origenId, 0.0);
        colaPrioridad.offer(
                new NodoDistancia(origenId, 0.0)
        );

        while (!colaPrioridad.isEmpty()) {
            NodoDistancia actual = colaPrioridad.poll();

            double distanciaRegistrada = distancias.getOrDefault(
                    actual.getCiudadId(),
                    Double.POSITIVE_INFINITY
            );

            // Descarta nodos antiguos que ya tienen una ruta más corta.
            if (actual.getDistancia() > distanciaRegistrada) {
                continue;
            }

            // Al extraer el destino, ya encontramos la distancia mínima.
            if (actual.getCiudadId().equals(destinoId)) {
                break;
            }

            List<Arista> vecinos = grafo.obtenerVecinos(
                    actual.getCiudadId()
            );

            for (Arista arista : vecinos) {
                double nuevaDistancia =
                        actual.getDistancia() + arista.getPeso();

                double distanciaAnterior = distancias.getOrDefault(
                        arista.getDestinoId(),
                        Double.POSITIVE_INFINITY
                );

                if (nuevaDistancia < distanciaAnterior) {
                    distancias.put(
                            arista.getDestinoId(),
                            nuevaDistancia
                    );

                    anteriores.put(
                            arista.getDestinoId(),
                            actual.getCiudadId()
                    );

                    colaPrioridad.offer(
                            new NodoDistancia(
                                    arista.getDestinoId(),
                                    nuevaDistancia
                            )
                    );
                }
            }
        }

        if (!distancias.containsKey(destinoId)) {
            return new ResultadoRuta(
                    Collections.emptyList(),
                    Double.POSITIVE_INFINITY
            );
        }

        List<Long> camino = reconstruirCamino(
                anteriores,
                origenId,
                destinoId
        );

        return new ResultadoRuta(
                camino,
                distancias.get(destinoId)
        );
    }

    private List<Long> reconstruirCamino(
            Map<Long, Long> anteriores,
            Long origenId,
            Long destinoId
    ) {
        List<Long> camino = new ArrayList<>();
        Long ciudadActual = destinoId;

        while (ciudadActual != null) {
            camino.add(ciudadActual);

            if (ciudadActual.equals(origenId)) {
                break;
            }

            ciudadActual = anteriores.get(ciudadActual);
        }

        Collections.reverse(camino);

        if (camino.isEmpty() || !camino.get(0).equals(origenId)) {
            return Collections.emptyList();
        }

        return camino;
    }

    private static class NodoDistancia {

        private Long ciudadId;
        private double distancia;

        public NodoDistancia(
                Long ciudadId,
                double distancia
        ) {
            this.ciudadId = ciudadId;
            this.distancia = distancia;
        }

        public Long getCiudadId() {
            return ciudadId;
        }

        public double getDistancia() {
            return distancia;
        }
    }
}