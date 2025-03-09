package com.edu.hrglass.model;

import java.util.Date;
import java.util.Objects;

public class Colaborador {
    private Long id;
    private String ra;
    private String nome;
    private String sexo;
    private Date dataNascimento;
    private Long idDepartamento;

    public Colaborador(Long id, String ra, String nome, String sexo, Date dataNascimento, Long idDepartamento) {
        this.id = id;
        this.ra = ra;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.idDepartamento = idDepartamento;
    }

    public Long getId() {
        return id;
    }

    public String getRa() {
        return ra;
    }

    public String getNome() {
        return nome;
    }

    public String getSexo() {
        return sexo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public Long getIdDepartamento() {
        return idDepartamento;
    }

    @Override
    public String toString() {
        return "Colaborador{" +
                "id=" + id +
                ", ra='" + ra + '\'' +
                ", nome='" + nome + '\'' +
                ", sexo='" + sexo + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", idDepartamento=" + idDepartamento +
                '}';
    }
}
