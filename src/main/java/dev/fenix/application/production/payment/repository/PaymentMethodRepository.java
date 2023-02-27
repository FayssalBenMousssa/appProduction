package dev.fenix.application.production.payment.repository;

import dev.fenix.application.production.payment.model.PaymentMethod;
import dev.fenix.application.production.treatment.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
  Iterable<PaymentMethod> findByActiveTrue();

  PaymentMethod findTopByOrderByIdDesc();

  PaymentMethod findOneById(Long paymentMethod);

    int countByActiveTrue();
}
