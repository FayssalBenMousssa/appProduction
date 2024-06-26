package dev.fenix.application.accounting.service;

import dev.fenix.application.accounting.model.AccountingEntry;
import dev.fenix.application.accounting.repository.AccountingEntryRepository;
import dev.fenix.application.business.model.Company;
import dev.fenix.application.business.repository.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountingEntryService {
  @Autowired private AccountingEntryRepository accountingEntryRepository;
  @Autowired private CompanyRepository companyRepository;
  private static final Logger log = LoggerFactory.getLogger(AccountingEntryService.class);
  private long count = 0;
  private int countAll = 0;

  private Page<AccountingEntry> pagedResult = null;

  /**
   * get list of accountingEntryes
   *
   * @param pageNo page number
   * @param pageSize page size
   * @param sortBy list of sort & direction
   * @param query list of query to filter data
   */
  public List<AccountingEntry> getAllAccountingEntry(Long companyId, Integer pageNo, Integer pageSize, String[] sortBy, String[] query) {

    Company company = companyRepository.findOneById(companyId);

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



    if (company != null) {

      return getAccountingEntry( company, paging , filters);
    }
    return  null;
  }

  private List<AccountingEntry> getAccountingEntry(Company company, Pageable paging, Map<String, String> filters) {
    if (filters.containsKey("startDate") && filters.containsKey("endDate")  ) {
      String  startDate = filters.get("startDate");
      String  endDate = filters.get("endDate");
      pagedResult = accountingEntryRepository.accountingEntry(company.getId(), startDate , endDate , paging );
      count = pagedResult.getTotalElements();

    } else {
            //log.info("Not work");
    }
    return pagedResult.getContent();
  }







  private Sort.Direction getSortDirection(String direction) {
    //log.trace("AccountingEntryeService.getSortDirection method accessed");
    if (direction.equals("asc")) {
      return Sort.Direction.ASC;
    } else if (direction.equals("desc")) {
      return Sort.Direction.DESC;
    }
    return Sort.Direction.ASC;
  }

  private Map<String, String> getFilters(String[] query) {
    //log.trace("AccountingEntryeService.getFilters method accessed");
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

  public long getCount() {
    return count;
  }
  public void setCount(long count) {
    this.count = count;
  }
  public int getCountAll() {
    return countAll;
  }
  public void setCountAll(int countAll) {
    this.countAll = countAll;
  }
}
