package com.edu.hrglass.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.hrglass.model.Departamento;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long>, JpaSpecificationExecutor<Departamento> {

    Page<Departamento> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
