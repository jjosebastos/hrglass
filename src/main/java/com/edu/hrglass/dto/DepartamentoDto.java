package com.edu.hrglass.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartamentoDto {
    @JsonProperty("idDepartamento")
    private Long idDepartamento;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("descricao")
    private String descricao;
}
