package com.utc.rutasfacil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utc.rutasfacil.entity.Entrega;

public interface EntregaRepository extends JpaRepository<Entrega, Long> {

    Entrega findByNumeroGuia(String numeroGuia);

    List<Entrega> findByClienteId(Long clienteId);

}