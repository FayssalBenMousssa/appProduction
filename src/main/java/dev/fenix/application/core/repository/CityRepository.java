package dev.fenix.application.core.repository;

import dev.fenix.application.core.model.City;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CityRepository extends PagingAndSortingRepository<City, Long> {
  City findCityById(Long id);

  City getCityByName(String name);

  City save(City city);

  void delete(City city);
}
