package com.edu.hrglass.service;

import com.edu.hrglass.dto.DepartamentoDto;
import com.edu.hrglass.model.Departamento;
import com.edu.hrglass.repository.DepartamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Transactional
    @Override
    public ResponseEntity<DepartamentoDto> update(Long id, DepartamentoDto departamentoUpdate) {
        Optional<Departamento> departamento = departamentoRepository.findById(id);
        if(departamento.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Departamento depto = departamento.get();

        depto.setDescricao(departamentoUpdate.getDescricao());
        depto.setNome(departamentoUpdate.getNome());
        Departamento updated = departamentoRepository.save(depto);

        return ResponseEntity.ok(mapperToDto(updated));
    }


    private DepartamentoDto mapperToDto(Departamento departamento) {
        return DepartamentoDto.builder()
                .nome(departamento.getNome())
                .descricao(departamento.getDescricao())
                .build();
    }
}
