package com.edu.hrglass.model;

import java.time.LocalDate;

import java.util.List;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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


    @JsonManagedReference(value = "dept-col")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departamento")
    private Departamento departamento;


    @JsonManagedReference(value = "col-crach")
    @OneToMany(mappedBy = "colaborador",cascade = jakarta.persistence.CascadeType.ALL)
    private List<Cracha> crachaList;
    
}