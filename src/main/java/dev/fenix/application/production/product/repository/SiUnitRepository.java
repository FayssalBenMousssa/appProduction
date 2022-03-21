package dev.fenix.application.production.product.repository;

import dev.fenix.application.production.product.model.ProductionUnit;
import dev.fenix.application.production.product.model.SiUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiUnitRepository extends JpaRepository<SiUnit, Long> {
    Iterable<SiUnit> findByActiveTrue();

    SiUnit findOneById(long l);
}
