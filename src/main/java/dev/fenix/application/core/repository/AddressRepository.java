package dev.fenix.application.core.repository;

import dev.fenix.application.core.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

  Iterable<Address> findByActiveTrue();
}
