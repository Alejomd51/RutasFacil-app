package com.utc.RutasFacil_app.controller;

import com.utc.RutasFacil_app.entity.LugarEntrega;
import com.utc.RutasFacil_app.repository.CiudadRepository;
import com.utc.RutasFacil_app.repository.LugarEntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LugarController {

    @Autowired
    private LugarEntregaRepository lugarRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @GetMapping("/lugares/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("lugar", new LugarEntrega());
        model.addAttribute("ciudades", ciudadRepository.findAll());
        return "formulario-lugar";
    }

    @PostMapping("/lugares/guardar")
    public String guardarLugar(@ModelAttribute LugarEntrega lugar, Model model) {
        lugarRepository.save(lugar);
        model.addAttribute("mensaje", "Lugar registrado correctamente");
        return "redirect:/lugares/nuevo";
    }

    @GetMapping("/lugares")
    public String listarLugares(Model model) {
        model.addAttribute("lugares", lugarRepository.findAll());
        return "lista-lugares";
    }
}