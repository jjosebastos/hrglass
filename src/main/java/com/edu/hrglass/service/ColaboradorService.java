package com.edu.hrglass.service;

import com.edu.hrglass.dto.ColaboradorDto;
import com.edu.hrglass.dto.ColaboradorUpdate;
import com.edu.hrglass.model.Colaborador;
import org.springframework.http.ResponseEntity;

public interface ColaboradorService {
    ResponseEntity<ColaboradorDto> update(Long id, ColaboradorUpdate colaborador);
    void deleteById(Long id);
}
