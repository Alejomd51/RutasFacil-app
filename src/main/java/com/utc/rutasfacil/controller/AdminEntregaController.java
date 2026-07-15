package com.utc.rutasfacil.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.utc.rutasfacil.entity.Entrega;
import com.utc.rutasfacil.service.EntregaService;

@RestController
@RequestMapping("/admin/entregas")
@CrossOrigin(origins = "*")
public class AdminEntregaController {


    private final EntregaService entregaService;


    public AdminEntregaController(EntregaService entregaService){
        this.entregaService = entregaService;
    }



    // Ver todos los pedidos
    @GetMapping
    public List<Entrega> listar(){

        return entregaService.listarEntregas();

    }



    // Cambiar estado del pedido
    @PutMapping("/{id}/estado")
    public Entrega cambiarEstado(
            @PathVariable Long id,
            @RequestParam String estado){

        Entrega entrega = entregaService.buscarEntrega(id);

        if(entrega != null){

            entrega.setEstado(estado);

            entrega.setFechaActualizacion(
                java.time.LocalDateTime.now()
            );

            return entregaService.guardarEntrega(entrega);

        }

        return null;

    }


}