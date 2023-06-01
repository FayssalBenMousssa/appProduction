package dev.fenix.application.business.repository;

import dev.fenix.application.business.model.Enterprise;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EnterpriseRepository extends PagingAndSortingRepository<Enterprise , Long> {
  Enterprise findTopByOrderByIdDesc();

    Iterable<Enterprise> findByActiveTrue();

  Enterprise getOne(Long id);
}
