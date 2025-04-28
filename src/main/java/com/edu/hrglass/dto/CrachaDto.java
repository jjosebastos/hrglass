package com.edu.hrglass.dto;

import java.time.LocalDate;

import com.edu.hrglass.model.Colaborador;
import com.edu.hrglass.model.StatusCracha;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrachaDto {

    @JsonProperty("idCracha")
    private Long idCracha;
    @JsonProperty("cracha")
    private String cracha;
    @JsonProperty("dataCriacao")
    private LocalDate dataCriacao;
    @JsonProperty("statusCracha")
    private StatusCracha statusCracha;
    @JsonProperty("colaborador")
    private Colaborador colaborador;
}
