package com.utc.RutasFacil_app.repository;

import com.utc.RutasFacil_app.entity.LugarEntrega;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LugarRepository extends JpaRepository<LugarEntrega, Long> {
}