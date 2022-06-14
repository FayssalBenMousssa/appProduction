package dev.fenix.application.production.product.repository;

import dev.fenix.application.production.product.model.ProductMgmtMode;
import dev.fenix.application.production.product.model.SiUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMgmtModeRepository extends JpaRepository<ProductMgmtMode, Long> {


  ProductMgmtMode findOneById(long l);
  ProductMgmtMode findTopByOrderByIdDesc();

}
