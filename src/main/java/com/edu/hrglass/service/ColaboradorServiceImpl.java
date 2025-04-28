package com.edu.hrglass.service;

import com.edu.hrglass.dto.ColaboradorDto;
import com.edu.hrglass.dto.ColaboradorUpdate;
import com.edu.hrglass.model.Colaborador;
import com.edu.hrglass.model.Cracha;
import com.edu.hrglass.model.Departamento;
import com.edu.hrglass.repository.ColaboradorRepository;
import com.edu.hrglass.repository.DepartamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ColaboradorServiceImpl implements ColaboradorService {
    @Autowired
    private final ColaboradorRepository colaboradorRepository;
    @Autowired
    private final DepartamentoRepository departamentoRepository;

    @Transactional
    @Override
    public ResponseEntity<ColaboradorDto> update(Long id, ColaboradorUpdate input) {
        Optional<Colaborador> found = colaboradorRepository.findById(id);
        if(found.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        Colaborador colab = found.get();
        colab.setNome(input.getNome());
        colab.setRa(input.getRa());
        colab.setSexo(input.getSexo());
        input.setDataNascimento(colab.getDataNascimento());

        if(input.getDepartamentoId() != null){
            Departamento depto =  departamentoRepository.findById(input.getDepartamentoId())
                    .orElseThrow(EntityNotFoundException::new);
            colab.setDepartamento(depto);
        }
        Colaborador updatedColaborador = colaboradorRepository.save(colab);
        return ResponseEntity.ok(convertToDTO(updatedColaborador));
    }

    @Override
    public void deleteById(Long id) {
        this.colaboradorRepository.deleteById(id);
    }

    private ColaboradorDto convertToDTO(Colaborador colaborador) {
        return ColaboradorDto.builder()
                .id(colaborador.getId())
                .ra(colaborador.getRa())
                .nome(colaborador.getNome())
                .sexo(colaborador.getSexo())
                .dataNascimento(colaborador.getDataNascimento())
                .departamentoId(colaborador.getDepartamento() != null ?
                        colaborador.getDepartamento().getIdDepartamento() : null)
                .build();
    }
}
