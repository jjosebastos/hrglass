package com.edu.hrglass.controller;

import com.edu.hrglass.model.Colaborador;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ColaboradorController {
    private final List<Colaborador> repository = new ArrayList<>();


    // POST
    @PostMapping("/colaboradores")
    public ResponseEntity<Colaborador> create(@RequestBody Colaborador colaborador) {

        if(colaborador.getId() == null){
            throw new NullPointerException();
        }
        System.out.println("Cadastrando colaborador..." + colaborador.getNome());
        repository.add(colaborador);
        return ResponseEntity.ok(colaborador);
    }
    // GET ALL
    @GetMapping("/colaboradores")
    public ResponseEntity<List<Colaborador>> index() {
        return ResponseEntity.status(200).body(repository);
    }

    // GET BY ID
    @GetMapping("/colaboradores/{id}")
    public ResponseEntity<Colaborador> getById(@PathVariable Long id) {
        System.out.println("Cadastrando colaborador..." + id);
        var colaborador = repository.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
        return colaborador.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/colaboradores/{id}")
    public ResponseEntity<Colaborador> delete(@PathVariable Long id) {
        System.out.println("Cadastrando colaborador..." + id);
        var colaborador = repository.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
        if(colaborador.isEmpty()) {
            return ResponseEntity.ok().build();
        }
        repository.remove(colaborador);
        return ResponseEntity.noContent().build();

    }


}
