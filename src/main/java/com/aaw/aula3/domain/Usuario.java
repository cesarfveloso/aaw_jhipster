package com.aaw.aula3.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Usuario.
 */
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf")
    private Integer cpf;

    @Column(name = "ativo")
    private Boolean ativo;

    @ManyToOne
    private Permissao permissao;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCpf() {
        return cpf;
    }

    public Usuario cpf(Integer cpf) {
        this.cpf = cpf;
        return this;
    }

    public void setCpf(Integer cpf) {
        this.cpf = cpf;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public Usuario ativo(Boolean ativo) {
        this.ativo = ativo;
        return this;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public Usuario permissao(Permissao permissao) {
        this.permissao = permissao;
        return this;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
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
        Usuario usuario = (Usuario) o;
        if (usuario.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), usuario.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Usuario{" +
            "id=" + getId() +
            ", cpf=" + getCpf() +
            ", ativo='" + isAtivo() + "'" +
            "}";
    }
}
