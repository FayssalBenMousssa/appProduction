package dev.fenix.application.production.customer.repository;

import dev.fenix.application.production.customer.model.Customer;
import dev.fenix.application.production.customer.model.CustomerClassification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

  Iterable<CustomerClassification> findByActiveTrue();

  Customer findTopByOrderByIdDesc();

  int countByActiveTrue();

  Page<Customer> findAllBySocialReasonContainsAndActiveTrue(String value, Pageable paging);

  int countBySocialReasonContainsAndActiveTrue(String value);

  Page<Customer> findByActiveTrue(Pageable paging);

  Page<Customer> findAllBySocialReasonContainingAndActiveTrue(String socialReason, Pageable paging);
  //
  Page<Customer> findAllByAndActiveTrue(String socialReason, Pageable paging);
}
