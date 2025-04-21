package com.edu.hrglass.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartamentoDto {
    @JsonProperty("idDepartamento")
    private Long idDepartamento;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("descricao")
    private String descricao;
}
