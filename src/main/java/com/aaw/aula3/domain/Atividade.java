package com.aaw.aula3.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Atividade.
 */
@Entity
@Table(name = "atividade")
public class Atividade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor")
    private Float valor;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "data_final")
    private String dataFinal;

    @OneToMany(mappedBy = "atividade")
    @JsonIgnore
    private Set<Nota> notas = new HashSet<>();

    @OneToMany(mappedBy = "atividade")
    @JsonIgnore
    private Set<Entrega> entregas = new HashSet<>();

    @ManyToOne
    private Disciplina disciplina;

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

    public Atividade valor(Float valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public Atividade tipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public Atividade dataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
        return this;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Set<Nota> getNotas() {
        return notas;
    }

    public Atividade notas(Set<Nota> notas) {
        this.notas = notas;
        return this;
    }

    public Atividade addNota(Nota nota) {
        this.notas.add(nota);
        nota.setAtividade(this);
        return this;
    }

    public Atividade removeNota(Nota nota) {
        this.notas.remove(nota);
        nota.setAtividade(null);
        return this;
    }

    public void setNotas(Set<Nota> notas) {
        this.notas = notas;
    }

    public Set<Entrega> getEntregas() {
        return entregas;
    }

    public Atividade entregas(Set<Entrega> entregas) {
        this.entregas = entregas;
        return this;
    }

    public Atividade addEntrega(Entrega entrega) {
        this.entregas.add(entrega);
        entrega.setAtividade(this);
        return this;
    }

    public Atividade removeEntrega(Entrega entrega) {
        this.entregas.remove(entrega);
        entrega.setAtividade(null);
        return this;
    }

    public void setEntregas(Set<Entrega> entregas) {
        this.entregas = entregas;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public Atividade disciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
        return this;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
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
        Atividade atividade = (Atividade) o;
        if (atividade.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), atividade.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Atividade{" +
            "id=" + getId() +
            ", valor=" + getValor() +
            ", tipo='" + getTipo() + "'" +
            ", dataFinal='" + getDataFinal() + "'" +
            "}";
    }
}
