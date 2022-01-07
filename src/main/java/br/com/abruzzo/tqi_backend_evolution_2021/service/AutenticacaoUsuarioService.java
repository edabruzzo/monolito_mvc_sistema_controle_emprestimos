package br.com.abruzzo.tqi_backend_evolution_2021.service;

import br.com.abruzzo.tqi_backend_evolution_2021.dto.UsuarioDTO;
import br.com.abruzzo.tqi_backend_evolution_2021.exceptions.FuncionarioSemPrivilegioAdminTentandoCriarSUPERADMINException;
import br.com.abruzzo.tqi_backend_evolution_2021.model.Usuario;
import br.com.abruzzo.tqi_backend_evolution_2021.repository.AutenticacaoUsuarioRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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


    /**
     * Apenas funcionários com privilégio de administrador podem criar usuários com o mesmo nível
     * de privilégio.
     *
     * Se um funcionário sem privilégio de Admin tentar criar um Funcionário Super_Admin, o sistema
     * irá lançar uma exceção do tipo customizado: FuncionarioSemPrivilegioAdminTentandoCriarSUPERADMINException
     *
     * @param usuarioDTO
     * @return
     */
    public UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO) {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean usuarioLogadoFuncionarioSemPrivilegioAdmin = ! authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("SUPER_ADMIN"));

        boolean usuarioNovoPossuiPrivilegioAdmin = usuarioDTO.getRoles().stream()
                .anyMatch(role -> role.equals("SUPER_ADMIN"));

        if(usuarioLogadoFuncionarioSemPrivilegioAdmin && usuarioNovoPossuiPrivilegioAdmin){

            String credenciaisUsuarioLogado = authentication.getCredentials().toString();

            String mensagemErro = "Tentativa de um Funcionário criar um SUPER_ADMIN no Sistema\n";
            mensagemErro += String.format("Usuário que fez a tentativa %s\n",credenciaisUsuarioLogado);
            mensagemErro += String.format("Usuário que ele tentou cadastrar: %s\n",usuarioDTO);
            throw new FuncionarioSemPrivilegioAdminTentandoCriarSUPERADMINException(mensagemErro, this.logger);

        }



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


    public List<UsuarioDTO> listarUsuarios() {

        List<Usuario> listaUsuarios = this.autenticacaoUsuarioRepository.findAll();
        List<UsuarioDTO> listaUsuariosDTO = this.converterListUsuarioModelToDTO(listaUsuarios);
        return listaUsuariosDTO;


    }

    private List<UsuarioDTO> converterListUsuarioModelToDTO(List<Usuario> listaUsuarios) {

        List<UsuarioDTO> listaUsuariosDTO = new ArrayList<>();

        listaUsuarios.stream().forEach(usuario ->{

            UsuarioDTO usuarioDTO = this.converterUsuarioModelToDTO(usuario);

            listaUsuariosDTO.add(usuarioDTO);


        });

        return listaUsuariosDTO;

    }


}