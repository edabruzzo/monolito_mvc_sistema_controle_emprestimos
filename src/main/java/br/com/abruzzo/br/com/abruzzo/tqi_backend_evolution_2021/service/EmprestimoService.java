package br.com.abruzzo.br.com.abruzzo.tqi_backend_evolution_2021.service;

/**
 * @author Emmanuel Abruzzo
 * @date 06/01/2022
 */

import br.com.abruzzo.br.com.abruzzo.tqi_backend_evolution_2021.dto.EmprestimoDTO;
import br.com.abruzzo.br.com.abruzzo.tqi_backend_evolution_2021.model.Emprestimo;
import br.com.abruzzo.br.com.abruzzo.tqi_backend_evolution_2021.repository.EmprestimoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmprestimoService {


    @Autowired
    EmprestimoRepository emprestimoRepository;

    @Autowired
    ModelMapper modelMapper;



    public List<Emprestimo> retornaTodosEmprestimosByCliente(String cpfClienteConsultado) {
        return this.emprestimoRepository.findAllByCpf(cpfClienteConsultado);
    }

    public List<Emprestimo> retornaTodosEmprestimos() {
        return this.emprestimoRepository.findAll();
    }


    public List<EmprestimoDTO> converterModelToDTO(List<Emprestimo> listaEmprestimos) {
        List<EmprestimoDTO> listaEmprestimoDTO = new ArrayList<>();

        listaEmprestimos.stream().forEach(emprestimo ->{

            EmprestimoDTO emprestimoDTO = this.modelMapper.map(emprestimo,EmprestimoDTO.class);
            listaEmprestimoDTO.add(emprestimoDTO);

        });
        return listaEmprestimoDTO;
    }



}
