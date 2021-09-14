package dev.fenix.application.production.vendor.repository;


import dev.fenix.application.production.vendor.model.VendorClassification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorClassificationRepository extends JpaRepository <VendorClassification,Long>{

     Iterable<VendorClassification> findByActiveTrue();

     VendorClassification findTopByOrderByIdDesc();
}
