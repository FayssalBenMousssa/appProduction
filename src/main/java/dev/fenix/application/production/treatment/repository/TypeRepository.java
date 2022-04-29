package dev.fenix.application.production.treatment.repository;

import dev.fenix.application.production.treatment.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
  Iterable<Type> findByActiveTrue();

    Type findTopByOrderByIdDesc();

    Type findOneById(Long type);

    Type findTopByOrderByIdAsc();
}
