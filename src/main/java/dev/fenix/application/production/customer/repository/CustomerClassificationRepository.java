package dev.fenix.application.production.customer.repository;

import dev.fenix.application.production.customer.model.CustomerClassification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerClassificationRepository
    extends JpaRepository<CustomerClassification, Long> {

  Iterable<CustomerClassification> findByActiveTrue();

  CustomerClassification findTopByOrderByIdDesc();

    CustomerClassification findOneById(long l);
}
