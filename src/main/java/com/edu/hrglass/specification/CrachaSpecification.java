package com.edu.hrglass.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.edu.hrglass.model.Cracha;
import com.edu.hrglass.model.CrachaFilter;

import jakarta.persistence.criteria.Predicate;

public class CrachaSpecification {
    
    public static Specification<Cracha> withFilters(CrachaFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.cracha() != null) {
                predicates.add(
                        cb.like(root.get("cracha"), filter.cracha()));
            }

            if (filter.startDate() != null && filter.endDate() != null) {
                predicates.add(
                        cb.between(root.get("dataCriacao"), filter.startDate(), filter.endDate()));
            }

            if (filter.startDate() == null) {
                predicates.add(
                        cb.equal(root.get("dataCriacao"), filter.startDate()));
            }
             
            if (filter.statusCracha() != null) {
                predicates.add(cb.equal(
                    root.get("statusCracha"),
                    filter.statusCracha()));
            }

            var arrayPredicates = predicates.toArray(new Predicate[0]);
            return cb.and(arrayPredicates);
        };
    }
}
