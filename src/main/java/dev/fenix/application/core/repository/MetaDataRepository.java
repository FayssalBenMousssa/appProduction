package dev.fenix.application.core.repository;

import dev.fenix.application.core.model.MetaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetaDataRepository extends JpaRepository<MetaData, Long> {
}