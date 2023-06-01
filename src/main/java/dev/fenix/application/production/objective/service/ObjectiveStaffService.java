package dev.fenix.application.production.objective.service;


import dev.fenix.application.business.model.Staff;
import dev.fenix.application.business.repository.StaffRepository;
import dev.fenix.application.production.objective.model.Objective;
import dev.fenix.application.production.objective.repository.ObjectiveStaffRepository;
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
public class ObjectiveStaffService {
    @Autowired
    private ObjectiveStaffRepository objectiveStaffRepository;

    @Autowired
    private StaffRepository staffRepository;

    private static final Logger log = LoggerFactory.getLogger(ObjectiveStaffService.class);
    private int count = 0;
    private int countAll = 0;
    private Page<Objective> pagedResult;

    /**
     * get list of objectiveStaffes
     *
     * @param pageNo   page number
     * @param pageSize page size
     * @param sortBy   list of sort & direction
     * @param query    list of query to filter data
     */
    public List<Objective> getAllObjectiveStaffs(
            Integer pageNo, Integer pageSize, String[] sortBy, String[] query) throws ParseException {

        //log.trace("ObjectiveStaffeService.getAllObjectiveStaffes method accessed");

        //log.trace("pageNo : " + pageNo);
        //log.trace("pageSize : " + pageSize);
        //log.trace("sortBy : " + (sortBy != null && sortBy.length > 0 ? Arrays.toString(sortBy) : "no sort"));
        //log.trace("query : " + (query != null && query.length > 0 ? Arrays.toString(query) : "no query"));

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


        countAll = objectiveStaffRepository.countByActiveTrue();


        if (filters != null && filters.size() != 0) {
          return loadObjectives(paging,filters);

        } else {
            //log.info("all active objectiveStaffes");
            pagedResult = objectiveStaffRepository.findByActiveTrue(paging);
            count = objectiveStaffRepository.countByActiveTrue();
            //log.info(count + " ObjectiveStaffes ");
            return pagedResult.getContent();
        }
    }

    private List<Objective> loadObjectives(Pageable paging, Map<String, String> filters) throws ParseException {

        if (filters.containsKey("staff_id") && filters.containsKey("start_date") && filters.containsKey("end_date")) {
            Staff staff = staffRepository.getOne(Long.valueOf(filters.get("staff_id")));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = formatter.parse(filters.get("start_date"));
            Date endDate = formatter.parse(filters.get("end_date"));
            pagedResult = objectiveStaffRepository.findByStaffAndStartDateAndEndDateAndActiveTrue(staff, startDate, endDate, paging);
            count = objectiveStaffRepository.countByStaffAndStartDateAndEndDateAndActiveTrue(staff, startDate, endDate);
            return pagedResult.getContent();
        } else if (filters.containsKey("start_date") && filters.containsKey("end_date")){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = formatter.parse(filters.get("start_date"));
            Date endDate = formatter.parse(filters.get("end_date"));
            pagedResult = objectiveStaffRepository.findByStartDateAndEndDateAndActiveTrue( startDate, endDate, paging);
            count = objectiveStaffRepository.countByStartDateAndEndDateAndActiveTrue( startDate, endDate);
            return pagedResult.getContent();
        }else {
            return null;
        }
    }


    private Sort.Direction getSortDirection(String direction) {
        //log.trace("ObjectiveStaffeService.getSortDirection method accessed");
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

    private Map<String, String> getFilters(String[] query) {
        //log.trace("ObjectiveStaffeService.getFilters method accessed");
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
