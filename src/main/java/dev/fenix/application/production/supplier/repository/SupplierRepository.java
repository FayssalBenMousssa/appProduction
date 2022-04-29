package dev.fenix.application.production.supplier.repository;

import dev.fenix.application.production.supplier.model.Supplier;
import dev.fenix.application.production.supplier.model.SupplierClassification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

  Iterable<SupplierClassification> findByActiveTrue();

  Supplier findTopByOrderByIdDesc();

  int countByActiveTrue();

  Page<Supplier> findAllBySocialReasonContainsAndActiveTrue(String value, Pageable paging);

  int countBySocialReasonContainsAndActiveTrue(String value);

  Page<Supplier> findByActiveTrue(Pageable paging);

  Page<Supplier> findAllBySocialReasonContainingAndActiveTrue(String socialReason, Pageable paging);
  //
  Page<Supplier> findAllByAndActiveTrue(String socialReason, Pageable paging);
}
