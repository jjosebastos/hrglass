package com.edu.hrglass.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Colaborador {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "ra não pode ser vazio.")
    @Size (min = 1, max = 12)
    private String ra;
    @NotBlank(message = "Nome não pode ser vazio.")
    private String nome;
    @Size(max = 1, message = "Sexo só pode conter um caractere.")
    private String sexo;
    @PastOrPresent(message = "A data de nascimento tem que ser no passado")
    private LocalDate dataNascimento;
    @NotNull(message = "O valir não pode ser nulo.")
    private Long idDepartamento;

    
}