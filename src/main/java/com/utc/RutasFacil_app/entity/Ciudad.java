package com.utc.RutasFacil_app.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ciudad")
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCiudad;

    @Column(nullable = false, unique = true, length = 80)
    private String nombre;

    @Column(nullable = false, precision = 9, scale = 6)
    private BigDecimal latitud;

    @Column(nullable = false, precision = 9, scale = 6)
    private BigDecimal longitud;

    public Long getIdCiudad() { return idCiudad; }
    public void setIdCiudad(Long idCiudad) { this.idCiudad = idCiudad; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public BigDecimal getLatitud() { return latitud; }
    public void setLatitud(BigDecimal latitud) { this.latitud = latitud; }

    public BigDecimal getLongitud() { return longitud; }
    public void setLongitud(BigDecimal longitud) { this.longitud = longitud; }
}