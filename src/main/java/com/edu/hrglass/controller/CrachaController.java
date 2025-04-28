package com.edu.hrglass.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.hrglass.dto.CrachaDto;
import com.edu.hrglass.model.Colaborador;
import com.edu.hrglass.model.Cracha;
import com.edu.hrglass.model.CrachaFilter;
import com.edu.hrglass.repository.ColaboradorRepository;
import com.edu.hrglass.repository.CrachaRepository;
import com.edu.hrglass.specification.CrachaSpecification;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/cracha")
public class CrachaController {
        
    @Autowired
        private CrachaRepository crachaRepository;
    @Autowired
        private ColaboradorRepository colaboradorRepository;

        @PostMapping
        @CacheEvict
        public ResponseEntity<Cracha> create(@RequestBody @Valid CrachaDto input) {
            if(input.getIdCracha() != null){
                return ResponseEntity.badRequest().build();
            }

             Colaborador colaborador = colaboradorRepository.findById(input.getColaborador().getId())
            .orElseThrow(() -> new EntityNotFoundException("Colaborador não encontrado"));

            Cracha cracha = new Cracha(null, input.getCracha(), input.getDataCriacao(), input.getStatusCracha(), colaborador);
            var saved = this.crachaRepository.save(cracha);
            return ResponseEntity.created(URI.create("/cracha/" + saved.getIdCracha())).body(saved);
        }

        @GetMapping("/{id}")
        @Cacheable("colaboradorById")
        public ResponseEntity<Optional<Cracha>> getById(@PathVariable Long id){
            var result = this.crachaRepository.findById(id);

            if(result.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(result);
        }

        @GetMapping
        public org.springframework.data.domain.Page<Cracha> index(
            CrachaFilter filter,
            @PageableDefault(size = 10, sort = "cracha", 
            direction = org.springframework.data.domain.Sort.Direction.DESC) Pageable pageable){
                
                var specification = CrachaSpecification.withFilters(filter);

                return crachaRepository.findAll(specification, pageable) ;
            }
                
        @DeleteMapping("/{id}")
        public ResponseEntity<Cracha> destroy(@PathVariable Long id){
            this.crachaRepository.deleteById(id);
            return ResponseEntity.noContent().build();

        }

        @PutMapping("/{id}")
        public ResponseEntity<Cracha> update(@PathVariable Long id, @RequestBody @Valid CrachaDto input) {
            if(id == null){
                return ResponseEntity.badRequest().build();
            }
            
            var result = this.crachaRepository.
                    findById(id).orElseThrow(()-> new EntityNotFoundException("Cracha não encontrado"));
            var colab = this.colaboradorRepository.findById(input.getColaborador().getId()).orElseThrow(() -> new EntityNotFoundException("Colaborador não encontrado"));

            result.setCracha(input.getCracha());
            result.setDataCriacao(input.getDataCriacao());
            result.setColaborador(colab);
            result.setStatusCracha(input.getStatusCracha());

            Cracha savedCracha = this.crachaRepository.save(result);
            
            return ResponseEntity.ok(savedCracha);
        }

}   
