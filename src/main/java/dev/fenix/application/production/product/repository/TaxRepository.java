package dev.fenix.application.production.product.repository;


import dev.fenix.application.production.product.model.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxRepository extends JpaRepository<Tax, Long> {
  Iterable<Tax> findByActiveTrue();

  Tax findOneById(long l);


  Tax findTopByOrderByIdDesc();
}
