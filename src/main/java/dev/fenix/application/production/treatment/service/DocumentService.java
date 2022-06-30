package dev.fenix.application.production.treatment.service;


import dev.fenix.application.production.treatment.model.Category;
import dev.fenix.application.production.treatment.model.Document;
import dev.fenix.application.production.treatment.model.Type;
import dev.fenix.application.production.treatment.repository.CategoryRepository;
import dev.fenix.application.production.treatment.repository.DocumentRepository;
import dev.fenix.application.production.treatment.repository.TypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DocumentService {
  @Autowired private DocumentRepository documentRepository;
  @Autowired private TypeRepository typeRepository;
  @Autowired private CategoryRepository categoryRepository;

  private static final Logger log = LoggerFactory.getLogger(DocumentService.class);
  private int count = 0;
  private int countAll = 0;

  /**
   * get list of documents
   *
   * @param pageNo page number
   * @param pageSize page size
   * @param sortBy list of sort & direction
   * @param query list of query to filter data
   * @param type type of document
   * @param category category of document
   */
  public List<Document> getAllDocuments(Integer pageNo, Integer pageSize, String[] sortBy, String[] query, Long type, Long category) {



    //// Order
    List<Sort.Order> orders = new ArrayList<Sort.Order>();
    if (sortBy[0].contains(",")) {
      // will sort more than 2 columns
      log.trace("we will sort more than 2 columns ");
      for (String sortOrder : sortBy) {
        // sortOrder="column, direction"

        String[] _sort = sortOrder.split(",");
        log.trace("sortOrder : " + _sort[1] + " " + _sort[0]);
        orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
      }
    } else {
      // sort=[column, direction]
      orders.add(new Sort.Order(getSortDirection(sortBy[1]), sortBy[0]));
    }

    //// filters
    Map<String, String> filters = getFilters(query);
    Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(orders));
    List<Document> filteringDocuments = new ArrayList<>();

    countAll = documentRepository.countByActiveTrue();
    log.info(countAll + " documents active in DB");
    Page<Document> pagedResult;

    /// if we have filters and type
    if (filters != null && filters.size() != 0 && type != null) {
      Type documentType = typeRepository.findOneById(type);
      log.info("We have filters and type : " + documentType.getName());
      for (Map.Entry<String, String> entry : filters.entrySet()) {
        String key = entry.getKey();
        String value = entry.getValue();
        switch (key) {
          case "code":
            filteringDocuments.addAll(documentRepository.findAllByCodeContainsAndActiveTrueAndType(value, type, paging).getContent());
            count = documentRepository.countByCodeContainsAndActiveTrueAndType(value, type);
            log.info(count + " Documents by name [" + value + "] found of Type :" + documentType.getName());
            break;
          default:
            log.info("value not in list of search !");
        }
      }
      pagedResult = new PageImpl<>(filteringDocuments, paging, pageSize);
      return pagedResult.getContent();
    }

    /// if we have filters and category
    if (filters != null && filters.size() != 0 && category != null) {
      Category documentCategory = categoryRepository.findOneById(category);
      log.info("We have filters and type : " + documentCategory.getName());
      for (Map.Entry<String, String> entry : filters.entrySet()) {
        String key = entry.getKey();
        String value = entry.getValue();
        switch (key) {
          case "code":
            filteringDocuments.addAll(documentRepository.findAllByCodeContainsAndActiveTrueAndTypeCategory(value, category, paging).getContent());
            count = documentRepository.countByCodeContainsAndActiveTrueAndTypeCategory(value, category);
            log.info(count + " Documents by name [" + value + "] found of Type :" + documentCategory.getName());
            break;
          default:
            log.info("value not in list of search !");
        }
      }
      pagedResult = new PageImpl<>(filteringDocuments, paging, pageSize);
      return pagedResult.getContent();
    }
    /// if we have filters and category And type
    if (filters != null && filters.size() != 0 && category != null && type != null) {
      Category documentCategory = categoryRepository.findOneById(category);
      log.info("We have filters and type : " + documentCategory.getName());
      Type documentType = typeRepository.findOneById(type);

      for (Map.Entry<String, String> entry : filters.entrySet()) {
        String key = entry.getKey();
        String value = entry.getValue();
        switch (key) {
          case "code":
            filteringDocuments.addAll(documentRepository.findAllByCodeContainsAndActiveTrueAndTypeCategoryAndType(value, category, type , paging).getContent());
            count = documentRepository.countByCodeContainsAndActiveTrueAndTypeCategoryAndType(value, category,type);
            log.info(count + " Documents by name [" + value + "] found of Type :" + documentCategory.getName() + "and type :" + documentType.getName());
            break;
          default:
            log.info("value not in list of search !");
        }
      }
      pagedResult = new PageImpl<>(filteringDocuments, paging, pageSize);
      return pagedResult.getContent();
    }
    /// if  have just filters
    else if (filters != null && filters.size() != 0) {
      log.info("we have just have filters");
      for (Map.Entry<String, String> entry : filters.entrySet()) {
        String key = entry.getKey();
        String value = entry.getValue();
        switch (key) {
          case "code":
            filteringDocuments.addAll(
                documentRepository.findAllByCodeContainsAndActiveTrue(value, paging).getContent());
            count = documentRepository.countByCodeContainsAndActiveTrue(value);
            log.info(count + " Documents by name [" + value + "] for all types");
            break;
          default:
            log.info("value not in list of search !");
        }
      }
      pagedResult = new PageImpl<>(filteringDocuments, paging, pageSize);
      return pagedResult.getContent();
    } else if (filters == null && type != null && category == null) {
      log.info("we have just type");
      Type documentType = typeRepository.findOneById(type);
      pagedResult = documentRepository.findByActiveTrueAndType(documentType, paging);
      count = documentRepository.countByActiveTrueAndType(documentType);
      log.info(count + " Documents of type" + documentType.getName());
      return pagedResult.getContent();
    }
    else if (filters == null && category != null && type == null) {
      log.info("we have just category");
      Category documentCategory = categoryRepository.findOneById(category);
      pagedResult = documentRepository.findByActiveTrueAndTypeCategory(documentCategory, paging);
      count = documentRepository.countByActiveTrueAndTypeCategory(documentCategory);
      log.info(count + " Documents of category" + documentCategory.getName());
      return pagedResult.getContent();
    } else if (filters == null && category != null && type != null) {
      log.info("we have category & type");
      Type documentType = typeRepository.findOneById(type);
      Category documentCategory = categoryRepository.findOneById(category);
      pagedResult = documentRepository.findByActiveTrueAndTypeCategoryAndType(documentCategory,documentType, paging);
      count = documentRepository.countByActiveTrueAndTypeCategoryAndType(documentCategory,documentType);
      log.info(count + " Documents of type" + documentType.getName() + " & " + documentCategory.getName() ) ;
      return pagedResult.getContent();
    }

    else {
      log.info("all active documents");
      pagedResult = documentRepository.findALLByActiveTrue(paging);
      count = documentRepository.countByActiveTrue();
      log.info(count + " Documents ");
      return pagedResult.getContent();
    }
  }

  private Sort.Direction getSortDirection(String direction) {
    log.trace("DocumentService.getSortDirection method accessed");
    if (direction.equals("asc")) {
      return Sort.Direction.ASC;
    } else if (direction.equals("desc")) {
      return Sort.Direction.DESC;
    }
    return Sort.Direction.ASC;
  }

  private Map<String, String> getFilters(String[] query) {
    log.trace("DocumentService.getFilters method accessed");
    if (query != null && query[0].contains(":")) {
      Map<String, String> hashMap = new HashMap<String, String>();
      for (String keyValue : query) {
        String[] _filter = keyValue.split(":");
        if (_filter.length > 1) {
          hashMap.put(_filter[0], _filter[1]);
          log.info("Filter found : " + _filter[0] + ":" + _filter[1]);
        }
      }
      return hashMap;
    } else {
      log.info("No filter found");
      return null;
    }
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public int getCountAll() {
    return countAll;
  }

  public void setCountAll(int countAll) {
    this.countAll = countAll;
  }
}
