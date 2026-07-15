package com.utc.rutasfacil.controller;

import org.springframework.web.bind.annotation.*;

import com.utc.rutasfacil.dto.RegistroDTO;
import com.utc.rutasfacil.entity.Cliente;
import com.utc.rutasfacil.entity.Usuario;
import com.utc.rutasfacil.service.RegistroService;

@RestController
@RequestMapping("/registro")
@CrossOrigin(origins = "*")
public class RegistroController {

    private final RegistroService registroService;


    public RegistroController(RegistroService registroService) {
        this.registroService = registroService;
    }


    @PostMapping
    public Usuario registrar(@RequestBody RegistroDTO datos) {


        Cliente cliente = new Cliente();

        cliente.setNombres(datos.getNombres());
        cliente.setApellidos(datos.getApellidos());
        cliente.setCedula(datos.getCedula());
        cliente.setEmail(datos.getEmail());
        cliente.setTelefono(datos.getTelefono());


        return registroService.registrarCliente(
                cliente,
                datos.getUsername(),
                datos.getPassword()
        );
    }

}