package com.aaw.aula3.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Falta.
 */
@Entity
@Table(name = "falta")
public class Falta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data")
    private String data;

    @Column(name = "abono")
    private Boolean abono;

    @Column(name = "motivo")
    private String motivo;

    @ManyToOne
    private Aluno aluno;

    @ManyToOne
    private Aula aula;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public Falta data(String data) {
        this.data = data;
        return this;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Boolean isAbono() {
        return abono;
    }

    public Falta abono(Boolean abono) {
        this.abono = abono;
        return this;
    }

    public void setAbono(Boolean abono) {
        this.abono = abono;
    }

    public String getMotivo() {
        return motivo;
    }

    public Falta motivo(String motivo) {
        this.motivo = motivo;
        return this;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Falta aluno(Aluno aluno) {
        this.aluno = aluno;
        return this;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Aula getAula() {
        return aula;
    }

    public Falta aula(Aula aula) {
        this.aula = aula;
        return this;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
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
        Falta falta = (Falta) o;
        if (falta.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), falta.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Falta{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            ", abono='" + isAbono() + "'" +
            ", motivo='" + getMotivo() + "'" +
            "}";
    }
}
