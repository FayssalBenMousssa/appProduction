package dev.fenix.application.production.stock.service;


import dev.fenix.application.production.logistic.model.Depot;
import dev.fenix.application.production.logistic.repository.DepotRepository;
import dev.fenix.application.production.product.model.Product;
import dev.fenix.application.production.product.repository.ProductRepository;
import dev.fenix.application.production.stock.model.StockCount;
import dev.fenix.application.production.stock.model.StockMovement;
import dev.fenix.application.production.stock.repository.StockRepository;
import dev.fenix.application.production.treatment.model.Status;
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
public class StockService {
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private DepotRepository depotRepository;

    @Autowired
    private ProductRepository productRepository;


    private static final Logger log = LoggerFactory.getLogger(StockService.class);
    private int count = 0;
    private int countAll = 0;
    private List<Status> statusStock = Arrays.asList(Status.APPROVED, Status.CLOSED);

    /**
     * get list of Stocks
     *
     * @param pageNo   page number
     * @param pageSize page size
     * @param sortBy   list of sort & direction
     * @param query    list of query to filter data
     */
    public Page<StockCount> getAllStocks(Integer pageNo, Integer pageSize, String[] sortBy, String[] query) {


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


        /// if we have filters
        if (filters != null && filters.size() != 0) {
            return this.loadStock(paging, filters);
        }

        return null;
    }


    private Page<StockCount> loadStock(Pageable paging, Map<String, String> filters) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = formatter.parse(filters.get("date"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        /// quantity:actual

        if(filters.containsKey("quantity") && filters.get("quantity").equals("actual") ){
            //log.info("quantity : actual");
            statusStock = Arrays.asList(Status.APPROVED, Status.CLOSED);
        } else if (  filters.containsKey("quantity") && filters.get("quantity").equals("reserved") ) {
            //log.info("quantity : reserved");
            statusStock = Arrays.asList(Status.APPROVED, Status.CLOSED,Status.COMPLETE,Status.INITIATED);
        }else {
            statusStock = Arrays.asList(Status.APPROVED, Status.CLOSED);
        }



        if (filters.containsKey("date") && filters.containsKey("idProduct") && filters.containsKey("depot") && filters.containsKey("groupedByBatch")) {
            Product product = productRepository.findById(Long.valueOf(filters.get("idProduct"))).orElse(null);
            Depot depot = depotRepository.findById(Long.valueOf(filters.get("depot"))).orElse(null);
            return stockRepository.stockProductGroupProductBatchDepot(statusStock, date, product, depot, paging);
        } else if (filters.containsKey("date") && filters.containsKey("idProduct") && filters.containsKey("depot")) {
            Product product = productRepository.findById(Long.valueOf(filters.get("idProduct"))).orElse(null);
            Depot depot = depotRepository.findById(Long.valueOf(filters.get("depot"))).orElse(null);
            return stockRepository.stockProductGroupProductDepot(statusStock, date, product, depot, paging);
        } else if (filters.containsKey("date") && filters.containsKey("idProduct") && filters.containsKey("groupedByBatch")) {
            Product product = productRepository.findById(Long.valueOf(filters.get("idProduct"))).orElse(null);
            //log.info("date & idProduct");
            return stockRepository.stockProductGroupProductBatch(statusStock, date, product, paging);
        } else if (filters.containsKey("date") && filters.containsKey("idProduct")) {
            Product product = productRepository.findById(Long.valueOf(filters.get("idProduct"))).orElse(null);
            //log.info("date & idProduct");
            return stockRepository.stockProductGroupProduct(statusStock, date, product, paging);
        } else if (filters.containsKey("date") && filters.containsKey("groupedByBatch") && filters.containsKey("depot")) {
            Depot depot = depotRepository.findById(Long.valueOf(filters.get("depot"))).orElse(null);
            return stockRepository.stockGroupProductBatchDepot(statusStock, date, depot, paging);
        } else if (filters.containsKey("date") && filters.containsKey("groupedByBatch")) {
            return stockRepository.stockGroupProductBatch(statusStock, date, paging);
        } else if (filters.containsKey("date") && filters.containsKey("depot")) {
            Depot depot = depotRepository.findById(Long.valueOf(filters.get("depot"))).orElse(null);
            return stockRepository.stockGroupProductDepot(statusStock, date, depot, paging);
        } else if (filters.containsKey("date")) {
            return stockRepository.stockGroupProduct(statusStock, date, paging);
        }
        return stockRepository.stockGroupProductBatch(statusStock, date, paging);
    }


    public Page<StockMovement> getAllMovements(Integer pageNo, Integer pageSize, String[] sortBy, String[] query) {


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


        /// if we have filters

        return this.loadMovements(paging, filters);


    }


    private Page<StockMovement> loadMovements(Pageable paging, Map<String, String> filters) {
/*
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = formatter.parse(filters.get("date"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
*/
        return stockRepository.getMovement(statusStock, paging);
    }


    private Sort.Direction getSortDirection(String direction) {
        //log.trace("StockService.getSortDirection method accessed");
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

    private Map<String, String> getFilters(String[] query) {
        //log.trace("StockService.getFilters method accessed");
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
