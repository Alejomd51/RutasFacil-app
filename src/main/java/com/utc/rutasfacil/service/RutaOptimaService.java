package com.utc.rutasfacil.service;

import com.utc.rutasfacil.dto.RutaOptimaDTO;
import com.utc.rutasfacil.entity.Ciudad;
import com.utc.rutasfacil.entity.Ruta;
import com.utc.rutasfacil.graph.Dijkstra;
import com.utc.rutasfacil.graph.Grafo;
import com.utc.rutasfacil.graph.ResultadoRuta;
import com.utc.rutasfacil.repository.CiudadRepository;
import com.utc.rutasfacil.repository.RutaRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RutaOptimaService {

    private final RutaRepository rutaRepository;
    private final CiudadRepository ciudadRepository;
    private final Dijkstra dijkstra;

    public RutaOptimaService(
            RutaRepository rutaRepository,
            CiudadRepository ciudadRepository
    ) {
        this.rutaRepository = rutaRepository;
        this.ciudadRepository = ciudadRepository;
        this.dijkstra = new Dijkstra();
    }

    @Transactional(readOnly = true)
    public RutaOptimaDTO calcularRutaOptima(
            Long origenId,
            Long destinoId
    ) {
        Ciudad origen = ciudadRepository.findById(origenId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No existe la ciudad de origen con ID: " + origenId
                ));

        Ciudad destino = ciudadRepository.findById(destinoId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No existe la ciudad de destino con ID: " + destinoId
                ));

        List<Ruta> rutasBD = rutaRepository.findAll();

        Grafo grafo = construirGrafo(
                rutasBD,
                origenId,
                destinoId
        );

        ResultadoRuta resultado = dijkstra.calcular(
                grafo,
                origenId,
                destinoId
        );

        if (!resultado.isExisteRuta()) {
            return new RutaOptimaDTO(
                    origenId,
                    origen.getNombre(),
                    destinoId,
                    destino.getNombre(),
                    List.of(),
                    List.of(),
                    0.0,
                    false,
                    "No existe una ruta registrada entre las ciudades seleccionadas."
            );
        }

        Map<Long, Ciudad> ciudadesPorId = ciudadRepository.findAll()
                .stream()
                .collect(Collectors.toMap(
                        Ciudad::getId,
                        Function.identity()
                ));

        List<String> ciudadesRecorridas = resultado.getCamino()
                .stream()
                .map(id -> {
                    Ciudad ciudad = ciudadesPorId.get(id);

                    if (ciudad != null) {
                        return ciudad.getNombre();
                    }

                    return "Ciudad ID " + id;
                })
                .toList();

        return new RutaOptimaDTO(
                origenId,
                origen.getNombre(),
                destinoId,
                destino.getNombre(),
                resultado.getCamino(),
                ciudadesRecorridas,
                resultado.getDistanciaTotal(),
                true,
                "Ruta óptima calculada correctamente."
        );
    }

    private Grafo construirGrafo(
            List<Ruta> rutasBD,
            Long origenId,
            Long destinoId
    ) {
        Grafo grafo = new Grafo();

        // Inserta ambos vértices aunque todavía no tengan rutas.
        grafo.agregarVertice(origenId);
        grafo.agregarVertice(destinoId);

        for (Ruta ruta : rutasBD) {
            if (ruta.getOrigen() == null
                    || ruta.getDestino() == null
                    || ruta.getDistancia() == null) {
                continue;
            }

            Long idOrigenRuta = ruta.getOrigen().getId();
            Long idDestinoRuta = ruta.getDestino().getId();
            double distancia = ruta.getDistancia();

            if (distancia < 0) {
                continue;
            }

            /*
             * Se agrega en ambos sentidos:
             * Ciudad A <-> Ciudad B.
             *
             * Si tus rutas son de un único sentido, reemplaza este bloque por:
             *
             * grafo.agregarArista(idOrigenRuta, idDestinoRuta, distancia);
             */
            grafo.agregarAristaBidireccional(
                    idOrigenRuta,
                    idDestinoRuta,
                    distancia
            );
        }

        return grafo;
    }
}