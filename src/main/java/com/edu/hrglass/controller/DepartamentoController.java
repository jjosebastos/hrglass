package com.edu.hrglass.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.hrglass.dto.DepartamentoDto;
import com.edu.hrglass.model.Departamento;
import com.edu.hrglass.repository.DepartamentoRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/departamento")
public class DepartamentoController {
    
    private final Logger log = LoggerFactory.getLogger(getClass());
    

    @Autowired
    private DepartamentoRepository departamentoRepository;

    
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
    
    
    @PostMapping
    @CacheEvict
    public ResponseEntity<Departamento> create (@RequestBody @Valid DepartamentoDto input) {
        log.info("Cadastrando departamento...");
        Departamento saved = new Departamento(null, input.getNome(), input.getDescricao());
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
