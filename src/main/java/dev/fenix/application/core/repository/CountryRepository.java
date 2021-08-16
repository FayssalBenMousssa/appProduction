package dev.fenix.application.core.repository;

import dev.fenix.application.core.model.Country;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CountryRepository extends PagingAndSortingRepository<Country, Long> {
  Country getCountryById(Long id);

  Country save(Country country);

  void delete(Country country);
}
