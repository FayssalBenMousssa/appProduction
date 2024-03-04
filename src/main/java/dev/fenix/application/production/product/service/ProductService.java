package dev.fenix.application.production.product.service;

import dev.fenix.application.production.product.model.Product;
import dev.fenix.application.production.product.model.ProductType;
import dev.fenix.application.production.product.repository.ProductRepository;
import dev.fenix.application.production.product.repository.ProductTypeRepository;
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
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductTypeRepository productTypeRepository;

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    private int count = 0;
    private int countAll = 0;
    private Page<Product> pagedResult;

    /**
     * get list of products
     *
     * @param pageNo   page number
     * @param pageSize page size
     * @param sortBy   list of sort & direction
     * @param query    list of query to filter data
     */
    public List<Product> getAllProducts(Integer pageNo, Integer pageSize, String[] sortBy, String[] query) {



        //// Order
        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        if (sortBy[0].contains(",")) {
            // will sort more than 2 columns
            log.trace("we will sort more than 2 columns ");

            for (String sortOrder : sortBy) {
                // sortOrder="column, direction"

                String[] _sort = sortOrder.split(",");
                //log.trace("sortOrder : " + _sort[1] + " " + _sort[0]);
                orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
                log.trace("we will sort      sort=[ " + _sort[1] + " " +  _sort[0] +"]");
            }
        } else {
            // sort=[column, direction]
            orders.add(new Sort.Order(getSortDirection(sortBy[1]), sortBy[0]));
            log.trace("we will sort      sort=[ " + sortBy[1] +  sortBy[0] +"]");

        }

        //// filters
        Map<String, String> filters = getFilters(query);
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(orders));
        List<Product> filteringProducts = new ArrayList<Product>();
        countAll = productRepository.countByActiveTrue();
        //log.info(countAll + " products active in DB");
        if (filters != null && filters.size() != 0) {
            return loadProducts(filters, paging);
        } else {
            return loadProducts(paging);
        }
    }


    // public List<Document> loadDocuments(Pageable paging, Category documentCategory)
    public List<Product> loadProducts(Map<String, String> filters, Pageable paging) {
        filters.forEach((s, s2) ->// documents + " " + s2));

        if (filters.containsKey("name") && filters.containsKey("type_product")) {
           // document"name");
           // document"type_product");
            ProductType productType = productTypeRepository.findOneByCode(filters.get("type_product"));
           // documentproductType.getName());
            pagedResult = productRepository.findAllByNameContainsAndActiveTrueAndProductType(filters.get("name"), productType, paging);
            count = productRepository.countByNameContainsAndActiveTrueAndProductType(filters.get("name"), productType);
            return pagedResult.getContent();

        } else if (filters.containsKey("type_product")) {
           // document"type_product");
            ProductType productType = productTypeRepository.findOneByCode(filters.get("type_product"));
           // documentproductType.getName());
            pagedResult = productRepository.findByActiveTrueAndProductType(productType, paging);
            count = productRepository.countByActiveTrueAndProductType(productType);
            return pagedResult.getContent();
        } else if (filters.containsKey("name")) {
           // document"name");
            pagedResult = productRepository.findAllByNameContainsAndActiveTrue(filters.get("name"), paging);
            count = productRepository.countByNameContainsAndActiveTrue(filters.get("name"));
            return pagedResult.getContent();
        }
        pagedResult = productRepository.findByActiveTrue(paging);
        count = productRepository.countByActiveTrue();
        return pagedResult.getContent();
    }

    public List<Product> loadProducts(Pageable paging) {
        pagedResult = productRepository.findByActiveTrue(paging);
        count = productRepository.countByActiveTrue();
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
