package com.utc.RutasFacil_app.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ruta")
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRuta;

    @ManyToOne
    @JoinColumn(name = "id_origen", nullable = false)
    private Ciudad origen;

    @ManyToOne
    @JoinColumn(name = "id_destino", nullable = false)
    private Ciudad destino;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal distanciaKm;

    private Integer tiempoEstimado;

    public Long getIdRuta() { return idRuta; }
    public void setIdRuta(Long idRuta) { this.idRuta = idRuta; }

    public Ciudad getOrigen() { return origen; }
    public void setOrigen(Ciudad origen) { this.origen = origen; }

    public Ciudad getDestino() { return destino; }
    public void setDestino(Ciudad destino) { this.destino = destino; }

    public BigDecimal getDistanciaKm() { return distanciaKm; }
    public void setDistanciaKm(BigDecimal distanciaKm) { this.distanciaKm = distanciaKm; }

    public Integer getTiempoEstimado() { return tiempoEstimado; }
    public void setTiempoEstimado(Integer tiempoEstimado) { this.tiempoEstimado = tiempoEstimado; }
}