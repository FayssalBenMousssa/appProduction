package dev.fenix.application.production.treatment.repository;

import dev.fenix.application.production.treatment.model.Trace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TraceRepository extends JpaRepository<Trace, Long> {
  List<Trace> findByDocumentId(Long documentId);
}
