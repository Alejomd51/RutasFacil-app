package com.utc.RutasFacil_app.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "encomienda")
public class Encomienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEncomienda;

    @ManyToOne
    @JoinColumn(name = "id_origen", nullable = false)
    private LugarEntrega origen;

    @ManyToOne
    @JoinColumn(name = "id_destino", nullable = false)
    private LugarEntrega destino;

    private BigDecimal pesoPaquete;

    @Column(length = 20)
    private String estado = "PENDIENTE";

    private BigDecimal distanciaCalculada;

    private LocalDateTime fechaRegistro = LocalDateTime.now();

    public Long getIdEncomienda() { return idEncomienda; }
    public void setIdEncomienda(Long idEncomienda) { this.idEncomienda = idEncomienda; }

    public LugarEntrega getOrigen() { return origen; }
    public void setOrigen(LugarEntrega origen) { this.origen = origen; }

    public LugarEntrega getDestino() { return destino; }
    public void setDestino(LugarEntrega destino) { this.destino = destino; }

    public BigDecimal getPesoPaquete() { return pesoPaquete; }
    public void setPesoPaquete(BigDecimal pesoPaquete) { this.pesoPaquete = pesoPaquete; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public BigDecimal getDistanciaCalculada() { return distanciaCalculada; }
    public void setDistanciaCalculada(BigDecimal distanciaCalculada) { this.distanciaCalculada = distanciaCalculada; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}