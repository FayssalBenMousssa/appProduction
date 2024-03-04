package dev.fenix.application.production.customer.service;

import dev.fenix.application.person.model.Person;
import dev.fenix.application.person.repository.PersonRepository;
import dev.fenix.application.production.customer.model.Customer;
import dev.fenix.application.production.customer.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {
  @Autowired private CustomerRepository customerRepository;
  @Autowired private PersonRepository personRepository;

  private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
  private int count = 0;
  private int countAll = 0;
  private Page<Customer> pagedResult;
  /**
   * get list of customers
   *
   * @param pageNo page number
   * @param pageSize page size
   * @param sortBy list of sort & direction
   * @param query list of query to filter data
   */
  public List<Customer> getAllCustomers(
      Integer pageNo, Integer pageSize, String[] sortBy, String[] query) {


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
    List<Customer> filteringcustomers = new ArrayList<Customer>();

    countAll = customerRepository.countByActiveTrue();
    //log.info(countAll + " customers active in DB");

    if (filters != null && filters.size() != 0) {

     return loadCustomers(filters,paging);

    } else {
      //log.info("all active customers");
      pagedResult = customerRepository.findByActiveTrue(paging);
      count = customerRepository.countByActiveTrue();
      //log.info(count + " customers ");
      return pagedResult.getContent();
    }
  }

  public List<Customer> loadCustomers(Map<String, String> filters, Pageable paging){
   // document"loadCustomers");
    if (filters.containsKey("social_reason") && filters.containsKey("user_customers")  ) {
      //log.info("code :  companyId  : filterStatus");
    } else if (filters.containsKey("social_reason")  ) {
      pagedResult = customerRepository.findAllBySocialReasonContainsAndActiveTrue(filters.get("social_reason"), paging);
      count = customerRepository.countBySocialReasonContainsAndActiveTrue(filters.get("social_reason"));
      return pagedResult.getContent();

    }else if ( filters.containsKey("user_customers")) {

     // document"user_customers");

      UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      String username = userDetails.getUsername();
      Person person =   personRepository.findByUserAccount_UserNameAndUserAccount_ActiveTrue(username);
      pagedResult = customerRepository.findByCustomerStaff_Staff_PersonAndCustomerStaff_ActiveTrue(person, paging);
      count = customerRepository.countByCustomerStaff_Staff_PersonAndCustomerStaff_ActiveTrue(person);
      return pagedResult.getContent();
    }else {
      pagedResult = customerRepository.findByActiveTrue(paging);
      count = customerRepository.countByActiveTrue();
      //log.info(count + " customers ");
      return pagedResult.getContent();
    }
    return  null;
  }

  private Sort.Direction getSortDirection(String direction) {
    //log.trace("customerService.getSortDirection method accessed");
    if (direction.equals("asc")) {
      return Sort.Direction.ASC;
    } else if (direction.equals("desc")) {
      return Sort.Direction.DESC;
    }
    return Sort.Direction.ASC;
  }

  private Map<String, String> getFilters(String[] query) {
    //log.trace("customerService.getFilters method accessed");
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
