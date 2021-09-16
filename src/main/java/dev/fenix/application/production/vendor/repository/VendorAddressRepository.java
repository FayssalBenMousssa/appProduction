package dev.fenix.application.production.vendor.repository;

import dev.fenix.application.production.vendor.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorAddressRepository extends JpaRepository <Address,Long> {

    Iterable<Address> findByActiveTrue();
}
