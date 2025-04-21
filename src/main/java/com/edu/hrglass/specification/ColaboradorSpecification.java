package com.edu.hrglass.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.edu.hrglass.model.Colaborador;
import com.edu.hrglass.model.ColaboradorFilter;

import jakarta.persistence.criteria.Predicate;

public class ColaboradorSpecification {
    
     public static Specification<Colaborador> withFilters(ColaboradorFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.ra() != null) {
                predicates.add(
                        cb.like(root.get("ra"), filter.ra()));
            }

            if (filter.startDate() != null && filter.endDate() != null) {
                predicates.add(
                        cb.between(root.get("date"), filter.startDate(), filter.endDate()));
            }

            if (filter.startDate() != null && filter.endDate() == null) {
                predicates.add(
                        cb.equal(root.get("date"), filter.startDate()));
            }

            var arrayPredicates = predicates.toArray(new Predicate[0]);
            return cb.and(arrayPredicates);
        };
    }
}
