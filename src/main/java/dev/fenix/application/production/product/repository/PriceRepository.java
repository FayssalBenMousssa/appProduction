package dev.fenix.application.production.product.repository;


import dev.fenix.application.production.product.model.Price;
import dev.fenix.application.production.product.model.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
  List<Price> findByActiveTrueAndProduct(Long product);
  Price findOneById(long l);
  Price findTopByOrderByIdDesc();


  ///   Page<Document> findAllByCodeContainsAndActiveTrueAndTypeCategoryAndType(String value, Long category, Long type, Pageable paging);


}
