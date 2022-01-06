package br.com.abruzzo.br.com.abruzzo.tqi_backend_evolution_2021.service;

import br.com.abruzzo.br.com.abruzzo.tqi_backend_evolution_2021.model.Cliente;
import br.com.abruzzo.br.com.abruzzo.tqi_backend_evolution_2021.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Emmanuel Abruzzo
 * @date 06/01/2022
 */
@Service
public class ClienteService {


    @Autowired
    ClienteRepository clienteRepository;


    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

}
