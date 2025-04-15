package com.edu.hrglass.controller;

import com.edu.hrglass.exception.ColaboradorNotFoundException;
import com.edu.hrglass.model.Colaborador;
import com.edu.hrglass.repository.ColaboradorRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;



@RestController()
@RequestMapping("/colaboradores")
public class ColaboradorController {
    
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @GetMapping("/all")
    public List<Colaborador> findAll() {
        return colaboradorRepository.findAll();
    }
    

    // GET BY ID
    @GetMapping("/{id}")
    public Colaborador getById(@PathVariable Long id) {
        log.info("Buscando colaborador por ID: " + id);
        return getColaborador(id);
    }

    // POST
    @PostMapping
    public ResponseEntity<Colaborador> create(@RequestBody Colaborador colaborador) {
        log.info("Cadastrando colaborador: " + colaborador.getNome());
        colaboradorRepository.save(colaborador);
        return ResponseEntity.status(HttpStatus.CREATED).body(colaborador);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("Removendo colaborador com ID: " + id);
        colaboradorRepository.delete(getColaborador(id));
    }

    // PUT (Update)
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
