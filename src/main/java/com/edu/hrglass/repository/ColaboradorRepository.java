package com.edu.hrglass.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.edu.hrglass.model.Colaborador;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long>, JpaSpecificationExecutor<Colaborador>{

    Page<Colaborador> findByRaContainingIgnoreCase(String ra, Pageable pageable);
}
