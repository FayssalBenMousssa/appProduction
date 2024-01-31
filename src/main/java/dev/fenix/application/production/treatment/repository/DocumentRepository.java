package dev.fenix.application.production.treatment.repository;

import dev.fenix.application.business.model.Company;
import dev.fenix.application.production.treatment.model.Category;
import dev.fenix.application.production.treatment.model.Document;
import dev.fenix.application.production.treatment.model.Status;
import dev.fenix.application.production.treatment.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
  int countByActiveTrue();
  int countByActiveTrueAndType(Type type);
  Document findTopByOrderByIdDesc();
  int countByType(Type type);
  int countByActiveTrueAndTypeCategory(Category category);
  Page<Document> findAll(Pageable sortedByName);
  int countByActiveTrueAndCode(String code);
  Page<Document> findByActiveTrueAndTypeCategoryAndAccessIn(Category documentCategory, List<String> accessList, Pageable paging);
  int countByActiveTrueAndTypeCategoryAndAccessIn(Category documentCategory, List<String> accessList);
  Page<Document> findByActiveTrueAndTypeAndAccessIn(Type documentType, List<String> accessList, Pageable paging);
  int countByActiveTrueAndTypeAndAccessIn(Type documentType, List<String> accessList);
  Page<Document> findAllByCodeContainsAndActiveTrueAndTypeAndSourceAndAccessIn(String code, Type documentType, Company source, List<String> accessList, Pageable paging);
  int countByCodeContainsAndActiveTrueAndTypeAndSourceAndAccessIn(String code, Type documentType, Company source, List<String> accessList);
  Page<Document> findAllByCodeContainsAndActiveTrueAndTypeAndStatusInAndAccessIn(String code, Type documentType, List<Status> workFlow, List<String> accessList, Pageable paging);
  int countByCodeContainsAndActiveTrueAndTypeAndStatusInAndAccessIn(String code, Type documentType, List<Status> workFlow, List<String> accessList);
  Page<Document> findAllByCodeContainsAndActiveTrueAndTypeAndAccessIn(String code, Type documentType, List<String> accessList, Pageable paging);
  int countByCodeContainsAndActiveTrueAndTypeAndAccessIn(String code, Type documentType, List<String> accessList);
  Page<Document> findAllByActiveTrueAndTypeAndSourceAndAccessIn(Type documentType, Company source, List<String> accessList, Pageable paging);
  int countByActiveTrueAndTypeAndSourceAndAccessIn(Type documentType, Company source, List<String> accessList);
  Page<Document> findByActiveTrueAndTypeAndStatusInAndAccessIn(Type documentType, List<Status> workFlow, List<String> accessList, Pageable paging);
  int countByActiveTrueAndTypeAndStatusInAndAccessIn(Type documentType, List<Status> workFlow, List<String> accessList);
  Page<Document> findALLByActiveTrueAndAccessIn(List<String> accessList, Pageable paging);
  int countByActiveTrueAndAccessIn(List<String> accessList);

  Page<Document> findAllByCodeContainsAndActiveTrueAndTypeCategoryAndSourceAndStatusInAndAccessIn(String code, Category documentCategory, Company source, List<Status> workFlow, List<String> accessList, Pageable paging);
  int countByCodeContainsAndActiveTrueAndTypeCategoryAndSourceAndStatusInAndAccessIn(String code, Category documentCategory, Company source, List<Status> workFlow, List<String> accessList);
  Page<Document> findAllByCodeContainsAndActiveTrueAndTypeCategoryAndSourceAndAccessIn(String code, Category documentCategory, Company source, List<String> accessList, Pageable paging);
  int countByCodeContainsAndActiveTrueAndTypeCategoryAndSourceAndAccessIn(String code, Category documentCategory, Company source, List<String> accessList);
  Page<Document> findAllByCodeContainsAndActiveTrueAndTypeCategoryAndStatusInAndAccessIn(String code, Category documentCategory, List<Status> workFlow, List<String> accessList, Pageable paging);
  int countByCodeContainsAndActiveTrueAndTypeCategoryAndStatusInAndAccessIn(String code, Category documentCategory, List<Status> workFlow, List<String> accessList);

  List<Document> findByActiveTrueAndType_MetaData_CodeAndDocumentDataValues_Value(String code, String value);



  Page<Document> findAllByCodeContainsAndActiveTrueAndTypeCategoryAndAccessIn(String code, Category documentCategory, List<String> accessList, Pageable paging);
  int countByCodeContainsAndActiveTrueAndTypeCategoryAndAccessIn(String code, Category documentCategory, List<String> accessList);

  Page<Document> findByActiveTrueAndTypeCategoryAndStatusInAndAccessIn(Category documentCategory, List<Status> workFlow, List<String> accessList, Pageable paging);
  int countByActiveTrueAndTypeCategoryAndStatusInAndAccessIn(Category documentCategory, List<Status> workFlow, List<String> accessList);





  @Query("select doc from Document doc where doc.active = true and doc.type.toInvoice = true and doc.type.category = :category and doc.status in :workFlow and (doc.source = :company or doc.destination = :company) and doc.access in :accessList")
  Page<Document> loadDocumentsToInvoice_ByActiveTrueAndTypeToInvoiceTrueAndTypeCategoryAndStatusInAndSourceOrDestinationAndAccessIn(@Param("category") Category documentCategory,
                                                                                                                                    @Param("workFlow") List<Status> workFlow,
                                                                                                                                    @Param("company") Company company,
                                                                                                                                    @Param("accessList") List<String> accessList,
                                                                                                                                    Pageable paging);
  @Query("select COUNT(doc) from Document doc where doc.active = true and doc.type.toInvoice = true and doc.type.category = :category and doc.status in :workFlow and (doc.source = :company or doc.destination = :company) and doc.access in :accessList")
  int countDocumentsToInvoice_ByActiveTrueAndTypeToInvoiceTrueAndTypeCategoryAndStatusInAndSourceOrDestinationAndAccessIn(@Param("category") Category documentCategory,
                                                                                                                          @Param("workFlow") List<Status> workFlow,
                                                                                                                          @Param("company") Company company,
                                                                                                                          @Param("accessList") List<String> accessList);

  @Query("select doc from Document doc where doc.active = true and doc.type.toInvoice = true and doc.type.category = :category and doc.status in :workFlow  and doc.access in :accessList")
  Page<Document> loadDocumentsToInvoice_ByActiveTrueAndTypeToInvoiceTrueAndTypeCategoryAndStatusInAndAccessIn(@Param("category") Category documentCategory,
                                                                                                              @Param("workFlow") List<Status> workFlow,
                                                                                                              @Param("accessList") List<String> accessList,
                                                                                                              Pageable paging);

  @Query("select COUNT(doc) from Document doc where doc.active = true and doc.type.toInvoice = true and doc.type.category = :category and doc.status in :workFlow  and doc.access in :accessList")
  int countDocumentsToInvoice_ByActiveTrueAndTypeToInvoiceTrueAndTypeCategoryAndStatusInAndAccessIn(@Param("category") Category documentCategory,
                                                                                                              @Param("workFlow") List<Status> workFlow,
                                                                                                              @Param("accessList") List<String> accessList);

  @Query("select doc from Document doc where doc.active = true and doc.type.category = :category and doc.status in :workFlow and (doc.source = :company or doc.destination = :company) and doc.access in :accessList")
  Page<Document> loadDocuments_findByActiveTrueAndTypeCategoryAndSourceAndStatusInAndAccessIn(@Param("category") Category documentCategory,@Param("company") Company source,@Param("workFlow") List<Status> workFlow, @Param("accessList") List<String> accessList, Pageable paging);

  @Query("select COUNT(doc) from Document doc where doc.active = true and doc.type.category = :category and doc.status in :workFlow and (doc.source = :company or doc.destination = :company) and doc.access in :accessList")
  int loadDocuments_countByActiveTrueAndTypeCategoryAndSourceAndStatusInAndAccessIn(@Param("category") Category documentCategory,@Param("company") Company source,@Param("workFlow") List<Status> workFlow, @Param("accessList") List<String> accessList );

  @Query("select doc from Document doc where doc.active = true and doc.type.category = :category  and (doc.source = :company or doc.destination = :company) and doc.access in :accessList")
  Page<Document> loadDocuments_findByActiveTrueAndTypeCategoryAndSourceAndAccessIn(@Param("category") Category documentCategory,@Param("company") Company source, @Param("accessList") List<String> accessList, Pageable paging);

  @Query("select COUNT(doc) from Document doc where doc.active = true and doc.type.category = :category  and (doc.source = :company or doc.destination = :company) and doc.access in :accessList")
  int loadDocuments_countByActiveTrueAndTypeCategoryAndSourceAndAccessIn(@Param("category") Category documentCategory,@Param("company") Company source, @Param("accessList") List<String> accessList);


  @Query("select doc from Document doc where doc.code like %:code%  and  doc.active = true and doc.type.category = :category  and (doc.source = :company or doc.destination = :company) and doc.access in :accessList")
  Page<Document> loadDocuments_findAllByCodeContainsAndActiveTrueAndTypeCategoryAndSourceAndAccessIn(@Param("code") String code, @Param("category") Category documentCategory,@Param("company") Company source, @Param("accessList") List<String> accessList, Pageable paging);

  @Query("select COUNT(doc) from Document doc where doc.code like %:code%  and  doc.active = true and doc.type.category = :category  and (doc.source = :company or doc.destination = :company) and doc.access in :accessList")
  int loadDocuments_countByCodeContainsAndActiveTrueAndTypeCategoryAndSourceAndAccessIn(@Param("code") String code, @Param("category") Category documentCategory,@Param("company") Company source, @Param("accessList") List<String> accessList);

  @Query("select COUNT(doc) from Document doc where doc.code like %:code%  and  doc.active = true and doc.status in :workFlow and doc.type.category = :category  and (doc.source = :company or doc.destination = :company) and doc.access in :accessList")
  int loadDocuments_countByCodeContainsAndActiveTrueAndTypeCategoryAndSourceAndStatusInAndAccessIn(@Param("code") String code,@Param("category") Category documentCategory,@Param("company") Company source, @Param("workFlow")  List<Status> workFlow, @Param("accessList") List<String> accessList);
  @Query("select doc from Document doc where doc.code like %:code%  and  doc.active = true  and doc.status in :workFlow and doc.type.category = :category  and (doc.source = :company or doc.destination = :company) and doc.access in :accessList")
  Page<Document> loadDocuments_findAllByCodeContainsAndActiveTrueAndTypeCategoryAndSourceAndStatusInAndAccessIn(@Param("code") String code,@Param("category") Category documentCategory,@Param("company") Company source, @Param("workFlow")  List<Status> workFlow, @Param("accessList") List<String> accessList ,  Pageable paging);
}
