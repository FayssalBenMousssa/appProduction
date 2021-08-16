package dev.fenix.application.core.repository;

import dev.fenix.application.core.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {}
