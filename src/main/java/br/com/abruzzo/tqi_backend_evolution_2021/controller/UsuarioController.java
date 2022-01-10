package br.com.abruzzo.tqi_backend_evolution_2021.controller;

import br.com.abruzzo.tqi_backend_evolution_2021.dto.UsuarioDTO;
import br.com.abruzzo.tqi_backend_evolution_2021.model.Usuario;
import br.com.abruzzo.tqi_backend_evolution_2021.service.AutenticacaoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @author Emmanuel Abruzzo
 * @date 06/01/2022
 */
@RequestMapping("/usuarios")
public class UsuarioController {


    @Autowired
    AutenticacaoUsuarioService autenticacaoUsuarioService;


    @GetMapping
    public String usuarioView(Model model){
        return "usuario";
    }


    @RolesAllowed({"ROLE_FUNCIONARIO", "ROLE_SUPER_ADMIN"})
    @PostMapping("criar")
    public String criarNovousuario(@ModelAttribute UsuarioDTO usuarioDTO, Model model){

        UsuarioDTO usuarioDTOSalvo = this.autenticacaoUsuarioService.criarUsuario(usuarioDTO);
        model.addAttribute("usuarioSalvo",usuarioDTOSalvo);
        return "usuario";
    }


    @RolesAllowed({"ROLE_SUPER_ADMIN"})
    @GetMapping
    public String listarUsuarios(Model model){

        List<UsuarioDTO> listaUsuariosDTO = this.autenticacaoUsuarioService.listarUsuarios();
        model.addAttribute("listaUsuarios",listaUsuariosDTO);
        return "usuario";
    }





}
