package dev.fenix.application.production.supplier.repository;

import dev.fenix.application.production.supplier.model.SupplierClassification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierClassificationRepository
    extends JpaRepository<SupplierClassification, Long> {

  Iterable<SupplierClassification> findByActiveTrue();

  SupplierClassification findTopByOrderByIdDesc();

    SupplierClassification findOneById(long l);
}
