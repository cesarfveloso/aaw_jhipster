package com.aaw.aula3.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Aula.
 */
@Entity
@Table(name = "aula")
public class Aula implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "horario_inicial")
    private String horarioInicial;

    @Column(name = "horario_final")
    private String horarioFinal;

    @OneToMany(mappedBy = "aula")
    @JsonIgnore
    private Set<Falta> faltas = new HashSet<>();

    @ManyToOne
    private Turma turma;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHorarioInicial() {
        return horarioInicial;
    }

    public Aula horarioInicial(String horarioInicial) {
        this.horarioInicial = horarioInicial;
        return this;
    }

    public void setHorarioInicial(String horarioInicial) {
        this.horarioInicial = horarioInicial;
    }

    public String getHorarioFinal() {
        return horarioFinal;
    }

    public Aula horarioFinal(String horarioFinal) {
        this.horarioFinal = horarioFinal;
        return this;
    }

    public void setHorarioFinal(String horarioFinal) {
        this.horarioFinal = horarioFinal;
    }

    public Set<Falta> getFaltas() {
        return faltas;
    }

    public Aula faltas(Set<Falta> faltas) {
        this.faltas = faltas;
        return this;
    }

    public Aula addFalta(Falta falta) {
        this.faltas.add(falta);
        falta.setAula(this);
        return this;
    }

    public Aula removeFalta(Falta falta) {
        this.faltas.remove(falta);
        falta.setAula(null);
        return this;
    }

    public void setFaltas(Set<Falta> faltas) {
        this.faltas = faltas;
    }

    public Turma getTurma() {
        return turma;
    }

    public Aula turma(Turma turma) {
        this.turma = turma;
        return this;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
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
        Aula aula = (Aula) o;
        if (aula.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), aula.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Aula{" +
            "id=" + getId() +
            ", horarioInicial='" + getHorarioInicial() + "'" +
            ", horarioFinal='" + getHorarioFinal() + "'" +
            "}";
    }
}
