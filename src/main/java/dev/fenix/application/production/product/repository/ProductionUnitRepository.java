package dev.fenix.application.production.product.repository;

import dev.fenix.application.production.product.model.ProductionUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionUnitRepository extends JpaRepository<ProductionUnit, Long> {}
