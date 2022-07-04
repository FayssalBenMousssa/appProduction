package dev.fenix.application.production.treatment.repository;

import dev.fenix.application.production.treatment.model.DocumentLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentLogRepository extends JpaRepository<DocumentLog, Long> {
  List<DocumentLog> findByDocumentId(Long documentId);
}
