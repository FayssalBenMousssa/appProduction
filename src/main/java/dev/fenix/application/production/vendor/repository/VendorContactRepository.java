package dev.fenix.application.production.vendor.repository;


import dev.fenix.application.production.vendor.model.VendorContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorContactRepository extends JpaRepository <VendorContact,Long> {

}
