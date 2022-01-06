package br.com.abruzzo.tqi_backend_evolution_2021.service;

import br.com.abruzzo.tqi_backend_evolution_2021.dto.ClienteDTO;
import br.com.abruzzo.tqi_backend_evolution_2021.dto.SolicitacaoClienteEmprestimoDTO;
import br.com.abruzzo.tqi_backend_evolution_2021.model.Cliente;
import br.com.abruzzo.tqi_backend_evolution_2021.repository.ClienteRepository;
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


    public List<ClienteDTO> converterlistModelToDTO(List<Cliente> listaClientes) {

        List<ClienteDTO> listaClientesDTO = new ArrayList<>();

        listaClientes.stream().forEach(cliente ->{

            ClienteDTO clienteDTO = this.modelMapper.map(cliente,ClienteDTO.class);
            listaClientesDTO.add(clienteDTO);

        });
        return listaClientesDTO;
    }


    public ClienteDTO criaNovoCliente(SolicitacaoClienteEmprestimoDTO solicitacaoClienteEmprestimoDTO) {

            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setNome(solicitacaoClienteEmprestimoDTO.getNome());
            clienteDTO.setCpf(solicitacaoClienteEmprestimoDTO.getCpf());
            clienteDTO.setRenda(solicitacaoClienteEmprestimoDTO.getRenda());
            clienteDTO.setEnderecoCompleto(solicitacaoClienteEmprestimoDTO.getEnderecoCompleto());
            clienteDTO.setRg(solicitacaoClienteEmprestimoDTO.getRg());

            Cliente cliente = this.converterClienteDTOToModel(clienteDTO);
            Cliente clienteSalvo = this.clienteRepository.save(cliente);
            ClienteDTO clienteSalvoDTO = this.converterModelToDTO(clienteSalvo);
            return clienteSalvoDTO;
    }


    private ClienteDTO converterModelToDTO(Cliente cliente) {
        ClienteDTO clienteDTO = this.modelMapper.map(cliente, ClienteDTO.class);
        return clienteDTO;
    }

    public Cliente converterClienteDTOToModel(ClienteDTO clienteDTO) {
        Cliente cliente = this.modelMapper.map(clienteDTO, Cliente.class);
        return cliente;
    }

    public Cliente findByCPF(String cpf) {
        return this.clienteRepository.findByCpf(cpf);
    }
}
