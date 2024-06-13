package dev.fenix.application.production.visit.service;


import dev.fenix.application.business.model.Staff;
import dev.fenix.application.business.repository.StaffRepository;
import dev.fenix.application.production.visit.model.Visit;
import dev.fenix.application.production.visit.repository.VisitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class VisitService {
    @Autowired
    private VisitRepository visitRepository;

    @Autowired private StaffRepository staffRepository;

 

    private static final Logger log = LoggerFactory.getLogger(VisitService.class);
    private int count = 0;
    private int countAll = 0;
    private Page<Visit> pagedResult;

    /**
     * get list of visites
     *
     * @param pageNo   page number
     * @param pageSize page size
     * @param sortBy   list of sort & direction
     * @param query    list of query to filter data
     */
    public List<Visit> getAllVisits(
            Integer pageNo, Integer pageSize, String[] sortBy, String[] query) throws ParseException {

        
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


        countAll = visitRepository.countByActiveTrue();


        if (filters != null && filters.size() != 0) {
            return loadVisits(paging,filters);

        } else {
            //log.info("all active visites");
            pagedResult = visitRepository.findByActiveTrue(paging);
            count = visitRepository.countByActiveTrue();
            //log.info(count + " Visits ");
            return pagedResult.getContent();
        }
    }

    private List<Visit> loadVisits(Pageable paging, Map<String, String> filters) throws ParseException {

         if (filters.containsKey("start_date") && filters.containsKey("end_date")  && filters.containsKey("staff_id")){


             Long staff_id = Long.valueOf(filters.get("staff_id").trim());
             Staff staff = staffRepository.getStaffById(staff_id);
             SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
             Date startDate = formatter.parse(filters.get("start_date"));
             Date endDate = formatter.parse(filters.get("end_date"));
             pagedResult = visitRepository.findByCustomer_ActiveTrueAndScheduledVisitDateBetweenAndStaff( startDate, endDate,staff, paging);
             count = visitRepository.countByCustomer_ActiveTrueAndScheduledVisitDateBetweenAndStaff( startDate, endDate,staff);
             return pagedResult.getContent();


        } else if (filters.containsKey("start_date") && filters.containsKey("end_date")  ) {


             SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
             Date startDate = formatter.parse(filters.get("start_date"));
             Date endDate = formatter.parse(filters.get("end_date"));
             pagedResult = visitRepository.findByScheduledVisitDateBetweenAndActiveTrue( startDate, endDate, paging);
             count = visitRepository.countByScheduledVisitDateBetweenAndActiveTrue( startDate, endDate);
             return pagedResult.getContent();
         } else {
            return new ArrayList<>();
        }
    }


    private Sort.Direction getSortDirection(String direction) {
        //log.trace("VisiteService.getSortDirection method accessed");
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

    private Map<String, String> getFilters(String[] query) {
        //log.trace("VisiteService.getFilters method accessed");
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
