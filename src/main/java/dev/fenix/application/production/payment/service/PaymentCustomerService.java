package dev.fenix.application.production.payment.service;


import dev.fenix.application.business.model.Company;
import dev.fenix.application.production.customer.model.Customer;
import dev.fenix.application.production.customer.repository.CustomerRepository;
import dev.fenix.application.production.payment.model.PaymentCustomer;
import dev.fenix.application.production.payment.model.PaymentMethod;
import dev.fenix.application.production.payment.model.PaymentStatus;
import dev.fenix.application.production.payment.repository.PaymentCustomerRepository;
import dev.fenix.application.production.payment.repository.PaymentMethodRepository;
import dev.fenix.application.production.payment.repository.PaymentStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PaymentCustomerService {
    @Autowired
    private PaymentCustomerRepository paymentCustomerRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PaymentStatusRepository paymentStatusRepository ;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository ;


    private static final Logger log = LoggerFactory.getLogger(PaymentCustomerService.class);
    private int count = 0;
    private int countAll = 0;

    /**
     * get list of customers
     *
     * @param pageNo   page number
     * @param pageSize page size
     * @param sortBy   list of sort & direction
     * @param query    list of query to filter data
     */
    public List<PaymentCustomer> getAllPaymentCustomers(
            Integer pageNo, Integer pageSize, String[] sortBy, String[] query) {
        log.trace("customerService.get Allcustomers method accessed");
        log.trace("pageNo : " + pageNo);
        log.trace("pageSize : " + pageSize);
        log.trace("sortBy : " + (sortBy != null && sortBy.length > 0 ? Arrays.toString(sortBy) : "no sort"));
        log.trace("query : " + (query != null && query.length > 0 ? Arrays.toString(query) : "no query"));

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
        List<PaymentCustomer> filteringpaymentCustomers = new ArrayList<PaymentCustomer>();

        countAll = paymentCustomerRepository.countByActiveTrue();
        log.info(countAll + " paymentCustomers active in DB");
        Page<PaymentCustomer> pagedResult;


        if (filters != null && filters.size() != 0) {
            try {
                return loadPaymentCustomer(paging, filters);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else {
            log.info("all active paymentCustomers");

            Date startDate = new Date();
            Date endDate = new Date();


            pagedResult = paymentCustomerRepository.findByActiveTrueAndPaymentDateBetween(startDate, endDate, paging);
            count = paymentCustomerRepository.countByActiveTrueAndPaymentDateBetween(startDate, endDate);


            log.info(count + " paymentCustomers ");
            return pagedResult.getContent();
        }
    }

    private List<PaymentCustomer> loadPaymentCustomer(Pageable paging, Map<String, String> filters) throws ParseException {
        Page<PaymentCustomer> pagedResult;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        if (filters.containsKey("startDate") && filters.containsKey("endDate") && filters.containsKey("paymentStatus") && filters.containsKey("idCustomer") && filters.containsKey("paymentMethod")) {
            Customer customer = customerRepository.findOneById(Long.valueOf(filters.get("idCustomer")));
            PaymentStatus paymentStatus = paymentStatusRepository.findOneById(Long.valueOf(filters.get("paymentStatus")));
            PaymentMethod paymentMethod = paymentMethodRepository.findOneById(Long.valueOf(filters.get("paymentMethod")));
            Date startDate = formatter.parse(filters.get("startDate"));
            Date endDate = formatter.parse(filters.get("endDate"));
            pagedResult = paymentCustomerRepository.findByActiveTrueAndPaymentDateBetweenAndCustomerAndPaymentStatusAndPaymentMethod(startDate, endDate, customer,paymentStatus,paymentMethod, paging);
            count = paymentCustomerRepository.countByActiveTrueAndPaymentDateBetweenAndCustomerAndPaymentStatusAndPaymentMethod(startDate, endDate, customer,paymentStatus,paymentMethod);
            log.info("startDate / endDate  / paymentStatus / idCustomer / paymentMethod: ");
        }

        else if (filters.containsKey("startDate") && filters.containsKey("endDate") && filters.containsKey("paymentStatus")  && filters.containsKey("paymentMethod")) {

            PaymentStatus paymentStatus = paymentStatusRepository.findOneById(Long.valueOf(filters.get("paymentStatus")));
            PaymentMethod paymentMethod = paymentMethodRepository.findOneById(Long.valueOf(filters.get("paymentMethod")));
            Date startDate = formatter.parse(filters.get("startDate"));
            Date endDate = formatter.parse(filters.get("endDate"));
            pagedResult = paymentCustomerRepository.findByActiveTrueAndPaymentDateBetweenAndPaymentStatusAndPaymentMethod(startDate, endDate,paymentStatus,paymentMethod, paging);
            count = paymentCustomerRepository.countByActiveTrueAndPaymentDateBetweenAndPaymentStatusAndPaymentMethod(startDate, endDate,paymentStatus,paymentMethod);
            log.info("startDate / endDate / paymentStatus  / paymentMethod ");
        }

      else  if (filters.containsKey("startDate") && filters.containsKey("endDate") &&   filters.containsKey("idCustomer") && filters.containsKey("paymentMethod")) {
            Customer customer = customerRepository.findOneById(Long.valueOf(filters.get("idCustomer")));
            PaymentMethod paymentMethod = paymentMethodRepository.findOneById(Long.valueOf(filters.get("paymentMethod")));
            Date startDate = formatter.parse(filters.get("startDate"));
            Date endDate = formatter.parse(filters.get("endDate"));
            pagedResult = paymentCustomerRepository.findByActiveTrueAndPaymentDateBetweenAndCustomerAndPaymentMethod(startDate, endDate, customer,paymentMethod, paging);
            count = paymentCustomerRepository.countByActiveTrueAndPaymentDateBetweenAndCustomerAndPaymentMethod(startDate, endDate, customer,paymentMethod);
            log.info("startDate / endDate / idCustomer /  paymentMethod");
        }
        else  if (filters.containsKey("startDate") && filters.containsKey("endDate")   && filters.containsKey("paymentMethod")) {

            PaymentMethod paymentMethod = paymentMethodRepository.findOneById(Long.valueOf(filters.get("paymentMethod")));
            Date startDate = formatter.parse(filters.get("startDate"));
            Date endDate = formatter.parse(filters.get("endDate"));
            pagedResult = paymentCustomerRepository.findByActiveTrueAndPaymentDateBetweenAndPaymentMethod(startDate, endDate,paymentMethod, paging);
            count = paymentCustomerRepository.countByActiveTrueAndPaymentDateBetweenAndPaymentMethod(startDate, endDate,paymentMethod);
            log.info("startDate / endDate  /  paymentMethod");
        }


        else if (filters.containsKey("startDate") && filters.containsKey("endDate") && filters.containsKey("paymentStatus") && filters.containsKey("idCustomer")) {
            Customer customer = customerRepository.findOneById(Long.valueOf(filters.get("idCustomer")));
            PaymentStatus paymentStatus = paymentStatusRepository.findOneById(Long.valueOf(filters.get("paymentStatus")));
            Date startDate = formatter.parse(filters.get("startDate"));
            Date endDate = formatter.parse(filters.get("endDate"));
            pagedResult = paymentCustomerRepository.findByActiveTrueAndPaymentDateBetweenAndCustomerAndPaymentStatus(startDate, endDate, customer,paymentStatus, paging);
            count = paymentCustomerRepository.countByActiveTrueAndPaymentDateBetweenAndCustomerAndPaymentStatus(startDate, endDate, customer,paymentStatus);
            log.info("startDate / endDate /  paymentStatus / idCustomer");
        }
        else if (filters.containsKey("startDate") && filters.containsKey("endDate") && filters.containsKey("paymentStatus")) {
            PaymentStatus paymentStatus = paymentStatusRepository.findOneById(Long.valueOf(filters.get("paymentStatus")));
            Date startDate = formatter.parse(filters.get("startDate"));
            Date endDate = formatter.parse(filters.get("endDate"));
            pagedResult = paymentCustomerRepository.findByActiveTrueAndPaymentDateBetweenAndPaymentStatus(startDate, endDate,paymentStatus, paging);
            count = paymentCustomerRepository.countByActiveTrueAndPaymentDateBetweenAndPaymentStatus(startDate, endDate,paymentStatus);
            log.info("startDate / endDate / paymentStatus ");
        }
        else if (filters.containsKey("startDate") && filters.containsKey("endDate") && filters.containsKey("idCustomer")) {
            Customer customer = customerRepository.findOneById(Long.valueOf(filters.get("idCustomer")));
            Date startDate = formatter.parse(filters.get("startDate"));
            Date endDate = formatter.parse(filters.get("endDate"));
            pagedResult = paymentCustomerRepository.findByActiveTrueAndPaymentDateBetweenAndCustomer(startDate, endDate, customer, paging);
            count = paymentCustomerRepository.countByActiveTrueAndPaymentDateBetweenAndCustomer(startDate, endDate, customer);
            log.info("startDate / endDate / idCustomer ");
        } else if (filters.containsKey("startDate") && filters.containsKey("endDate")) {
            Date startDate = formatter.parse(filters.get("startDate"));
            Date endDate = formatter.parse(filters.get("endDate"));
            pagedResult = paymentCustomerRepository.findByActiveTrueAndPaymentDateBetween(startDate, endDate, paging);
            count = paymentCustomerRepository.countByActiveTrueAndPaymentDateBetween(startDate, endDate);
            log.info("startDate / endDate : ");
        } else {
            Date startDate = new Date();
            Date endDate = new Date();
            pagedResult = paymentCustomerRepository.findByActiveTrueAndPaymentDateBetween(startDate, endDate, paging);
            count = paymentCustomerRepository.countByActiveTrueAndPaymentDateBetween(startDate, endDate);
            log.info("all active paymentCustomer  ... today : ");
        }
        return pagedResult.getContent();

    }

    private Sort.Direction getSortDirection(String direction) {
        log.trace("customerService.getSortDirection method accessed");
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

    private Map<String, String> getFilters(String[] query) {
        log.trace("customerService.getFilters method accessed");
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
