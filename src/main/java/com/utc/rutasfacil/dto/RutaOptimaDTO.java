package com.utc.rutasfacil.dto;

import java.util.List;

public class RutaOptimaDTO {

    private Long ciudadOrigenId;
    private String ciudadOrigen;

    private Long ciudadDestinoId;
    private String ciudadDestino;

    private List<Long> idsCiudades;
    private List<String> ciudadesRecorridas;

    private double distanciaTotal;
    private boolean encontrada;
    private String mensaje;

    public RutaOptimaDTO() {
    }

    public RutaOptimaDTO(
            Long ciudadOrigenId,
            String ciudadOrigen,
            Long ciudadDestinoId,
            String ciudadDestino,
            List<Long> idsCiudades,
            List<String> ciudadesRecorridas,
            double distanciaTotal,
            boolean encontrada,
            String mensaje
    ) {
        this.ciudadOrigenId = ciudadOrigenId;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestinoId = ciudadDestinoId;
        this.ciudadDestino = ciudadDestino;
        this.idsCiudades = idsCiudades;
        this.ciudadesRecorridas = ciudadesRecorridas;
        this.distanciaTotal = distanciaTotal;
        this.encontrada = encontrada;
        this.mensaje = mensaje;
    }

    public Long getCiudadOrigenId() {
        return ciudadOrigenId;
    }

    public void setCiudadOrigenId(Long ciudadOrigenId) {
        this.ciudadOrigenId = ciudadOrigenId;
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public Long getCiudadDestinoId() {
        return ciudadDestinoId;
    }

    public void setCiudadDestinoId(Long ciudadDestinoId) {
        this.ciudadDestinoId = ciudadDestinoId;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public List<Long> getIdsCiudades() {
        return idsCiudades;
    }

    public void setIdsCiudades(List<Long> idsCiudades) {
        this.idsCiudades = idsCiudades;
    }

    public List<String> getCiudadesRecorridas() {
        return ciudadesRecorridas;
    }

    public void setCiudadesRecorridas(
            List<String> ciudadesRecorridas
    ) {
        this.ciudadesRecorridas = ciudadesRecorridas;
    }

    public double getDistanciaTotal() {
        return distanciaTotal;
    }

    public void setDistanciaTotal(double distanciaTotal) {
        this.distanciaTotal = distanciaTotal;
    }

    public boolean isEncontrada() {
        return encontrada;
    }

    public void setEncontrada(boolean encontrada) {
        this.encontrada = encontrada;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}