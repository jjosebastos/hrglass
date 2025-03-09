package com.edu.hrglass.model;

public class Departamento {
    private Long idDepartamento;
    private String nome;
    private String descricao;

    public Departamento(Long idDepartamento, String nome, String descricao) {
        this.idDepartamento = idDepartamento;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Long getIdDepartamento() {
        return idDepartamento;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "idDepartamento=" + idDepartamento +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
