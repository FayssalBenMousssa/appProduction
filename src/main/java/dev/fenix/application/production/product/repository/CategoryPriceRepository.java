package dev.fenix.application.production.product.repository;


import dev.fenix.application.production.product.model.CategoryPrice;
import dev.fenix.application.production.product.model.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryPriceRepository extends JpaRepository<CategoryPrice, Long> {
  Iterable<CategoryPrice> findByActiveTrue();

  CategoryPrice findOneById(long l);

  CategoryPrice findTopByOrderByIdDesc();
  CategoryPrice findTopByOrderByIdAsc();
}
