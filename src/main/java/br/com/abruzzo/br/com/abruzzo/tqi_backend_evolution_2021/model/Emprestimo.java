package br.com.abruzzo.br.com.abruzzo.tqi_backend_evolution_2021.model;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import java.io.Serializable;
import java.sql.Date;



/**
 * Classe de entidade JPA que representa um POJO - Empréstimo
 *
 * Validações dos fields usando Bean validation do JAVAEE7
 *
 *
 * @link https://docs.oracle.com/javaee/7/tutorial/bean-validation001.htm
 * @link https://github.com/javaee-samples/javaee7-samples/blob/master/pom.xml
 * @link https://guilhermesteves.dev/tutoriais/regex-uteis-para-o-seu-dia-a-dia/
 *
 * @author Emmanuel Abruzzo
 * @date 06/01/2022
 */

@Entity
@Table(name = "tb_emprestimo", catalog = "emprestimo", schema = "public")
//@Data //Using @Data for JPA entities is not recommended. It can cause severe performance and memory consumption issues.
public class Emprestimo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "data_primeira_parcela")
    @Future
    private Date data_primeira_parcela;

    @Column(name = "numeroMaximoParcelas")
    @Max(60)
    private Integer numeroMaximoParcelas;

    @Column(name="idCliente")
    private Integer idCliente;

    public Emprestimo() {}

    public Emprestimo(Long id, Double valor, Date data_primeira_parcela, Integer numeroMaximoParcelas, Integer idCliente) {
        this.id = id;
        this.valor = valor;
        this.data_primeira_parcela = data_primeira_parcela;
        this.numeroMaximoParcelas = numeroMaximoParcelas;
        this.idCliente = idCliente;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getData_primeira_parcela() {
        return data_primeira_parcela;
    }

    public void setData_primeira_parcela(Date data_primeira_parcela) {
        this.data_primeira_parcela = data_primeira_parcela;
    }

    public Integer getNumeroMaximoParcelas() {
        return numeroMaximoParcelas;
    }

    public void setNumeroMaximoParcelas(Integer numeroMaximoParcelas) {
        this.numeroMaximoParcelas = numeroMaximoParcelas;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "id=" + id +
                ", valor=" + valor +
                ", data_primeira_parcela=" + data_primeira_parcela +
                ", numeroMaximoParcelas=" + numeroMaximoParcelas +
                ", idCliente=" + idCliente +
                '}';
    }
}
