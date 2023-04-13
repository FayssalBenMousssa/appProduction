package dev.fenix.application.production.product.service;

import dev.fenix.application.production.product.model.Product;
import dev.fenix.application.production.product.model.ProductType;
import dev.fenix.application.production.product.repository.ProductRepository;
import dev.fenix.application.production.product.repository.ProductTypeRepository;
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
public class ProductService {
  @Autowired private ProductRepository productRepository;
  @Autowired private ProductTypeRepository productTypeRepository;

  private static final Logger log = LoggerFactory.getLogger(ProductService.class);
  private int count = 0;
  private int countAll = 0;
  private Page<Product> pagedResult;

  /**
   * get list of products
   *
   * @param pageNo page number
   * @param pageSize page size
   * @param sortBy list of sort & direction
   * @param query list of query to filter data
   * @param type type of product
   */
  public List<Product> getAllProducts(Integer pageNo, Integer pageSize, String[] sortBy, String[] query, Long type, Long[] types) {


    //// Order
    List<Sort.Order> orders = new ArrayList<Sort.Order>();
    if (sortBy[0].contains(",")) {
      // will sort more than 2 columns
      //log.trace("we will sort more than 2 columns ");
      for (String sortOrder : sortBy) {
        // sortOrder="column, direction"

        String[] _sort = sortOrder.split(",");
        //log.trace("sortOrder : " + _sort[1] + " " + _sort[0]);
        orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
      }
    } else {
      // sort=[column, direction]
      orders.add(new Sort.Order(getSortDirection(sortBy[1]), sortBy[0]));
    }

    //// filters
    Map<String, String> filters = getFilters(query);
    Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(orders));
    List<Product> filteringProducts = new ArrayList<Product>();
    countAll = productRepository.countByActiveTrue();
    //log.info(countAll + " products active in DB");

    /// if we have filters and type
    if (filters != null && filters.size() != 0 && type != null) {
      ProductType productType = productTypeRepository.findOneById(type);
      //log.info("We have filters and type : " + productType.getName());
      for (Map.Entry<String, String> entry : filters.entrySet()) {
        String key = entry.getKey();
        String value = entry.getValue();
          //log.info("value not in list of search !");
          if ("name".equals(key)) {
              filteringProducts.addAll(
                      productRepository.findAllByNameContainsAndActiveTrueAndProductType(value, productType, paging).getContent());
              count = productRepository.countByNameContainsAndActiveTrueAndProductType(value, productType);
          }
      }
      pagedResult = new PageImpl<>(filteringProducts, paging, pageSize);
      return pagedResult.getContent();
    }
    /// if just have filters
    else if (filters != null && filters.size() != 0) {
      //log.info("we have just have filters");
      for (Map.Entry<String, String> entry : filters.entrySet()) {
        String key = entry.getKey();
        String value = entry.getValue();
          //log.info("value not in list of search !");
          if ("name".equals(key)) {
              filteringProducts.addAll(productRepository.findAllByNameContainsAndActiveTrue(value, paging).getContent());
              count = productRepository.countByNameContainsAndActiveTrue(value);
              //log.info(count + " Products by name [" + value + "] for all types");
          }
      }
      pagedResult = new PageImpl<>(filteringProducts, paging, pageSize);
      return pagedResult.getContent();
    } else if (filters == null && type != null) {
      //log.info("we have just type");
      ProductType productType = productTypeRepository.findOneById(type);
      pagedResult = productRepository.findByActiveTrueAndProductType(productType, paging);
      count = productRepository.countByActiveTrueAndProductType(productType);
      //log.info(count + " Products of type" + productType.getName());
      return pagedResult.getContent();
    } else {
      //log.info("all active products");
      pagedResult = productRepository.findByActiveTrue(paging);
      count = productRepository.countByActiveTrue();
      //log.info(count + " Products ");
      return pagedResult.getContent();
    }
  }


  // public List<Document> loadDocuments(Pageable paging, Category documentCategory)
  public List<Product> loadProducts(Pageable paging ) {
    pagedResult = productRepository.findByActiveTrue(paging);
    count = productRepository.countByActiveTrue();
    //log.info(count + " Products ");
    return pagedResult.getContent();

  }

  private Sort.Direction getSortDirection(String direction) {
    //log.trace("ProductService.getSortDirection method accessed");
    if (direction.equals("asc")) {
      return Sort.Direction.ASC;
    } else if (direction.equals("desc")) {
      return Sort.Direction.DESC;
    }
    return Sort.Direction.ASC;
  }

  private Map<String, String> getFilters(String[] query) {
    //log.trace("ProductService.getFilters method accessed");
    if (query != null && query[0].contains(":")) {
      Map<String, String> hashMap = new HashMap<String, String>();
      for (String keyValue : query) {
        String[] _filter = keyValue.split(":");
        if (_filter.length > 1) {
          hashMap.put(_filter[0], _filter[1]);
          //log.info("Filter found : " + _filter[0] + ":" + _filter[1]);
        }
      }
      return hashMap;
    } else {
      //log.info("No filter found");
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
