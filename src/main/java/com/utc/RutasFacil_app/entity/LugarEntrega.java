package com.utc.RutasFacil_app.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "lugar_entrega")
public class LugarEntrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLugar;

    @ManyToOne
    @JoinColumn(name = "id_ciudad", nullable = false)
    private Ciudad ciudad;

    @Column(nullable = false, length = 10)
    private String telefono;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String direccion;

    @Column(precision = 9, scale = 6)
    private BigDecimal latitud;

    @Column(precision = 9, scale = 6)
    private BigDecimal longitud;

    public Long getIdLugar() { return idLugar; }
    public void setIdLugar(Long idLugar) { this.idLugar = idLugar; }

    public Ciudad getCiudad() { return ciudad; }
    public void setCiudad(Ciudad ciudad) { this.ciudad = ciudad; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public BigDecimal getLatitud() { return latitud; }
    public void setLatitud(BigDecimal latitud) { this.latitud = latitud; }

    public BigDecimal getLongitud() { return longitud; }
    public void setLongitud(BigDecimal longitud) { this.longitud = longitud; }
}