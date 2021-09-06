package dev.fenix.application.production.vendor.repository;

import dev.fenix.application.production.vendor.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository <Vendor,Long> {

}
