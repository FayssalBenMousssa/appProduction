package dev.fenix.application.production.payment.repository;

import dev.fenix.application.production.customer.model.Customer;
import dev.fenix.application.production.payment.model.PaymentCustomer;
import dev.fenix.application.production.payment.model.PaymentMethod;
import dev.fenix.application.production.payment.model.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PaymentCustomerRepository extends JpaRepository<PaymentCustomer, Long> {
    Iterable<PaymentCustomer> findByActiveTrue();

    List<PaymentCustomer> findByCodeContainsAndActiveTrue(String code);




    PaymentCustomer findTopByOrderByIdDesc();

    PaymentCustomer findOneById(Long paymentCustomer);

    int countByActiveTrue();

    Page<PaymentCustomer> findByActiveTrue(Pageable paging);

    Page<PaymentCustomer> findByActiveTrueAndPaymentDateBetween(Date startDate, Date endDate, Pageable paging);

    int countByActiveTrueAndPaymentDateBetween(Date startDate, Date endDate);

    Page<PaymentCustomer> findByActiveTrueAndPaymentDateBetweenAndCustomer(Date startDate, Date endDate, Customer customer, Pageable paging);

    Page<PaymentCustomer> findByActiveTrueAndPaymentDateBetweenAndCustomerAndPaymentStatus(Date startDate, Date endDate, Customer customer, PaymentStatus paymentStatus, Pageable paging);

    Page<PaymentCustomer> findByActiveTrueAndPaymentDateBetweenAndPaymentStatus(Date startDate, Date endDate, PaymentStatus paymentStatus, Pageable paging);

    Page<PaymentCustomer> findByActiveTrueAndPaymentDateBetweenAndCustomerAndPaymentStatusAndPaymentMethod(Date startDate, Date endDate, Customer customer, PaymentStatus paymentStatus, PaymentMethod paymentMethod, Pageable paging);

    Page<PaymentCustomer> findByActiveTrueAndPaymentDateBetweenAndPaymentStatusAndPaymentMethod(Date startDate, Date endDate, PaymentStatus paymentStatus, PaymentMethod paymentMethod, Pageable paging);

    Page<PaymentCustomer> findByActiveTrueAndPaymentDateBetweenAndCustomerAndPaymentMethod(Date startDate, Date endDate, Customer customer, PaymentMethod paymentMethod, Pageable paging);

    int countByActiveTrueAndPaymentDateBetweenAndCustomer(Date startDate, Date endDate, Customer customer);

    int countByActiveTrueAndPaymentDateBetweenAndPaymentStatus(Date startDate, Date endDate, PaymentStatus paymentStatus);

    int countByActiveTrueAndPaymentDateBetweenAndCustomerAndPaymentStatus(Date startDate, Date endDate, Customer customer, PaymentStatus paymentStatus);

    int countByActiveTrueAndPaymentDateBetweenAndCustomerAndPaymentMethod(Date startDate, Date endDate, Customer customer, PaymentMethod paymentMethod);

    int countByActiveTrueAndPaymentDateBetweenAndPaymentStatusAndPaymentMethod(Date startDate, Date endDate, PaymentStatus paymentStatus, PaymentMethod paymentMethod);

    int countByActiveTrueAndPaymentDateBetweenAndCustomerAndPaymentStatusAndPaymentMethod(Date startDate, Date endDate, Customer customer, PaymentStatus paymentStatus, PaymentMethod paymentMethod);

    Page<PaymentCustomer> findByActiveTrueAndPaymentDateBetweenAndPaymentMethod(Date startDate, Date endDate, PaymentMethod paymentMethod, Pageable paging);

    int countByActiveTrueAndPaymentDateBetweenAndPaymentMethod(Date startDate, Date endDate, PaymentMethod paymentMethod);



    /* */

}
