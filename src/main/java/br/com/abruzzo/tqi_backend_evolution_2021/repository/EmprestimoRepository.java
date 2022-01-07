package br.com.abruzzo.tqi_backend_evolution_2021.repository;

import br.com.abruzzo.tqi_backend_evolution_2021.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Emmanuel Abruzzo
 * @date 06/01/2022
 */
@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    String consultaEmprestimosPorCpfCliente = "select e.* from tb_emprestimo e " +
            " inner join tb_cliente c on c.idCliente = e.idCliente " +
            " where c.cpfCliente :=cpfCliente";


    @Query(value=consultaEmprestimosPorCpfCliente,nativeQuery = true)
    List<Emprestimo> findAllByCpf(@Param(value = "cpfCliente") String cpfCliente);

}
