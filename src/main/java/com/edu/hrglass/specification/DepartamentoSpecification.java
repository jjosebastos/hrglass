package com.edu.hrglass.specification;

import com.edu.hrglass.model.Departamento;
import com.edu.hrglass.model.DepartamentoFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class DepartamentoSpecification {

    public static Specification<Departamento> withFilters(DepartamentoFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.nome() != null) {
                predicates.add(
                        cb.equal(root.get("nome"), filter.nome().toLowerCase()));
            }

            if (filter.desc() != null) {
                predicates.add(
                        cb.equal(root.get("descricao"), filter.desc().toLowerCase()));
            }

            var arrayPredicates = predicates.toArray(new Predicate[0]);
            return cb.and(arrayPredicates);
        };
    }
}
