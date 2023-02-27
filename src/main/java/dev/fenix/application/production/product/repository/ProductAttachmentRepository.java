package dev.fenix.application.production.product.repository;

import dev.fenix.application.production.product.model.ProductAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAttachmentRepository extends JpaRepository<ProductAttachment,String> {

}
