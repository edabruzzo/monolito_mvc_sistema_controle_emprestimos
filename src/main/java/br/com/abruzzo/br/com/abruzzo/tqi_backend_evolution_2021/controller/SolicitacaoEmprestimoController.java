package br.com.abruzzo.br.com.abruzzo.tqi_backend_evolution_2021.controller;

import br.com.abruzzo.br.com.abruzzo.tqi_backend_evolution_2021.dto.ClienteDTO;
import br.com.abruzzo.br.com.abruzzo.tqi_backend_evolution_2021.dto.EmprestimoDTO;
import br.com.abruzzo.br.com.abruzzo.tqi_backend_evolution_2021.dto.SolicitacaoClienteEmprestimoDTO;
import br.com.abruzzo.br.com.abruzzo.tqi_backend_evolution_2021.dto.UsuarioDTO;
import br.com.abruzzo.br.com.abruzzo.tqi_backend_evolution_2021.model.Cliente;
import br.com.abruzzo.br.com.abruzzo.tqi_backend_evolution_2021.service.AutenticacaoUsuarioService;
import br.com.abruzzo.br.com.abruzzo.tqi_backend_evolution_2021.service.ClienteService;
import br.com.abruzzo.br.com.abruzzo.tqi_backend_evolution_2021.service.EmprestimoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;
import java.util.List;


/**
 *
 * Classe do tipo Controller que recebe os dados do cliente e da solicitação de empréstimo diretamente da tela
 * e envia os objetos separados ClienteDTO e SolicitacaoEmprestimoDTO para os FeignClients dos respectivos serviços
 * que fazem efetivamente as chamadas para os microsserviços responsáveis pelo gerenciamento de cliente e de solicitações
 * de empréstimo.
 *
 * @author Emmanuel Abruzzo
 * @date 02/01/2022
 */
@Controller
@RequestMapping("solicitacao-emprestimo")
public class SolicitacaoEmprestimoController {

    private static final Logger logger = LoggerFactory.getLogger(SolicitacaoEmprestimoController.class);


    @Autowired
    AutenticacaoUsuarioService autenticacaoUsuarioService;

    @Autowired
    EmprestimoService solicitacaoEmprestimoService;

    @Autowired
    ClienteService clienteService;


    @GetMapping("solicitar")
    @RolesAllowed({"FUNCIONARIO", "SUPER_ADMIN"})
    public String solicitarEmprestimo(Model model) {
        return "emprestimo/solicitacao-emprestimo";
    }


    /**
     * Método para efetuar a solicitação de um novo empréstimo
     * Aqui partimos do princípio que somente um funcionário ou um
     * super_admin (que também é um funcionário) pode cadastrar uma nova solicitação
     * supondo que exista uma agência física para que o cliente realize tal pedido e tenha
     * seus documentos pessoais e comprovante de renda em mãos, para pedir um empréstimo.
     *
     *
     * @param solicitacaoClienteEmprestimoDTO
     * @return nomeView  emprestimo
     */
    @RolesAllowed({"FUNCIONARIO", "SUPER_ADMIN"})
    @PostMapping("novo")
    public String solicitarNovoEmprestimo(@ModelAttribute SolicitacaoClienteEmprestimoDTO solicitacaoClienteEmprestimoDTO, Model model){

        UsuarioDTO usuarioDTOSalvo = this.autenticacaoUsuarioService.criarUsuario(solicitacaoClienteEmprestimoDTO);
        logger.info("Foi criado o usuário na base para fins de autenticação %s",usuarioDTOSalvo);

        /**
         * Após a chamada para @link ClienteService se tudo correr bem já teremos
         * salvo o cliente no microsserviço responsável pelo gerenciamento de clientes
         * que nos retornará o clienteSalvoDTO já com um idCliente preenchido
         */
        ClienteDTO clienteSalvoDTO = this.clienteService.criaNovoCliente(solicitacaoClienteEmprestimoDTO);
        logger.info("Foi criado o Cliente na base %s",clienteSalvoDTO);

        Cliente clienteSalvo = this.clienteService.converterClienteDTOToModel(clienteSalvoDTO);

        EmprestimoDTO emprestimoSalvoDTO = this.solicitacaoEmprestimoService.criarEmprestimo(solicitacaoClienteEmprestimoDTO, clienteSalvo);
        logger.info("Foi criado o empréstimo na base %s",emprestimoSalvoDTO);

        List<EmprestimoDTO> listaEmprestimosDTO = this.solicitacaoEmprestimoService.retornaTodosEmprestimos();
        model.addAttribute("listaEmprestimos",listaEmprestimosDTO);

        return "emprestimo/listagemEmprestimos";
    }



}
