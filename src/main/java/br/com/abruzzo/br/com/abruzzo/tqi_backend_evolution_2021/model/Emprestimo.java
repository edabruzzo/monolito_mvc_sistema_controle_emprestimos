package br.com.abruzzo.br.com.abruzzo.tqi_backend_evolution_2021.model;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;


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
    private Date dataPrimeiraParcela;

    @Column(name = "numeroMaximoParcelas")
    @Max(60)
    private Integer numeroMaximoParcelas;

    @Column(name="idCliente")
    private Integer idCliente;

    @Enumerated(EnumType.STRING)
    private StatusEmprestimo status;

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

    public Date getDataPrimeiraParcela() {
        return dataPrimeiraParcela;
    }

    public void setDataPrimeiraParcela(Date dataPrimeiraParcela) {
        this.dataPrimeiraParcela = dataPrimeiraParcela;
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

    public StatusEmprestimo getStatus() {
        return status;
    }

    public void setStatus(StatusEmprestimo status) {
        this.status = status;
    }


    public Emprestimo() { }

    public Emprestimo(Long id, Double valor, Date dataPrimeiraParcela, Integer numeroMaximoParcelas, Integer idCliente, StatusEmprestimo status) {
        this.id = id;
        this.valor = valor;
        this.dataPrimeiraParcela = dataPrimeiraParcela;
        this.numeroMaximoParcelas = numeroMaximoParcelas;
        this.idCliente = idCliente;
        this.status = status;
    }


    @Override
    public String toString() {
        return "Emprestimo{" +
                "id=" + id +
                ", valor=" + valor +
                ", data_primeira_parcela=" + dataPrimeiraParcela +
                ", numeroMaximoParcelas=" + numeroMaximoParcelas +
                ", idCliente=" + idCliente +
                ", status=" + status +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emprestimo that = (Emprestimo) o;
        return Objects.equals(id, that.id) && Objects.equals(valor, that.valor) && Objects.equals(dataPrimeiraParcela, that.dataPrimeiraParcela) && Objects.equals(numeroMaximoParcelas, that.numeroMaximoParcelas) && Objects.equals(idCliente, that.idCliente) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, valor, dataPrimeiraParcela, numeroMaximoParcelas, idCliente, status);
    }
}
