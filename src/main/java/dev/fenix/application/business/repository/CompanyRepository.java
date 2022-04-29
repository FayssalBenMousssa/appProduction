package dev.fenix.application.business.repository;

import dev.fenix.application.business.model.Company;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {

  Company findTopByOrderByIdDesc();
}
