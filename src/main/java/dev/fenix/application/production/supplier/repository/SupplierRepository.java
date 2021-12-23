package dev.fenix.application.production.supplier.repository;

import dev.fenix.application.production.supplier.model.Supplier;
import dev.fenix.application.production.supplier.model.SupplierClassification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository <Supplier,Long> {

    Iterable<SupplierClassification> findByActiveTrue();

    Supplier findTopByOrderByIdDesc();
}
