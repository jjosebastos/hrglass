package com.edu.hrglass.controller;

import com.edu.hrglass.model.Colaborador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ColaboradorController {
    
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final List<Colaborador> repository = new ArrayList<>();

    // GET ALL
    @GetMapping("/colaboradores")
    public List<Colaborador> index() {
        return repository;
    }

    // GET BY ID
    @GetMapping("/colaboradores/{id}")
    public Colaborador getById(@PathVariable Long id) {
        log.info("Buscando colaborador por ID: " + id);
        return getColaborador(id);
    }

    // POST
    @PostMapping("/colaboradores")
    public ResponseEntity<Colaborador> create(@RequestBody Colaborador colaborador) {
        log.info("Cadastrando colaborador: " + colaborador.getNome());
        repository.add(colaborador);
        return ResponseEntity.status(HttpStatus.CREATED).body(colaborador);
    }

    // DELETE
    @DeleteMapping("/colaboradores/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("Removendo colaborador com ID: " + id);
        repository.remove(getColaborador(id));
    }

    // PUT (Update)
    @PutMapping("/colaboradores/{id}")
    public Colaborador update(@PathVariable Long id, @RequestBody Colaborador colaborador) {
        log.info("Atualizando colaborador com ID: " + id);
        repository.remove(getColaborador(id));
        colaborador.setId(id);
        repository.add(colaborador);
        return colaborador;
    }

    private Colaborador getColaborador(Long id) {
        return repository.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
