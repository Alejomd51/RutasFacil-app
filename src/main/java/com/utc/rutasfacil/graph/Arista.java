package com.utc.rutasfacil.graph;

public class Arista {

    private Long destinoId;
    private double peso;

    public Arista(Long destinoId, double peso) {
        if (destinoId == null) {
            throw new IllegalArgumentException(
                    "El ID de ciudad destino no puede ser nulo."
            );
        }

        if (peso < 0) {
            throw new IllegalArgumentException(
                    "Dijkstra no admite rutas con distancia negativa."
            );
        }

        this.destinoId = destinoId;
        this.peso = peso;
    }

    public Long getDestinoId() {
        return destinoId;
    }

    public void setDestinoId(Long destinoId) {
        this.destinoId = destinoId;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}