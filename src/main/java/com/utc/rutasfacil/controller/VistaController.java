package com.utc.rutasfacil.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VistaController {


    @GetMapping("/login")
    public String login() {
        return "login";
    }



    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }



    @GetMapping("/panel")
    public String panel() {
        return "panel";
    }



    @GetMapping("/pedido")
    public String pedido() {
        return "pedido";
    }



    @GetMapping("/mispedidos")
    public String misPedidos() {
        return "mispedidos";
    }



    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

}