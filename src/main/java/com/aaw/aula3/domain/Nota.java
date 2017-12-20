package com.aaw.aula3.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Nota.
 */
@Entity
@Table(name = "nota")
public class Nota implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor")
    private Float valor;

    @Column(name = "data_avaliacao")
    private String dataAvaliacao;

    @ManyToOne
    private Aluno aluno;

    @ManyToOne
    private Atividade atividade;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getValor() {
        return valor;
    }

    public Nota valor(Float valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getDataAvaliacao() {
        return dataAvaliacao;
    }

    public Nota dataAvaliacao(String dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
        return this;
    }

    public void setDataAvaliacao(String dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Nota aluno(Aluno aluno) {
        this.aluno = aluno;
        return this;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public Nota atividade(Atividade atividade) {
        this.atividade = atividade;
        return this;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Nota nota = (Nota) o;
        if (nota.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nota.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Nota{" +
            "id=" + getId() +
            ", valor=" + getValor() +
            ", dataAvaliacao='" + getDataAvaliacao() + "'" +
            "}";
    }
}
