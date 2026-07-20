package com.utc.rutasfacil.graph;

import java.util.List;

public class ResultadoRuta {

    private List<Long> camino;
    private double distanciaTotal;

    public ResultadoRuta(
            List<Long> camino,
            double distanciaTotal
    ) {
        this.camino = camino;
        this.distanciaTotal = distanciaTotal;
    }

    public List<Long> getCamino() {
        return camino;
    }

    public void setCamino(List<Long> camino) {
        this.camino = camino;
    }

    public double getDistanciaTotal() {
        return distanciaTotal;
    }

    public void setDistanciaTotal(double distanciaTotal) {
        this.distanciaTotal = distanciaTotal;
    }

    public boolean isExisteRuta() {
        return camino != null
                && !camino.isEmpty()
                && Double.isFinite(distanciaTotal);
    }
}