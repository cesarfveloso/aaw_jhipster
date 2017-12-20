package com.aaw.aula3.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Professor.
 */
@Entity
@Table(name = "professor")
public class Professor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "titulo_formacao")
    private String tituloFormacao;

    @Column(name = "area_conhecimento")
    private String areaConhecimento;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Professor nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTituloFormacao() {
        return tituloFormacao;
    }

    public Professor tituloFormacao(String tituloFormacao) {
        this.tituloFormacao = tituloFormacao;
        return this;
    }

    public void setTituloFormacao(String tituloFormacao) {
        this.tituloFormacao = tituloFormacao;
    }

    public String getAreaConhecimento() {
        return areaConhecimento;
    }

    public Professor areaConhecimento(String areaConhecimento) {
        this.areaConhecimento = areaConhecimento;
        return this;
    }

    public void setAreaConhecimento(String areaConhecimento) {
        this.areaConhecimento = areaConhecimento;
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
        Professor professor = (Professor) o;
        if (professor.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), professor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Professor{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", tituloFormacao='" + getTituloFormacao() + "'" +
            ", areaConhecimento='" + getAreaConhecimento() + "'" +
            "}";
    }
}
