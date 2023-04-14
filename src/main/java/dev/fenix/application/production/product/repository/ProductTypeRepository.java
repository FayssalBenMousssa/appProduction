package dev.fenix.application.production.product.repository;

import dev.fenix.application.production.product.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
  Iterable<ProductType> findByActiveTrue();

  ProductType findOneById(Long type);

  ProductType findTopByOrderByIdDesc();

    ProductType findTopByOrderByIdAsc();

    ProductType findOneByCode(String type_product);
}
