package dev.fenix.application.production.treatment.repository;

import dev.fenix.application.production.treatment.model.Category;
import dev.fenix.application.production.treatment.model.Document;
import dev.fenix.application.production.treatment.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

  Page<Document> findALLByActiveTrue(Pageable paging);

  int countByActiveTrue();

  Page<Document> findAllByCodeContainsAndActiveTrueAndType(String value, Long type, Pageable paging);
  int countByCodeContainsAndActiveTrueAndType(String value, Long type);


  //// Optimized query
  /*
  @Query("SELECT u FROM User u WHERE u.status = :status and u.name = :name")
  User findUserByStatusAndNameNamedParams(
  @Param("status") Integer status,
  @Param("name") String name);
   */

  /// "SELECT ph FROM Employee e JOIN e.phones ph WHERE ph LIKE '1%'"

 // @Query(" from Document as document where document.active = true   ")

// Page<Document> documentsByActiveTrueAndType(@Param("type")   Long type, Pageable paging);

 // int countByDocumentsCodeContainsAndActiveTrueAndType(String value, Long type);



  Page<Document> findAllByCodeContainsAndActiveTrue(String value, Pageable paging);

  int countByCodeContainsAndActiveTrue(String value);

  Page<Document> findByActiveTrueAndType(Type type, Pageable paging);

  int countByActiveTrueAndType(Type type);

  Document findTopByOrderByIdDesc();

  int countByType(Type type);

  Page<Document> findAllByCodeContainsAndActiveTrueAndTypeCategoryAndType(String value, Long category, Long type, Pageable paging);

  int countByCodeContainsAndActiveTrueAndTypeCategoryAndType(
      String value, Long category, Long type);

  Page<Document> findAllByCodeContainsAndActiveTrueAndTypeCategory(String value, Long category, Pageable paging);

  int countByCodeContainsAndActiveTrueAndTypeCategory(String value, Long category);

  Page<Document> findByActiveTrueAndTypeCategory(Category category, Pageable paging);

  int countByActiveTrueAndTypeCategory(Category category);

  Page<Document> findByActiveTrueAndTypeCategoryAndType(
      Category category, Type type, Pageable paging);

  int countByActiveTrueAndTypeCategoryAndType(Category category, Type type);

  Page<Document> findAll(Pageable sortedByName);

  /*
  @Query("SELECT u FROM User u WHERE u.status = :status and u.name = :name")
  User findUserByStatusAndNameNamedParams(
  @Param("status") Integer status,
  @Param("name") String name);
   */
}
