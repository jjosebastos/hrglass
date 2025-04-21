package com.edu.hrglass.controller;

import com.edu.hrglass.exception.ColaboradorNotFoundException;
import com.edu.hrglass.model.Colaborador;
import com.edu.hrglass.model.ColaboradorFilter;
import com.edu.hrglass.repository.ColaboradorRepository;
import com.edu.hrglass.specification.ColaboradorSpecification;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.hibernate.query.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {
    
    private final Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @GetMapping

    public org.springframework.data.domain.Page<Colaborador> index(
        ColaboradorFilter filter,
        @PageableDefault(size = 10, sort = "date", direction = org.springframework.data.domain.Sort.Direction.DESC) Pageable pageable) {

        var specification = ColaboradorSpecification.withFilters(filter);

        return colaboradorRepository.findAll(specification, pageable) ;
    }
      
    

    // GET BY ID

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

    @PostMapping
    @CacheEvict(value = "colaboradores", allEntries = true)
    public ResponseEntity<Colaborador> create(@RequestBody Colaborador colaborador) {
        log.info("Cadastrando colaborador: " + colaborador.getNome());
        colaboradorRepository.save(colaborador);
        return ResponseEntity.status(HttpStatus.CREATED).body(colaborador);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("Removendo colaborador com ID: " + id);
        colaboradorRepository.delete(getColaborador(id));
    }

    @PutMapping("/{id}")
    public Colaborador update(@PathVariable Long id, @RequestBody Colaborador colaborador) {
        log.info("Atualizando colaborador com ID: " + id);
        colaboradorRepository.delete(getColaborador(id));
        colaborador.setId(id);
        colaboradorRepository.save(colaborador);
        return colaborador;
    }

    private Colaborador getColaborador(Long id) {
        return colaboradorRepository.findById(id)
            .orElseThrow(() -> new ColaboradorNotFoundException("Colaborador não encontrado"));
               
    }
}
