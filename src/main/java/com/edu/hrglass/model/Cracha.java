package com.edu.hrglass.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.*;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "colaborador_id")
    @JsonBackReference(value = "col-crach")
    private Colaborador colaborador;

}
