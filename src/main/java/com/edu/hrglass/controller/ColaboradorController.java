package com.edu.hrglass.controller;

import com.edu.hrglass.dto.ColaboradorDto;
import com.edu.hrglass.dto.ColaboradorUpdate;
import com.edu.hrglass.exception.ColaboradorNotFoundException;
import com.edu.hrglass.model.Colaborador;
import com.edu.hrglass.model.ColaboradorFilter;
import com.edu.hrglass.model.Departamento;
import com.edu.hrglass.repository.ColaboradorRepository;
import com.edu.hrglass.repository.DepartamentoRepository;
import com.edu.hrglass.service.ColaboradorService;
import com.edu.hrglass.specification.ColaboradorSpecification;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {

    @Autowired
    private ColaboradorService colaboradorService;
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private ColaboradorRepository colaboradorRepository;
    @Autowired
    private DepartamentoRepository departamentoRepository;
    @PostMapping(consumes = "application/json")
    @CacheEvict
    public ResponseEntity<Colaborador> create(@RequestBody ColaboradorDto input) {
        if (input.getId() == null) {
            Colaborador colaborador = new Colaborador(null, input.getRa(), input.getNome(), input.getSexo(),
                    input.getDataNascimento(), null, null);
            colaboradorRepository.save(colaborador);
            return ResponseEntity.status(HttpStatus.CREATED).body(colaborador);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }
    @GetMapping
    public org.springframework.data.domain.Page<Colaborador> index(
        ColaboradorFilter filter,
        @PageableDefault(size = 10, sort = "ra", direction = org.springframework.data.domain.Sort.Direction.DESC) Pageable pageable) {
        var specification = ColaboradorSpecification.withFilters(filter);
        return colaboradorRepository.findAll(specification, pageable) ;
    }
      
    @GetMapping("/{id}")
    @Cacheable("colaboradorById")
    public Colaborador getById(@PathVariable Long id) {
        return getColaborador(id);
    }

     @Operation(
            summary = "Cadastrar colaborador", description = "Insere um colaborador..", responses = {
                    @ApiResponse(responseCode = "201"),
                    @ApiResponse(responseCode = "400"),
            })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("Excluindo colaborador...: {}", id);
        this.colaboradorRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    @Operation ( summary = "Atualizar colaborador", description = "Atualizar colaborador com base em um id",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400")
            }
    )
    public ResponseEntity<ColaboradorDto> update(@PathVariable Long id, @RequestBody ColaboradorUpdate input){
        return colaboradorService.update(id, input);
    }

    private Colaborador getColaborador(Long id) {
        return colaboradorRepository.findById(id)
            .orElseThrow(() -> new ColaboradorNotFoundException("Colaborador não encontrado"));
    }

}

