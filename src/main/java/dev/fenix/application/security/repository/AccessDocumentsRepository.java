package dev.fenix.application.security.repository;

import dev.fenix.application.security.model.AccessDocuments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AccessDocumentsRepository extends JpaRepository<AccessDocuments, Long> { }
