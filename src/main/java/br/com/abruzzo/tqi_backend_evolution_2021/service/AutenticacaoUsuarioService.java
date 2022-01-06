package br.com.abruzzo.tqi_backend_evolution_2021.service;

import br.com.abruzzo.tqi_backend_evolution_2021.dto.SolicitacaoClienteEmprestimoDTO;
import br.com.abruzzo.tqi_backend_evolution_2021.dto.UsuarioDTO;
import br.com.abruzzo.tqi_backend_evolution_2021.model.Usuario;
import br.com.abruzzo.tqi_backend_evolution_2021.repository.AutenticacaoUsuarioRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Emmanuel Abruzzo
 * @date 06/01/2022
 */
@Service
@RolesAllowed({"FUNCIONARIO","SUPER_ADMIN"})
public class AutenticacaoUsuarioService {

    private static final Logger logger = LoggerFactory.getLogger(AutenticacaoUsuarioService.class);

    @Autowired
    AutenticacaoUsuarioRepository autenticacaoUsuarioRepository;

    @Autowired
    ModelMapper modelMapper;



    public UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO) {


        Usuario usuario = this.converterUsuarioDTOToModel(usuarioDTO);
        Usuario usuarioSalvo = this.autenticacaoUsuarioRepository.save(usuario);
        UsuarioDTO usuarioDTOSalvo = this.converterUsuarioModelToDTO(usuarioSalvo);

        return usuarioDTOSalvo;
    }





    public Usuario converterUsuarioDTOToModel(UsuarioDTO usuarioDTO) {
        Usuario usuario = this.modelMapper.map(usuarioDTO,Usuario.class);
        return usuario;
    }


    public UsuarioDTO converterUsuarioModelToDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = this.modelMapper.map(usuario, UsuarioDTO.class);
        return usuarioDTO;
    }



}