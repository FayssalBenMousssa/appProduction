package dev.fenix.application.production.treatment.repository;

import dev.fenix.application.production.treatment.model.DocumentProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface DocumentProductRepository extends JpaRepository<DocumentProduct, Long> {


  @Modifying
  @Query("update DocumentProduct documentProduct set documentProduct.document.id = :document where  documentProduct.id = :documentProduct ")
  @Transactional
  void updateChildren(@Param("document") Long document,@Param("documentProduct") Long documentProduct );

}
