package dev.fenix.application.app.repository;

import dev.fenix.application.app.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {}
