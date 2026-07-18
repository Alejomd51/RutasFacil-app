package com.utc.rutasfacil.controller;


import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.utc.rutasfacil.entity.TipoPaquete;
import com.utc.rutasfacil.repository.TipoPaqueteRepository;


@RestController
@RequestMapping("/tipos-paquete")
@CrossOrigin(origins = "*")
public class TipoPaqueteController {


    private final TipoPaqueteRepository repository;


    public TipoPaqueteController(TipoPaqueteRepository repository){

        this.repository = repository;

    }



    @GetMapping
    public List<TipoPaquete> listar(){

        return repository.findAll();

    }


}