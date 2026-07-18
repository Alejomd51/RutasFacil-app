package com.utc.rutasfacil.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "asignaciones")
public class Asignacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Entrega asignada
    @ManyToOne
    @JoinColumn(name = "entrega_id", nullable = false)
    private Entrega entrega;

    // Vehículo asignado
    @ManyToOne
    @JoinColumn(name = "vehiculo_id", nullable = false)
    private Vehiculo vehiculo;

    @Column(name = "fecha_asignacion", nullable = false)
    private LocalDateTime fechaAsignacion;

    @Column(nullable = false, length = 30)
    private String estado;

    public Asignacion() {
        this.fechaAsignacion = LocalDateTime.now();
        this.estado = "ASIGNADA";
    }

    public Asignacion(Entrega entrega, Vehiculo vehiculo) {
        this.entrega = entrega;
        this.vehiculo = vehiculo;
        this.fechaAsignacion = LocalDateTime.now();
        this.estado = "ASIGNADA";
    }

    public Long getId() {
        return id;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public LocalDateTime getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDateTime fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}