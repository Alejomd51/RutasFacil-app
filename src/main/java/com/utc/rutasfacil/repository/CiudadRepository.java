package com.utc.rutasfacil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utc.rutasfacil.entity.Ciudad;


public interface CiudadRepository 
extends JpaRepository<Ciudad, Long>{


    Optional<Ciudad> findByNombreIgnoreCase(String nombre);


}