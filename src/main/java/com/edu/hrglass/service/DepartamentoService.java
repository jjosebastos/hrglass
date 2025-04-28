package com.edu.hrglass.service;


import com.edu.hrglass.dto.DepartamentoDto;
import org.springframework.http.ResponseEntity;

public interface DepartamentoService {
    ResponseEntity<DepartamentoDto> update(Long id, DepartamentoDto departamentoUpdate);
}
