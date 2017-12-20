package com.aaw.aula3.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Turma.
 */
@Entity
@Table(name = "turma")
public class Turma implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "ano_ingresso")
    private Integer anoIngresso;

    @Column(name = "semestre_ingresso")
    private Integer semestreIngresso;

    @OneToMany(mappedBy = "turma")
    @JsonIgnore
    private Set<Aluno> alunos = new HashSet<>();

    @OneToMany(mappedBy = "turma")
    @JsonIgnore
    private Set<Aula> aulas = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Turma descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getAnoIngresso() {
        return anoIngresso;
    }

    public Turma anoIngresso(Integer anoIngresso) {
        this.anoIngresso = anoIngresso;
        return this;
    }

    public void setAnoIngresso(Integer anoIngresso) {
        this.anoIngresso = anoIngresso;
    }

    public Integer getSemestreIngresso() {
        return semestreIngresso;
    }

    public Turma semestreIngresso(Integer semestreIngresso) {
        this.semestreIngresso = semestreIngresso;
        return this;
    }

    public void setSemestreIngresso(Integer semestreIngresso) {
        this.semestreIngresso = semestreIngresso;
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public Turma alunos(Set<Aluno> alunos) {
        this.alunos = alunos;
        return this;
    }

    public Turma addAluno(Aluno aluno) {
        this.alunos.add(aluno);
        aluno.setTurma(this);
        return this;
    }

    public Turma removeAluno(Aluno aluno) {
        this.alunos.remove(aluno);
        aluno.setTurma(null);
        return this;
    }

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Set<Aula> getAulas() {
        return aulas;
    }

    public Turma aulas(Set<Aula> aulas) {
        this.aulas = aulas;
        return this;
    }

    public Turma addAula(Aula aula) {
        this.aulas.add(aula);
        aula.setTurma(this);
        return this;
    }

    public Turma removeAula(Aula aula) {
        this.aulas.remove(aula);
        aula.setTurma(null);
        return this;
    }

    public void setAulas(Set<Aula> aulas) {
        this.aulas = aulas;
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
        Turma turma = (Turma) o;
        if (turma.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), turma.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Turma{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            ", anoIngresso=" + getAnoIngresso() +
            ", semestreIngresso=" + getSemestreIngresso() +
            "}";
    }
}
