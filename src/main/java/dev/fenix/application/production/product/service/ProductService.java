package dev.fenix.application.production.product.service;

import dev.fenix.application.Application;
import dev.fenix.application.production.product.model.Product;
import dev.fenix.application.production.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
  @Autowired private ProductRepository productRepository;
  private static final Logger log = LoggerFactory.getLogger(Application.class);

  public List<Product> getAllProducts(
      Integer pageNo, Integer pageSize, String[] sortBy, String[] query) {

    List<Sort.Order> orders = new ArrayList<Sort.Order>();
    if (sortBy[0].contains(",")) {
      // will sort more than 2 columns
      for (String sortOrder : sortBy) {
        // sortOrder="column, direction"
        String[] _sort = sortOrder.split(",");
        orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
      }
    } else {
      // sort=[column, direction]
      orders.add(new Sort.Order(getSortDirection(sortBy[1]), sortBy[0]));
    }

    Map<String, String> filters = getFilters(query);
    Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(orders));
    List<Product> filteringProducts = new ArrayList<Product>();

    if (filters != null && filters.size() != 0) {
      for (Map.Entry<String, String> entry : filters.entrySet()) {
        String key = entry.getKey();
        String value = entry.getValue();
        log.info(key + " -> " + value);

        switch (key) {
          case "name":
            filteringProducts.addAll(
                productRepository.findAllByNameContains(value, paging).getContent());
            log.info(
                "  key : " + key + "  value : " + value + " size : " + (filteringProducts.size()));
            filteringProducts.forEach(
                item -> {
                  log.info(item.getName());
                });
            break;
          case "id":
            filteringProducts.addAll(
                productRepository.findAllByNameContains(value, paging).getContent());
            log.info(
                "  key id: "
                    + key
                    + "  value : "
                    + value
                    + " size : "
                    + (filteringProducts.size()));
            filteringProducts.forEach(
                item -> {
                  log.info(item.getName());
                });
            break;
          default:
            log.info("default");
        }
      }
    }

    if (filteringProducts.size() != 0) {
      filteringProducts.forEach(
          item -> {

            log.info(filteringProducts.indexOf(item) + " " + item.getName());
          });
      Page<Product> page = new PageImpl<>(filteringProducts, paging, filteringProducts.size());
      return page.getContent();
    } else {
      Page<Product> pagedResult = productRepository.findAll(paging);
      if (pagedResult.hasContent()) {
        return pagedResult.getContent();
      } else {
        return new ArrayList<Product>();
      }
    }
  }

  private Sort.Direction getSortDirection(String direction) {
    if (direction.equals("asc")) {
      return Sort.Direction.ASC;
    } else if (direction.equals("desc")) {
      return Sort.Direction.DESC;
    }
    return Sort.Direction.ASC;
  }

  private Map<String, String> getFilters(String[] query) {
    if (query != null && query[0].contains(":")) {
      Map<String, String> hashMap = new HashMap<String, String>();
      for (String keyValue : query) {
        String[] _sort = keyValue.split(":");
        hashMap.put(_sort[0], _sort[1]);
      }
      return hashMap;
    } else {
      log.info("No data");
      return null;
    }
  }
}
