package dev.fenix.application.production.treatment.repository;

import dev.fenix.application.production.treatment.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
  Iterable<Category> findByActiveTrue();

  Category findTopByOrderByIdDesc();

    Category findOneById(Long category);

    int countByActiveTrue();
}
