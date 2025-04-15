package com.edu.hrglass.model;

public enum StatusCracha {
    ATIVO("1"),
    SUSPENSO("2"),
    BLOQUEADO("3");

    private final String stCracha;

    private StatusCracha (String stCracha){
        this.stCracha = stCracha;
    }

    public String getStCracha() {
        return stCracha;
    }
}
