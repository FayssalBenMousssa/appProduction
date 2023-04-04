package dev.fenix.application.production.product.service;

import dev.fenix.application.production.product.model.Formula;
import dev.fenix.application.production.product.model.Product;
import dev.fenix.application.production.product.repository.FormulaRepository;
import dev.fenix.application.production.product.repository.ProductRepository;
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
public class FormulaService {
  @Autowired private FormulaRepository formulaRepository;
  @Autowired private ProductRepository productRepository;

  private static final Logger log = LoggerFactory.getLogger(FormulaService.class);
  private int count = 0;
  private int countAll = 0;

  /**
   * get list of Formula
   *
   * @param pageNo page number
   * @param pageSize page size
   * @param sortBy list of sort & direction
   * @param query list of query to filter data
   */
  public List<Formula> getAllFormula(Integer pageNo, Integer pageSize, String[] sortBy, String[] query) {


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
    List<Formula> filteringFormulas = new ArrayList<Formula>();

    countAll = formulaRepository.countByActiveTrue();
    //log.info(countAll + " formula active in DB");
    Page<Formula> pagedResult;

    if (filters != null && filters.size() != 0) {
      //log.info("we have just have filters");
      for (Map.Entry<String, String> entry : filters.entrySet()) {
        String key = entry.getKey();
        String value = entry.getValue();
        switch (key) {
          case "name":
            filteringFormulas.addAll(formulaRepository.findAllByNameContainsAndActiveTrue(value, paging).getContent());
            count = formulaRepository.countByNameContainsAndActiveTrue(value);
            //log.info(count + " formulas by name [" + value + "] for all types");
            break;
          case "product":
            Product product = productRepository.findOneById(Long.valueOf(value));
            filteringFormulas.addAll(formulaRepository.findAllByProductAndActiveTrue(product, paging).getContent());
            count = formulaRepository.countByProductAndActiveTrue(product);
            //log.info(count + " formulas by product [" + product.getName() + "] for all types");
            break;
          default:
            //log.info("value not in list of search !");
        }
      }
      pagedResult = new PageImpl<>(filteringFormulas, paging, pageSize);
      return pagedResult.getContent();
    } else {
      //log.info("all active formulas");
      pagedResult = formulaRepository.findByActiveTrue(paging);
      count = formulaRepository.countByActiveTrue();
      //log.info(count + " formulas ");
      return pagedResult.getContent();
    }
  }

  private Sort.Direction getSortDirection(String direction) {
    //log.trace("formula service.getSortDirection method accessed");
    if (direction.equals("asc")) {
      return Sort.Direction.ASC;
    } else if (direction.equals("desc")) {
      return Sort.Direction.DESC;
    }
    return Sort.Direction.ASC;
  }

  private Map<String, String> getFilters(String[] query) {
    //log.trace("formula service.getFilters method accessed");
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
