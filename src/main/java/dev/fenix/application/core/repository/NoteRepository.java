package dev.fenix.application.core.repository;

import dev.fenix.application.core.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {}
