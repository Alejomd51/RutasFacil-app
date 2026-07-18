package com.utc.rutasfacil.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.utc.rutasfacil.entity.Entrega;
import com.utc.rutasfacil.service.EntregaService;


@RestController
@RequestMapping("/entregas")
@CrossOrigin(origins = "http://localhost:8081")
public class EntregaController {


    private final EntregaService entregaService;


    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }



    // Listar todas las entregas
    @GetMapping
    public List<Entrega> listarEntregas(){

        return entregaService.listarEntregas();

    }



    // Crear entrega
    @PostMapping
    public Entrega guardarEntrega(
            @RequestBody Entrega entrega){

        return entregaService.guardarEntrega(entrega);

    }



    // Buscar por ID
    @GetMapping("/{id}")
    public Entrega buscarEntrega(
            @PathVariable Long id){

        return entregaService.buscarEntrega(id);

    }



    // Buscar por guía
    @GetMapping("/guia/{numeroGuia}")
    public Entrega buscarPorGuia(
            @PathVariable String numeroGuia){

        return entregaService.buscarPorNumeroGuia(numeroGuia);

    }



    // Pedidos del cliente
    @GetMapping("/mis-pedidos/{clienteId}")
    public List<Entrega> misPedidos(
            @PathVariable Long clienteId){

        return entregaService.listarPorCliente(clienteId);

    }



    // Eliminar
    @DeleteMapping("/{id}")
    public void eliminarEntrega(
            @PathVariable Long id){

        entregaService.eliminarEntrega(id);

    }

}