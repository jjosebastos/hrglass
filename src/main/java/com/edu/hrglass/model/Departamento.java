package com.edu.hrglass.model;

import java.util.List;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class Departamento {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idDepartamento;

    @NotBlank(message = "valor de nome não pode ser vazio.")
    private String nome;
    @NotBlank(message = "valor de descrição não pode ser vazio")
    @Size(min = 30, max = 255, message = "valor do campo descrição não pode exceder 255 caracteres")
    private String descricao;

    @JsonBackReference(value = "dept-col")
    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL)
    private List<Colaborador> colaboradoresList; 
   
}
