package dev.fenix.application.production.vendor.repository;

import dev.fenix.application.production.vendor.model.VendorAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorAddressRepository extends JpaRepository <VendorAddress,Long> {

}
