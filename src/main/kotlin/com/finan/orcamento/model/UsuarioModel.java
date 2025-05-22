package com.finan.orcamento.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name="usuario")
public class UsuarioModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name="nome_usuario")
    private String nomeUsuario;

    @JsonIgnore
    @OneToMany(mappedBy = "id")
    private List<OrcamentoModel> orcamentos = new ArrayList<>();

    public UsuarioModel(){}

    public UsuarioModel(Long id, String nomeUsuario, List<OrcamentoModel> orcamentos) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.orcamentos = orcamentos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public List<OrcamentoModel> getOrcamentos() {
        return orcamentos;
    }

    public void setOrcamentos(List<OrcamentoModel> orcamentos) {
        this.orcamentos = orcamentos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioModel that = (UsuarioModel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
