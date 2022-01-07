package br.com.abruzzo.tqi_backend_evolution_2021.controller;

import br.com.abruzzo.tqi_backend_evolution_2021.dto.ClienteDTO;
import br.com.abruzzo.tqi_backend_evolution_2021.model.Cliente;
import br.com.abruzzo.tqi_backend_evolution_2021.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;


/**
 * @author Emmanuel Abruzzo
 * @date 06/01/2022
 */
@Controller
@RequestMapping("/cliente")
@RolesAllowed({"FUNCIONARIO", "SUPER_ADMIN"})
public class ClienteController {


    @Autowired
    ClienteService clienteService;


    @GetMapping()
    public String clientes(Model model){

        List<Cliente> listaClientes = this.clienteService.listarClientes();
        List<ClienteDTO> listaClientesDTO = this.clienteService.converterlistModelToDTO(listaClientes);

        model.addAttribute("listaClientes",listaClientesDTO);

        return "cliente";

    }



    @PostMapping
    public void cadastrarCliente(@Valid @ModelAttribute ClienteDTO clienteDTO, BindingResult bindingResult, Model model){

        this.clienteService.criaNovoCliente(clienteDTO);
        this.clientes(model);

    }

}

