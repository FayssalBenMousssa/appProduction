package dev.fenix.application.production.product.repository;

import dev.fenix.application.production.product.model.Classification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificationRepository extends JpaRepository<Classification, Long> {
    Iterable<Classification> findByActiveTrue();



    Iterable<Classification> findByActiveTrueAndParent(Classification parent);

    Iterable<Classification> findByActiveTrueAndLevel(Long level);
}
