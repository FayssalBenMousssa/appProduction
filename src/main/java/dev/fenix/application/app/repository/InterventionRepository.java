package dev.fenix.application.app.repository;

import dev.fenix.application.app.model.Intervention;
import dev.fenix.application.production.customer.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InterventionRepository extends PagingAndSortingRepository<Intervention, Long> {

  Intervention getOne(Long id);

  Page<Intervention> findAll(Pageable paging);
}
