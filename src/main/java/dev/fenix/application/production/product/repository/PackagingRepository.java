package dev.fenix.application.production.product.repository;

import dev.fenix.application.production.product.model.Packaging;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackagingRepository extends JpaRepository<Packaging, Long> {
  Iterable<Packaging> findByActiveTrue();

  Page<Packaging> findByActiveTrue(Pageable paging);

  int countByActiveTrue();

  Page<Packaging> findAllByNameContainsAndActiveTrue(String value, Pageable paging);

  int countByNameContainsAndActiveTrue(String value);

    Packaging findTopByOrderByIdDesc();
}
