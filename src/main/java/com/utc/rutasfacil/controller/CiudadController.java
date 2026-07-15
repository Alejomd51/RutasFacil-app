package com.utc.rutasfacil.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.utc.rutasfacil.entity.Ciudad;
import com.utc.rutasfacil.repository.CiudadRepository;

@RestController
@RequestMapping("/ciudades")
@CrossOrigin(origins = "*")
public class CiudadController {


    private final CiudadRepository ciudadRepository;


    public CiudadController(CiudadRepository ciudadRepository) {
        this.ciudadRepository = ciudadRepository;
    }



    // Listar todas las ciudades
    @GetMapping
    public List<Ciudad> listarCiudades(){

        return ciudadRepository.findAll();

    }



    // Buscar ciudad por ID
    @GetMapping("/{id}")
    public Ciudad buscarPorId(
            @PathVariable Long id){

        return ciudadRepository
                .findById(id)
                .orElse(null);

    }



    // Buscar ciudad automáticamente por nombre
    // Ejemplo: /ciudades/nombre/Cuenca
    @GetMapping("/nombre/{nombre}")
    public Ciudad buscarPorNombre(
            @PathVariable String nombre){


        return ciudadRepository
                .findByNombreIgnoreCase(nombre)
                .orElse(null);


    }



    // Registrar ciudad
    @PostMapping
    public Ciudad guardarCiudad(
            @RequestBody Ciudad ciudad){


        return ciudadRepository.save(ciudad);


    }



    // Actualizar ciudad
    @PutMapping("/{id}")
    public Ciudad actualizarCiudad(
            @PathVariable Long id,
            @RequestBody Ciudad ciudad){


        Ciudad actual =
                ciudadRepository
                .findById(id)
                .orElse(null);



        if(actual == null){

            return null;

        }



        actual.setNombre(ciudad.getNombre());
        actual.setLatitud(ciudad.getLatitud());
        actual.setLongitud(ciudad.getLongitud());


        return ciudadRepository.save(actual);


    }



    // Eliminar ciudad
    @DeleteMapping("/{id}")
    public void eliminarCiudad(
            @PathVariable Long id){


        ciudadRepository.deleteById(id);


    }


}