package com.aaw.aula3.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Aluno.
 */
@Entity
@Table(name = "aluno")
public class Aluno implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "tipo_ingresso")
    private String tipoIngresso;

    @Column(name = "bolsista")
    private Boolean bolsista;

    @Column(name = "data_ingresso")
    private String dataIngresso;

    @Column(name = "observacoes")
    private String observacoes;

    @OneToMany(mappedBy = "aluno")
    @JsonIgnore
    private Set<Falta> faltas = new HashSet<>();

    @OneToMany(mappedBy = "aluno")
    @JsonIgnore
    private Set<Nota> notas = new HashSet<>();

    @OneToMany(mappedBy = "aluno")
    @JsonIgnore
    private Set<Entrega> entregas = new HashSet<>();

    @OneToOne(mappedBy = "aluno")
    @JsonIgnore
    private Usuario usuario;

    @ManyToOne
    private Turma turma;

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

    public Aluno nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoIngresso() {
        return tipoIngresso;
    }

    public Aluno tipoIngresso(String tipoIngresso) {
        this.tipoIngresso = tipoIngresso;
        return this;
    }

    public void setTipoIngresso(String tipoIngresso) {
        this.tipoIngresso = tipoIngresso;
    }

    public Boolean isBolsista() {
        return bolsista;
    }

    public Aluno bolsista(Boolean bolsista) {
        this.bolsista = bolsista;
        return this;
    }

    public void setBolsista(Boolean bolsista) {
        this.bolsista = bolsista;
    }

    public String getDataIngresso() {
        return dataIngresso;
    }

    public Aluno dataIngresso(String dataIngresso) {
        this.dataIngresso = dataIngresso;
        return this;
    }

    public void setDataIngresso(String dataIngresso) {
        this.dataIngresso = dataIngresso;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public Aluno observacoes(String observacoes) {
        this.observacoes = observacoes;
        return this;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Set<Falta> getFaltas() {
        return faltas;
    }

    public Aluno faltas(Set<Falta> faltas) {
        this.faltas = faltas;
        return this;
    }

    public Aluno addFalta(Falta falta) {
        this.faltas.add(falta);
        falta.setAluno(this);
        return this;
    }

    public Aluno removeFalta(Falta falta) {
        this.faltas.remove(falta);
        falta.setAluno(null);
        return this;
    }

    public void setFaltas(Set<Falta> faltas) {
        this.faltas = faltas;
    }

    public Set<Nota> getNotas() {
        return notas;
    }

    public Aluno notas(Set<Nota> notas) {
        this.notas = notas;
        return this;
    }

    public Aluno addNota(Nota nota) {
        this.notas.add(nota);
        nota.setAluno(this);
        return this;
    }

    public Aluno removeNota(Nota nota) {
        this.notas.remove(nota);
        nota.setAluno(null);
        return this;
    }

    public void setNotas(Set<Nota> notas) {
        this.notas = notas;
    }

    public Set<Entrega> getEntregas() {
        return entregas;
    }

    public Aluno entregas(Set<Entrega> entregas) {
        this.entregas = entregas;
        return this;
    }

    public Aluno addEntrega(Entrega entrega) {
        this.entregas.add(entrega);
        entrega.setAluno(this);
        return this;
    }

    public Aluno removeEntrega(Entrega entrega) {
        this.entregas.remove(entrega);
        entrega.setAluno(null);
        return this;
    }

    public void setEntregas(Set<Entrega> entregas) {
        this.entregas = entregas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Aluno usuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Turma getTurma() {
        return turma;
    }

    public Aluno turma(Turma turma) {
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
        Aluno aluno = (Aluno) o;
        if (aluno.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), aluno.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Aluno{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", tipoIngresso='" + getTipoIngresso() + "'" +
            ", bolsista='" + isBolsista() + "'" +
            ", dataIngresso='" + getDataIngresso() + "'" +
            ", observacoes='" + getObservacoes() + "'" +
            "}";
    }
}
