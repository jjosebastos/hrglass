package com.edu.hrglass.model;

import java.time.LocalDate;

public class Cracha {
    private Long idCracha;
    private int cracha;
    private LocalDate dataCriacao;
    private String status;
    private Long idColab;

    public Cracha(Long idCracha, int cracha, String status, Long idColab) {
        this.idCracha = idCracha;
        this.cracha = cracha;
        this.dataCriacao = LocalDate.now();
        this.status = status;
        this.idColab = idColab;
    }

    public Long getIdCracha() {
        return idCracha;
    }

    public void setIdCracha(Long idCracha) {
        this.idCracha = idCracha;
    }

    public int getCracha() {
        return cracha;
    }

    public void setCracha(int cracha) {
        this.cracha = cracha;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getIdColab() {
        return idColab;
    }

    public void setIdColab(Long idColab) {
        this.idColab = idColab;
    }
}
