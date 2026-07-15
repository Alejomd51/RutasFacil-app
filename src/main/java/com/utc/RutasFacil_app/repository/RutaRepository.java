package com.utc.RutasFacil_app.repository;

import com.utc.RutasFacil_app.entity.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RutaRepository extends JpaRepository<Ruta, Long> {
    List<Ruta> findByOrigen_IdCiudad(Long idCiudad);
    List<Ruta> findByDestino_IdCiudad(Long idCiudad);
}