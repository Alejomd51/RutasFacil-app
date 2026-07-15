package com.utc.rutasfacil.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "entregas")
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_guia", nullable = false, unique = true, length = 30)
    private String numeroGuia;

    // Cliente que realiza el envío
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    // Ciudad de origen
    @ManyToOne
    @JoinColumn(name = "ciudad_origen_id", nullable = false)
    private Ciudad ciudadOrigen;

    // Ciudad de destino
    @ManyToOne
    @JoinColumn(name = "ciudad_destino_id", nullable = false)
    private Ciudad ciudadDestino;

    // Tipo de paquete
    @ManyToOne
    @JoinColumn(name = "tipo_paquete_id", nullable = false)
    private TipoPaquete tipoPaquete;

    @NotBlank(message = "La dirección de origen es obligatoria")
    @Column(name = "direccion_origen", nullable = false, length = 250)
    private String direccionOrigen;

    @NotBlank(message = "La dirección de destino es obligatoria")
    @Column(name = "direccion_destino", nullable = false, length = 250)
    private String direccionDestino;

    @NotNull
    @Column(name = "latitud_origen", nullable = false)
    private Double latitudOrigen;

    @NotNull
    @Column(name = "longitud_origen", nullable = false)
    private Double longitudOrigen;

    @NotNull
    @Column(name = "latitud_destino", nullable = false)
    private Double latitudDestino;

    @NotNull
    @Column(name = "longitud_destino", nullable = false)
    private Double longitudDestino;

    @NotBlank(message = "La descripción del paquete es obligatoria")
    @Column(nullable = false, length = 250)
    private String descripcion;

    @NotNull
    @DecimalMin(value = "0.1", message = "El peso debe ser mayor a cero")
    @Column(nullable = false)
    private Double peso;

    @NotNull
    @DecimalMin(value = "0.1")
    @Column(nullable = false)
    private Double alto;

    @NotNull
    @DecimalMin(value = "0.1")
    @Column(nullable = false)
    private Double ancho;

    @NotNull
    @DecimalMin(value = "0.1")
    @Column(nullable = false)
    private Double largo;

    @NotNull
    @DecimalMin(value = "0.0")
    @Column(name = "valor_declarado", nullable = false)
    private Double valorDeclarado;

    @Column(length = 300)
    private String observaciones;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    @Column(nullable = false, length = 20)
    private String estado;
    
    @Column(name="fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    public Entrega() {
        this.fechaRegistro = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
        this.estado = "REGISTRADA";
    }
    
    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }


    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Long getId() {
        return id;
    }

    public String getNumeroGuia() {
        return numeroGuia;
    }

    public void setNumeroGuia(String numeroGuia) {
        this.numeroGuia = numeroGuia;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Ciudad getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(Ciudad ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public Ciudad getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(Ciudad ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public TipoPaquete getTipoPaquete() {
        return tipoPaquete;
    }

    public void setTipoPaquete(TipoPaquete tipoPaquete) {
        this.tipoPaquete = tipoPaquete;
    }

    public String getDireccionOrigen() {
        return direccionOrigen;
    }

    public void setDireccionOrigen(String direccionOrigen) {
        this.direccionOrigen = direccionOrigen;
    }

    public String getDireccionDestino() {
        return direccionDestino;
    }

    public void setDireccionDestino(String direccionDestino) {
        this.direccionDestino = direccionDestino;
    }

    public Double getLatitudOrigen() {
        return latitudOrigen;
    }

    public void setLatitudOrigen(Double latitudOrigen) {
        this.latitudOrigen = latitudOrigen;
    }

    public Double getLongitudOrigen() {
        return longitudOrigen;
    }

    public void setLongitudOrigen(Double longitudOrigen) {
        this.longitudOrigen = longitudOrigen;
    }

    public Double getLatitudDestino() {
        return latitudDestino;
    }

    public void setLatitudDestino(Double latitudDestino) {
        this.latitudDestino = latitudDestino;
    }

    public Double getLongitudDestino() {
        return longitudDestino;
    }

    public void setLongitudDestino(Double longitudDestino) {
        this.longitudDestino = longitudDestino;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAlto() {
        return alto;
    }

    public void setAlto(Double alto) {
        this.alto = alto;
    }

    public Double getAncho() {
        return ancho;
    }

    public void setAncho(Double ancho) {
        this.ancho = ancho;
    }

    public Double getLargo() {
        return largo;
    }

    public void setLargo(Double largo) {
        this.largo = largo;
    }

    public Double getValorDeclarado() {
        return valorDeclarado;
    }

    public void setValorDeclarado(Double valorDeclarado) {
        this.valorDeclarado = valorDeclarado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}