package br.com.abruzzo.br.com.abruzzo.tqi_backend_evolution_2021.controller;

import br.com.abruzzo.br.com.abruzzo.tqi_backend_evolution_2021.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.security.RolesAllowed;
import java.util.List;


/**
 * @author Emmanuel Abruzzo
 * @date 06/01/2022
 */

@Controller
public class ClienteController {


    @Autowired
    ClienteService clienteService;


    @GetMapping("/cliente")
    @RolesAllowed({"FUNCIONARIO", "SUPER_ADMIN"})
    public String home(Model model){

        List<ClienteDTO> listaClientes = this.clienteService.listarClientes();
        model.addAttribute("listaClientes",listaClientes);

        return "cliente";



    }


}

