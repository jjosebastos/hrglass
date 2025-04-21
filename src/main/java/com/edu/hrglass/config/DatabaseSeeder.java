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
                var departamento = List.of(
                        Departamento.builder().nome("RH").descricao("DEPARTAMENTO DE RECURSOS HUMANOS").build(),
                        Departamento.builder().nome("TI").descricao("DEPARTAMENTO DE SUPORTE TECNICO").build(),
                        Departamento.builder().nome("FINANCEIRO").descricao("DEPARTAMENTO DA AREA FINANCEIRA") .build()
                        );
                departamentoRepository.saveAll(departamento);

                var colaborador = List.of(
                        Colaborador.builder()
                        .ra("1234567").nome("JOSÉ BEZERRA")
                        .sexo("M").dataNascimento(LocalDate.of(2000, 8,10))
                        .idDepartamento(1L).build(),

                        Colaborador.builder().ra("1234568").nome("NICOLAS")
                        .sexo("M").dataNascimento(LocalDate.of(2004, 7,27))
                        .idDepartamento(1L).build(),
                        
                        Colaborador.builder()
                        .ra("1234569").nome("HENRY").sexo("M")
                        .dataNascimento(LocalDate.of(2005, 4,7))
                        .idDepartamento(1L).build()
                        );
                colaboradorRepository.saveAll(colaborador);

                    var cracha = List.of(
                        Cracha.builder().cracha("1245123").dataCriacao(LocalDate.of(2024, 3, 28))
                        .colaborador(colaborador.get(0)).statusCracha(StatusCracha.ATIVO).build(),
                        Cracha
                        .builder().cracha("1246789").dataCriacao(LocalDate.of(2024, 2, 10))
                        .colaborador(colaborador.get(1)).statusCracha(StatusCracha.BLOQUEADO).build(),
                        Cracha
                        .builder()
                        .cracha("1234567").dataCriacao(LocalDate.of(2025, 4, 9))
                        .colaborador(colaborador.get(2)).statusCracha(StatusCracha.ATIVO).build(),
                        Cracha
                        .builder()
                        .cracha("1234568").dataCriacao(LocalDate.of(2025, 3, 8))
                        .colaborador(colaborador.get(2)).statusCracha(StatusCracha.ATIVO).build(),
                        Cracha
                        .builder()
                        .cracha("1234569").dataCriacao(LocalDate.of(2025, 4, 9))
                        .colaborador(colaborador.get(1)).statusCracha(StatusCracha.ATIVO).build(),

                        Cracha
                        .builder()
                        .cracha("1234570").dataCriacao(LocalDate.of(2025, 4, 9))
                        .colaborador(colaborador.get(1)).statusCracha(StatusCracha.ATIVO).build(),
                        
                        
                        Cracha
                        .builder()
                        .cracha("1234571").dataCriacao(LocalDate.of(2025, 4, 9))
                        .colaborador(colaborador.get(2)).statusCracha(StatusCracha.ATIVO).build()
                        );
                crachaRepository.saveAll(cracha);
                    
    }

}


