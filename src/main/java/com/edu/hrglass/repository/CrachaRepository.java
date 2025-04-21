package com.edu.hrglass.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.edu.hrglass.model.Cracha;

public interface CrachaRepository extends JpaRepository<Cracha, Long>, JpaSpecificationExecutor<Cracha>{
    
    Page<Cracha> findByCrachaContainingIgnoreCase(String cracha, Pageable pageable);

}
