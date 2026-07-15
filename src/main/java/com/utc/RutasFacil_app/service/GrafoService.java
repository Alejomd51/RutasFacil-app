package com.utc.RutasFacil_app.service;

import com.utc.RutasFacil_app.model.Ruta;
import com.utc.RutasFacil_app.repository.RutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class GrafoService {

    @Autowired
    private RutaRepository rutaRepository;

    public Map<Long, List<double[]>> construirListaAdyacencia() {
        Map<Long, List<double[]>> grafo = new HashMap<>();
        for (Ruta r : rutaRepository.findAll()) {
            long origenId = r.getOrigen().getIdCiudad();
            long destinoId = r.getDestino().getIdCiudad();
            double peso = r.getDistanciaKm().doubleValue();

            grafo.computeIfAbsent(origenId, k -> new ArrayList<>())
                 .add(new double[]{destinoId, peso});
            grafo.computeIfAbsent(destinoId, k -> new ArrayList<>())
                 .add(new double[]{origenId, peso});
        }
        return grafo;
    }

    public List<Long> dijkstra(Long origenId, Long destinoId) {
        Map<Long, List<double[]>> grafo = construirListaAdyacencia();
        Map<Long, Double> distancias = new HashMap<>();
        Map<Long, Long> previos = new HashMap<>();
        PriorityQueue<double[]> cola = new PriorityQueue<>((a, b) -> Double.compare(a[1], b[1]));

        for (Long nodo : grafo.keySet()) distancias.put(nodo, Double.MAX_VALUE);
        distancias.put(origenId, 0.0);
        cola.add(new double[]{origenId, 0.0});

        while (!cola.isEmpty()) {
            double[] actual = cola.poll();
            long nodoActual = (long) actual[0];

            if (nodoActual == destinoId) break;

            for (double[] vecino : grafo.getOrDefault(nodoActual, new ArrayList<>())) {
                long vecinoId = (long) vecino[0];
                double nuevaDist = distancias.get(nodoActual) + vecino[1];

                if (nuevaDist < distancias.getOrDefault(vecinoId, Double.MAX_VALUE)) {
                    distancias.put(vecinoId, nuevaDist);
                    previos.put(vecinoId, nodoActual);
                    cola.add(new double[]{vecinoId, nuevaDist});
                }
            }
        }

        LinkedList<Long> camino = new LinkedList<>();
        Long actual = destinoId;
        while (actual != null) {
            camino.addFirst(actual);
            actual = previos.get(actual);
        }
        return camino;
    }

    public double calcularDistanciaTotal(List<Long> camino) {
        Map<Long, List<double[]>> grafo = construirListaAdyacencia();
        double total = 0;
        for (int i = 0; i < camino.size() - 1; i++) {
            long actual = camino.get(i);
            long siguiente = camino.get(i + 1);
            for (double[] vecino : grafo.get(actual)) {
                if ((long) vecino[0] == siguiente) {
                    total += vecino[1];
                    break;
                }
            }
        }
        return total;
    }
}