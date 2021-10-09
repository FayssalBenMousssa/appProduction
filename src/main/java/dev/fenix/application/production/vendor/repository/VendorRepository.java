package dev.fenix.application.production.vendor.repository;

import dev.fenix.application.production.vendor.model.Vendor;
import dev.fenix.application.production.vendor.model.VendorClassification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository <Vendor,Long> {

    Iterable<VendorClassification> findByActiveTrue();

    Vendor findTopByOrderByIdDesc();
}
