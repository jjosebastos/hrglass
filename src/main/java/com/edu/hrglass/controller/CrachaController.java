package com.edu.hrglass.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.hrglass.dto.CrachaDto;
import com.edu.hrglass.model.Cracha;
import com.edu.hrglass.repository.CrachaRepository;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.Optional;

import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/cracha")
@RequiredArgsConstructor
public class CrachaController {
        @Autowired
        private CrachaRepository crachaRepository;
        @PostMapping
        @CacheEvict
        public ResponseEntity<Cracha> create(@RequestBody @Valid CrachaDto input) {
            Cracha cracha = new Cracha(null, input.getCracha(), input.getDataCriacao(), input.getStatusCracha(), input.getColaborador());
            var saved = this.crachaRepository.save(cracha);
            return ResponseEntity.created(URI.create("/cracha/" + saved.getIdCracha())).body(saved);
        }

        @GetMapping("/{id}")
        @Cacheable("colaboradorById")
        public ResponseEntity<Optional<Cracha>> getById(@PathVariable Long id){
            return ResponseEntity.status(HttpStatus.OK).body(this.crachaRepository.findById(id));
        }

        
        

}   
