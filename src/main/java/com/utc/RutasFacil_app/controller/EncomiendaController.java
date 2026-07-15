package com.utc.RutasFacil_app.controller;

import com.utc.RutasFacil_app.entity.Encomienda;
import com.utc.RutasFacil_app.repository.EncomiendaRepository;
import com.utc.RutasFacil_app.repository.LugarRepository;
import com.utc.RutasFacil_app.service.GrafoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EncomiendaController {

    @Autowired
    private EncomiendaRepository encomiendaRepository;

    @Autowired
    private LugarEntregaRepository lugarRepository;

    @Autowired
    private GrafoService grafoService;

    @GetMapping("/encomiendas/nueva")
    public String mostrarFormulario(Model model) {
        model.addAttribute("encomienda", new Encomienda());
        model.addAttribute("lugares", lugarRepository.findAll());
        return "formulario-encomienda";
    }

    @PostMapping("/encomiendas/calcular")
    public String calcularRuta(@RequestParam Long idOrigen,
                                @RequestParam Long idDestino,
                                Model model) {
        List<Long> camino = grafoService.dijkstra(idOrigen, idDestino);
        double distanciaTotal = grafoService.calcularDistanciaTotal(camino);

        model.addAttribute("camino", camino);
        model.addAttribute("distanciaTotal", distanciaTotal);
        model.addAttribute("origen", idOrigen);
        model.addAttribute("destino", idDestino);
        return "resultado-ruta";
    }

    @GetMapping("/encomiendas")
    public String listarEncomiendas(Model model) {
        model.addAttribute("encomiendas", encomiendaRepository.findAll());
        return "lista-encomiendas";
    }
}