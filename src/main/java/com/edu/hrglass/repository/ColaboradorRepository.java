package com.edu.hrglass.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.edu.hrglass.model.Colaborador;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long>, JpaSpecificationExecutor<Colaborador>{

    @Query("SELECT c FROM Colaborador c LEFT JOIN FETCH c.departamento WHERE c.id = :id")
    Optional<Colaborador> findByIdWithDepartamento(@Param("id") Long id);

    Page<Colaborador> findByRaContainingIgnoreCase(String ra, Pageable pageable);
}
