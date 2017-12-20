package com.aaw.aula3.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Entrega.
 */
@Entity
@Table(name = "entrega")
public class Entrega implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_entrega")
    private String dataEntrega;

    @Column(name = "entrega_no_prazo")
    private Boolean entregaNoPrazo;

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

    public String getDataEntrega() {
        return dataEntrega;
    }

    public Entrega dataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
        return this;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Boolean isEntregaNoPrazo() {
        return entregaNoPrazo;
    }

    public Entrega entregaNoPrazo(Boolean entregaNoPrazo) {
        this.entregaNoPrazo = entregaNoPrazo;
        return this;
    }

    public void setEntregaNoPrazo(Boolean entregaNoPrazo) {
        this.entregaNoPrazo = entregaNoPrazo;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Entrega aluno(Aluno aluno) {
        this.aluno = aluno;
        return this;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public Entrega atividade(Atividade atividade) {
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
        Entrega entrega = (Entrega) o;
        if (entrega.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entrega.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Entrega{" +
            "id=" + getId() +
            ", dataEntrega='" + getDataEntrega() + "'" +
            ", entregaNoPrazo='" + isEntregaNoPrazo() + "'" +
            "}";
    }
}
