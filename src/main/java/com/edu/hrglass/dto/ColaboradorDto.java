package com.edu.hrglass.dto;

import com.edu.hrglass.model.Departamento;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ColaboradorDto {
    @JsonProperty("idColaborador")
    private Long id;
    @JsonProperty("ra")
    private String ra;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("sexo")
    private String sexo;
    @JsonProperty("dataNascimento")
    private LocalDate dataNascimento;
    @JsonProperty("departamentoId")
    private Long departamentoId;
}
