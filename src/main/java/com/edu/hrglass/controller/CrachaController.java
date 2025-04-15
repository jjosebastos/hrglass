package com.edu.hrglass.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.hrglass.model.Cracha;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/cracha")
public class CrachaController {
    
    @PostMapping("path")
    public ResponseEntity<Cracha> postMethodName(@RequestBody CrachaDto input) {
        //TODO: process POST request
        
        return entity;
    }
    
}
