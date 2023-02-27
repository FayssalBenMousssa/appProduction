package dev.fenix.application.production.payment.repository;

import dev.fenix.application.production.payment.model.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentStatusRepository extends JpaRepository<PaymentStatus, Long> {
  Iterable<PaymentStatus> findByActiveTrue();

  PaymentStatus findTopByOrderByIdDesc();

  PaymentStatus findOneById(Long PaymentStatus);

    int countByActiveTrue();
}
