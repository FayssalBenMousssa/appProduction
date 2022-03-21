package dev.fenix.application.production.product.repository;

import dev.fenix.application.production.product.model.Formula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormulaRepository extends JpaRepository<Formula, Long> {

    Iterable<Formula> findByActiveTrue();

    Formula findTopByOrderByIdDesc();

    int countByActiveTrue();

    Page<Formula> findAllByNameContainsAndActiveTrue(String value, Pageable paging);

    int countByNameContainsAndActiveTrue(String value);

    Page<Formula> findByActiveTrue(Pageable paging);
}
