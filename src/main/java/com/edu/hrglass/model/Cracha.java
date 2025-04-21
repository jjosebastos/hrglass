package com.edu.hrglass.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cracha {
    
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idCracha;
    
    @NotBlank(message = "valor do crachá não pode ser vazio.")
    private String cracha;

    @PastOrPresent(message = "data da criação não pode ser futura.")
    private LocalDate dataCriacao;

    @NotNull(message = "valor do status não pode ser vazio.")
    @Enumerated
    private StatusCracha statusCracha;

    @ManyToOne
    @JsonBackReference("produto")
    @JoinColumn(name = "colaborador_id", nullable = false)
    private Colaborador colaborador;
   
}
