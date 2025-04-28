package com.edu.hrglass.controller;

import java.util.List;
import java.util.Optional;

import com.edu.hrglass.model.DepartamentoFilter;
import com.edu.hrglass.service.DepartamentoService;
import com.edu.hrglass.specification.DepartamentoSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edu.hrglass.dto.DepartamentoDto;
import com.edu.hrglass.model.Departamento;
import com.edu.hrglass.repository.DepartamentoRepository;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/departamento")
public class DepartamentoController {
    
    private final Logger log = LoggerFactory.getLogger(getClass());
    

    @Autowired
    private DepartamentoRepository departamentoRepository;
    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping
    public Page<Departamento> index(
            DepartamentoFilter filter,
            @PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.DESC) Pageable pageable){
        var specification = DepartamentoSpecification.withFilters(filter);
        return departamentoRepository.findAll(specification, pageable);
    }

    @Cacheable("departamentoById")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Departamento>> getById(@PathVariable Long id) {
        var result = this.departamentoRepository.findById(id);
        if(result.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Departamento>> getAll() {
        return ResponseEntity.ok(this.departamentoRepository.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartamentoDto> update(@PathVariable Long id,
                                               @RequestBody @Valid DepartamentoDto input) {
        return departamentoService.update(id, input);
    }
    
    @PostMapping
    @CacheEvict
    public ResponseEntity<Departamento> create (@RequestBody @Valid DepartamentoDto input) {
        log.info("Cadastrando departamento...");
        Departamento saved = new Departamento(null, input.getNome(), input.getDescricao(), null);
        departamentoRepository.save(saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Departamento> destroy(@PathVariable Long id){
        log.info("Excluindo o departamento...");
        departamentoRepository.deleteById(id);        
        return ResponseEntity.noContent().build();
        
    }
}
