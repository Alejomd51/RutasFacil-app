package com.utc.rutasfacil.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class VistaController {


    @GetMapping("/registro")
    public String registro(){

        return "registro";

    }


    @GetMapping("/login")
    public String login(){

        return "login";

    }
    
    @GetMapping("/panel")
    public String panel(){

        return "panel";

    }
    
    @GetMapping("/pedido")
    public String pedido(){

        return "pedido";

    }

}