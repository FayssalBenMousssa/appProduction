package dev.fenix.application.production.product.repository;


import dev.fenix.application.production.customer.model.Customer;
import dev.fenix.application.production.product.model.Price;
import dev.fenix.application.production.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
  List<Price> findByActiveTrueAndProduct(Long product);
  Price findOneById(long l);
  Price findTopByOrderByIdDesc();

  List<Price> findByProductAndCustomersAndActiveTrue(Product product, Customer customers);

  List<Price> findByProductAndActiveTrueAndCustomersEmpty(Product product);






}
