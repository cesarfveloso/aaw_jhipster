package com.aaw.aula3.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Disciplina.
 */
@Entity
@Table(name = "disciplina")
public class Disciplina implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "carga_horaria")
    private Float cargaHoraria;

    @Column(name = "sigla")
    private String sigla;

    @Column(name = "ementa")
    private String ementa;

    @Column(name = "creditos")
    private Integer creditos;

    @OneToMany(mappedBy = "disciplina")
    @JsonIgnore
    private Set<Atividade> atividades = new HashSet<>();

    @ManyToOne
    private Professor professor;

    @ManyToMany(mappedBy = "professors")
    @JsonIgnore
    private Set<Professor> disciplinas = new HashSet<>();

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

    public Disciplina nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getCargaHoraria() {
        return cargaHoraria;
    }

    public Disciplina cargaHoraria(Float cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
        return this;
    }

    public void setCargaHoraria(Float cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getSigla() {
        return sigla;
    }

    public Disciplina sigla(String sigla) {
        this.sigla = sigla;
        return this;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getEmenta() {
        return ementa;
    }

    public Disciplina ementa(String ementa) {
        this.ementa = ementa;
        return this;
    }

    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public Disciplina creditos(Integer creditos) {
        this.creditos = creditos;
        return this;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public Set<Atividade> getAtividades() {
        return atividades;
    }

    public Disciplina atividades(Set<Atividade> atividades) {
        this.atividades = atividades;
        return this;
    }

    public Disciplina addAtividade(Atividade atividade) {
        this.atividades.add(atividade);
        atividade.setDisciplina(this);
        return this;
    }

    public Disciplina removeAtividade(Atividade atividade) {
        this.atividades.remove(atividade);
        atividade.setDisciplina(null);
        return this;
    }

    public void setAtividades(Set<Atividade> atividades) {
        this.atividades = atividades;
    }

    public Professor getProfessor() {
        return professor;
    }

    public Disciplina professor(Professor professor) {
        this.professor = professor;
        return this;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Set<Professor> getDisciplinas() {
        return disciplinas;
    }

    public Disciplina disciplinas(Set<Professor> professors) {
        this.disciplinas = professors;
        return this;
    }

    public Disciplina addDisciplina(Professor professor) {
        this.disciplinas.add(professor);
        professor.getProfessors().add(this);
        return this;
    }

    public Disciplina removeDisciplina(Professor professor) {
        this.disciplinas.remove(professor);
        professor.getProfessors().remove(this);
        return this;
    }

    public void setDisciplinas(Set<Professor> professors) {
        this.disciplinas = professors;
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
        Disciplina disciplina = (Disciplina) o;
        if (disciplina.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), disciplina.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Disciplina{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", cargaHoraria=" + getCargaHoraria() +
            ", sigla='" + getSigla() + "'" +
            ", ementa='" + getEmenta() + "'" +
            ", creditos=" + getCreditos() +
            "}";
    }
}
