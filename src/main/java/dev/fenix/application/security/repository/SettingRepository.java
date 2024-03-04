package dev.fenix.application.security.repository;

import dev.fenix.application.security.model.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingRepository extends JpaRepository<Setting, Long> {
}