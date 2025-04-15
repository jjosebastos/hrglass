package com.edu.hrglass.config;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edu.hrglass.model.Colaborador;
import com.edu.hrglass.model.Cracha;
import com.edu.hrglass.model.Departamento;
import com.edu.hrglass.model.StatusCracha;
import com.edu.hrglass.repository.ColaboradorRepository;
import com.edu.hrglass.repository.CrachaRepository;
import com.edu.hrglass.repository.DepartamentoRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DatabaseSeeder {
    
    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private CrachaRepository crachaRepository;

        @PostConstruct
        public void init(){   
                departamentoRepository.saveAll(List.of(
                        Departamento.builder().nome("RH").descricao("DEPARTAMENTO DE RECURSOS HUMANOS").build(),
                        Departamento.builder().nome("TI").descricao("DEPARTAMENTO DE SUPORTE TECNICO").build(),
                        Departamento.builder().nome("FINANCEIRO").descricao("DEPARTAMENTO DA AREA FINANCEIRA") .build()
                        ));
        

                colaboradorRepository.saveAll(List.of(
                    Colaborador.builder().ra("1234567").nome("JOSÉ BEZERRA").sexo("M").dataNascimento(LocalDate.of(2000, 8,10)).idDepartamento(1L).build(),
                        Colaborador.builder().ra("1234568").nome("NICOLAS").sexo("M").dataNascimento(LocalDate.of(2004, 7,27)).idDepartamento(1L).build(),
                        Colaborador.builder().ra("1234569").nome("HENRY").sexo("M").dataNascimento(LocalDate.of(2005, 4,7)).idDepartamento(1L).build()
                        ));


                crachaRepository.saveAll(List.of(
                    Cracha.builder().cracha("1245123").dataCriacao(LocalDate.of(2024, 3, 28)).statusCracha(StatusCracha.ATIVO).build(),
                    Cracha.builder().cracha("1246789").dataCriacao(LocalDate.of(2024, 2, 10)).statusCracha(StatusCracha.BLOQUEADO).build(),
                    Cracha.builder().cracha("1234567").dataCriacao(LocalDate.of(2025, 4, 9)).statusCracha(StatusCracha.ATIVO).build()
                    ));
                    
    }

}


