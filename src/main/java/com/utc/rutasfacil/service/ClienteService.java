package com.utc.rutasfacil.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.utc.rutasfacil.entity.Cliente;
import com.utc.rutasfacil.repository.ClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }


    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }


    public Cliente buscarCliente(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }


    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}