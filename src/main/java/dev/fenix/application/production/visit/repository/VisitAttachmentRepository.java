package dev.fenix.application.production.visit.repository;

import dev.fenix.application.production.visit.model.VisitAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitAttachmentRepository extends JpaRepository<VisitAttachment, String> {
}