package br.com.abruzzo.br.com.abruzzo.tqi_backend_evolution_2021.controller;

import br.com.abruzzo.br.com.abruzzo.tqi_backend_evolution_2021.dto.UsuarioDTO;
import br.com.abruzzo.br.com.abruzzo.tqi_backend_evolution_2021.model.Usuario;
import br.com.abruzzo.br.com.abruzzo.tqi_backend_evolution_2021.service.AutenticacaoUsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;

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


    @RolesAllowed({"FUNCIONARIO", "SUPER_ADMIN"})
    @PostMapping("criar")
    public String criarNovousuario(@ModelAttribute UsuarioDTO usuarioDTO, Model model){

        Usuario usuario = this.autenticacaoUsuarioService.converterUsuarioDTOToModel(usuarioDTO);
        Usuario usuarioSalvo = this.autenticacaoUsuarioService.criarUsuario(usuario);
        UsuarioDTO usuarioSalvoDTO = this.autenticacaoUsuarioService.converterUsuarioModelToDTO(usuarioSalvo);

        model.addAttribute("usuarioSalvo",usuarioSalvoDTO);

        return "usuario";
    }







}
