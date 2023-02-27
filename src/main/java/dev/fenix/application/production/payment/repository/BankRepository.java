package dev.fenix.application.production.payment.repository;

import dev.fenix.application.production.payment.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
  Iterable<Bank> findByActiveTrue();

  Bank findTopByOrderByIdDesc();

  Bank findOneById(Long bank_id);

    int countByActiveTrue();
}
