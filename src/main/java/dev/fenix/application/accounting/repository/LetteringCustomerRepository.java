package dev.fenix.application.accounting.repository;

import dev.fenix.application.accounting.model.LetterCase;
import dev.fenix.application.accounting.model.LetteringCustomer;
import dev.fenix.application.production.customer.model.Customer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

@EnableJpaRepositories
public interface LetteringCustomerRepository extends PagingAndSortingRepository<LetteringCustomer, Long> {



    LetteringCustomer findTopOneByCustomerAndLetterLetterCaseOrderByIdDesc(Customer customer, LetterCase lowercase);
    List<LetteringCustomer> findByCustomerAndLetterLetterCaseOrderByIdDesc(Customer customer, LetterCase lowercase);


    LetteringCustomer getOne(Long id);
}
