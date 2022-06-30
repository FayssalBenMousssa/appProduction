package dev.fenix.application.cascade.repository;

import dev.fenix.application.cascade.model.CascadeAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CascadePersonRepository extends JpaRepository<CascadeAddress, Integer> {

}
