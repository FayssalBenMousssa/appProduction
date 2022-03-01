package dev.fenix.application.production.logistic.repository;

import dev.fenix.application.production.logistic.model.Depot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepotRepository extends JpaRepository<Depot, Long> {
    Iterable<Depot> findByActiveTrue();
}
