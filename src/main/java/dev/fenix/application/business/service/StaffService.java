package dev.fenix.application.business.service;

import dev.fenix.application.business.model.Staff;
import dev.fenix.application.business.repository.StaffRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StaffService {
  @Autowired private StaffRepository staffRepository;

  private static final Logger log = LoggerFactory.getLogger(StaffService.class);
  private int count = 0;
  private int countAll = 0;

  /**
   * get list of staffes
   *
   * @param pageNo page number
   * @param pageSize page size
   * @param sortBy list of sort & direction
   * @param query list of query to filter data
   */
  public List<Staff> getAllStaffs(
      Integer pageNo, Integer pageSize, String[] sortBy, String[] query) {

    log.trace("StaffeService.getAllStaffes method accessed");

    log.trace("pageNo : " + pageNo);
    log.trace("pageSize : " + pageSize);
    log.trace(
        "sortBy : " + (sortBy != null && sortBy.length > 0 ? Arrays.toString(sortBy) : "no sort"));
    log.trace(
        "query : " + (query != null && query.length > 0 ? Arrays.toString(query) : "no query"));

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

    List<Staff> filteringStaff = new ArrayList<>();
    countAll = staffRepository.countByActiveTrue();
    log.info(countAll + " staffes active in DB");
    Page<Staff> pagedResult;
    if (filters != null && filters.size() != 0) {
      log.info("we have just have filters");
      for (Map.Entry<String, String> entry : filters.entrySet()) {
        String key = entry.getKey();
        String value = entry.getValue();
        switch (key) {
          case "person_fullName":
            filteringStaff.addAll(
                staffRepository
                    .findAllByPersonFirstNameContainsOrPersonLastNameContainsAndActiveTrue(
                        value, value, paging)
                    .getContent());
            count =
                staffRepository.countByPersonFirstNameContainsOrPersonLastNameContainsAndActiveTrue(
                    value, value);
            log.info(count + " Staffes by name [" + value + "] for all types");
            break;
          default:
            log.info("value not in list of search !");
        }
      }
      pagedResult = new PageImpl<>(filteringStaff, paging, pageSize);
      return pagedResult.getContent();

    } else {
      log.info("all active staffes");
      pagedResult = staffRepository.findByActiveTrue(paging);
      count = staffRepository.countByActiveTrue();
      log.info(count + " Staffes ");
      return pagedResult.getContent();
    }
  }

  private Sort.Direction getSortDirection(String direction) {
    log.trace("StaffeService.getSortDirection method accessed");
    if (direction.equals("asc")) {
      return Sort.Direction.ASC;
    } else if (direction.equals("desc")) {
      return Sort.Direction.DESC;
    }
    return Sort.Direction.ASC;
  }

  private Map<String, String> getFilters(String[] query) {
    log.trace("StaffeService.getFilters method accessed");
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
