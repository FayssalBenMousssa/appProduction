package dev.fenix.application.production.product.repository;

import dev.fenix.application.production.product.model.Product;
import dev.fenix.application.production.product.model.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {


     Page<Product> findAllByNameContains(String name, Pageable pageable);

     List<Product> findAllByNameContains(String name);

    Page<Product> findByActiveTrue(Pageable paging);
    Product getOne(Long id);

    Page<Product> findByActiveTrueAndProductType(ProductType productType, Pageable paging);

     //int countActiveTrueAndProductType(ProductType productType);

    long countByActiveTrueAndProductType(ProductType productType);

    // Page<Product> findByProductType(ProductType productType, Pageable paging);
 //  Page<Product> findByActiveTrueAndProductType(ProductType productType);
    //Page<Product> findByActiveTrueAndProductIdProductType(ProductType productType, Pageable paging);
}
