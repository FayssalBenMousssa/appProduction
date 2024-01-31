package dev.fenix.application.production.treatment.repository;

import dev.fenix.application.production.treatment.model.PrintModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrintModelRepository extends JpaRepository<PrintModel, Long> {

}
