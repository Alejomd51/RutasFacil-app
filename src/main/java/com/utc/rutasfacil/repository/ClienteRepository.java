package com.utc.rutasfacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utc.rutasfacil.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Cliente findByCedula(String cedula);
}
