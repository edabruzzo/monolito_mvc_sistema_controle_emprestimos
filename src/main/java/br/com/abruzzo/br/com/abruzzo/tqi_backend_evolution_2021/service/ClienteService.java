package br.com.abruzzo.br.com.abruzzo.tqi_backend_evolution_2021.service;

import br.com.abruzzo.br.com.abruzzo.tqi_backend_evolution_2021.dto.ClienteDTO;
import br.com.abruzzo.br.com.abruzzo.tqi_backend_evolution_2021.model.Cliente;
import br.com.abruzzo.br.com.abruzzo.tqi_backend_evolution_2021.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Emmanuel Abruzzo
 * @date 06/01/2022
 */
@Service
public class ClienteService {


    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ModelMapper modelMapper;


    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }


    public List<ClienteDTO> converterModelToDTO(List<Cliente> listaClientes) {

        List<ClienteDTO> listaClientesDTO = new ArrayList<>();

        listaClientes.stream().forEach(cliente ->{

            ClienteDTO clienteDTO = this.modelMapper.map(cliente,ClienteDTO.class);
            listaClientesDTO.add(clienteDTO);

        });
        return listaClientesDTO;
    }





}
