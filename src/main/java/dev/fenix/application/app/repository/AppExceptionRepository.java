package dev.fenix.application.app.repository;

import dev.fenix.application.app.model.Exception;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppExceptionRepository extends JpaRepository<Exception, Long> {

  Page<Exception> findAllByOrderByIdDesc(Pageable pageable);
}
