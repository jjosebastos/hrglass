package com.edu.hrglass.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ColaboradorUpdate {
    private String ra;
    private String nome;
    private String sexo;
    private LocalDate dataNascimento;
    private Long departamentoId;
}