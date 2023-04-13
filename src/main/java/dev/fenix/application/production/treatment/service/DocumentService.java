package dev.fenix.application.production.treatment.service;

import dev.fenix.application.business.model.Company;
import dev.fenix.application.business.repository.CompanyRepository;
import dev.fenix.application.production.treatment.model.Category;
import dev.fenix.application.production.treatment.model.Document;
import dev.fenix.application.production.treatment.model.Status;
import dev.fenix.application.production.treatment.model.Type;
import dev.fenix.application.production.treatment.repository.CategoryRepository;
import dev.fenix.application.production.treatment.repository.DocumentRepository;
import dev.fenix.application.production.treatment.repository.TypeRepository;
import dev.fenix.application.security.model.Role;
import dev.fenix.application.security.model.User;
import dev.fenix.application.security.repository.AccessDocumentsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private AccessDocumentsRepository accessDocumentsRepository;
    private static final Logger log = LoggerFactory.getLogger(DocumentService.class);
    private int count = 0;
    private int countAll = 0;

    private Page<Document> pagedResult = null;
    private final List<Status> workFlow = Arrays.asList(Status.INITIATED , Status.APPROVED);
    private List<Type> typeList;
    private List<String> accessList;

    /**
     * get list of documents
     *
     * @param pageNo    page number
     * @param pageSize  page size
     * @param sortBy    list of sort & direction
     * @param query     list of query to filter data
     * @param type      type of document
     * @param category  category of document
     * @param toInvoice document boolean
     */
    public List<Document> getAllDocuments(Integer pageNo, Integer pageSize, String[] sortBy, String[] query, Long type, Long category, boolean toInvoice, User CurrentUser) throws UnsupportedEncodingException {
        typeList = new ArrayList<>();
        accessList = new ArrayList<>();
        Set<Role> userRoles = CurrentUser.getRoles();
        //log.info(CurrentUser.getUserName() + " : ");
        userRoles.forEach(
                role -> {
                    role.getAccessDocuments()
                            .forEach(accessDocuments -> {
                                typeList.add(accessDocuments.getType());
                                accessList.add(accessDocuments.getType().getId() + "_" + accessDocuments.getStatus());
                            });
                });


        // typeList.forEach(type1 -> //log.info("Type : " + type1.getName() ));


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

        countAll = documentRepository.countByActiveTrue();
        //log.info(countAll + " documents active in DB");


        if (category != null) {
            Category documentCategory = categoryRepository.findOneById(category);
            if (toInvoice) {
                return loadDocumentsToInvoice(paging, documentCategory, filters);
            }
            if (filters != null) {
                return loadDocuments(paging, documentCategory, filters);
            }
            return loadDocuments(paging, documentCategory);
        } else if (type != null) {

            Type documentType = typeRepository.findOneById(type);

            if (filters != null) {
                return loadDocuments(paging, documentType, filters);
            }

            return loadDocuments(paging, documentType);
        } else {
            return loadDocuments(paging);
        }
    }

    private List<Document> loadDocuments(Pageable paging, Type documentType, Map<String, String> filters) {

        if (filters.containsKey("code") && filters.containsKey("companyId") && filters.containsKey("filterStatus")) {
            //log.info("code :  companyId  : filterStatus");
        } else if (filters.containsKey("code") && filters.containsKey("companyId")) {
            Company source = companyRepository.findOneById(Long.valueOf(filters.get("companyId")));
            pagedResult = documentRepository.findAllByCodeContainsAndActiveTrueAndTypeAndSourceAndAccessIn(filters.get("code"), documentType, source, accessList, paging);
            count = documentRepository.countByCodeContainsAndActiveTrueAndTypeAndSourceAndAccessIn(filters.get("code"), documentType, source, accessList);
            //log.info(" active documents : Code " + filters.get("code") + " Name : " + documentType.getName() + "  ... category && filterStatus:  and company :" + source.getSocialReason());
        } else if (filters.containsKey("code") && filters.containsKey("filterStatus")) {
            pagedResult = documentRepository.findAllByCodeContainsAndActiveTrueAndTypeAndStatusInAndAccessIn(filters.get("code"), documentType, workFlow, accessList, paging);
            count = documentRepository.countByCodeContainsAndActiveTrueAndTypeAndStatusInAndAccessIn(filters.get("code"), documentType, workFlow, accessList);
            //log.info("all active documents ... TYPE  && filterStatus: " + documentType.getName() + " code : " + filters.get("code"));
            //log.info("code :  filterStatus  ");
        } else if (filters.containsKey("companyId") && filters.containsKey("filterStatus")) {
            //log.info("companyId :  filterStatus  ");
        } else if (filters.containsKey("code")) {
            pagedResult = documentRepository.findAllByCodeContainsAndActiveTrueAndTypeAndAccessIn(filters.get("code"), documentType, accessList, paging);
            count = documentRepository.countByCodeContainsAndActiveTrueAndTypeAndAccessIn(filters.get("code"), documentType, accessList);
            //log.info("all active documents ... documentType : " + documentType.getName() + " code : " + filters.get("code"));
        } else if (filters.containsKey("companyId")) {
            Company source = companyRepository.findOneById(Long.valueOf(filters.get("companyId")));
            pagedResult = documentRepository.findAllByActiveTrueAndTypeAndSourceAndAccessIn(documentType, source, accessList, paging);
            count = documentRepository.countByActiveTrueAndTypeAndSourceAndAccessIn(documentType, source, accessList);
            //log.info("all active documents : " + documentType.getName() + "  ... category && filterStatus:  and company :" + source.getSocialReason());
        } else if (filters.containsKey("filterStatus")) {
            pagedResult = documentRepository.findByActiveTrueAndTypeAndStatusInAndAccessIn(documentType, workFlow, accessList, paging);
            count = documentRepository.countByActiveTrueAndTypeAndStatusInAndAccessIn(documentType, workFlow, accessList);
            //log.info("all active documents  .. documentType : " + documentType.getName() + " with filterStatus ");
        } else {
            //log.info("all active documents ... documentType : " + documentType.getName());
        }
        return pagedResult.getContent();
    }

    public List<Document> loadDocuments(Pageable paging) {
        pagedResult = documentRepository.findALLByActiveTrueAndAccessIn(accessList, paging);
        count = documentRepository.countByActiveTrueAndAccessIn(accessList);
        //log.info("all active documents ... ");
        return pagedResult.getContent();
    }

    public List<Document> loadDocuments(Pageable paging, Category documentCategory) {


        pagedResult = documentRepository.findByActiveTrueAndTypeCategoryAndAccessIn(documentCategory, accessList, paging);
        count = documentRepository.countByActiveTrueAndTypeCategoryAndAccessIn(documentCategory, accessList);
        //log.info("all active documents ... category : " + documentCategory.getName());
        return pagedResult.getContent();
    }

    public List<Document> loadDocuments(Pageable paging, Type documentType) {
        pagedResult = documentRepository.findByActiveTrueAndTypeAndAccessIn(documentType, accessList, paging);
        count = documentRepository.countByActiveTrueAndTypeAndAccessIn(documentType, accessList);
        //log.info("all active documents ... type : " + documentType.getName());
        return pagedResult.getContent();
    }



    public List<Document> loadDocuments(Pageable paging, Category documentCategory, Map<String, String> filters) {
        if (filters.containsKey("code") && filters.containsKey("companyId") && filters.containsKey("filterStatus")) {
            Company source = companyRepository.findOneById(Long.valueOf(filters.get("companyId")));
            pagedResult = documentRepository.loadDocuments_findAllByCodeContainsAndActiveTrueAndTypeCategoryAndSourceAndStatusInAndAccessIn(filters.get("code"), documentCategory, source, workFlow, accessList, paging);
            count = documentRepository.loadDocuments_countByCodeContainsAndActiveTrueAndTypeCategoryAndSourceAndStatusInAndAccessIn(filters.get("code"), documentCategory, source, workFlow, accessList);
            //log.info("all active documents ... category && filterStatus: " + documentCategory.getName() + " code : " + filters.get("code") + "and company :" + source.getSocialReason());
        } else if (filters.containsKey("code") && filters.containsKey("companyId")) {
            Company source = companyRepository.findOneById(Long.valueOf(filters.get("companyId")));
            pagedResult = documentRepository.loadDocuments_findAllByCodeContainsAndActiveTrueAndTypeCategoryAndSourceAndAccessIn(filters.get("code"), documentCategory, source, accessList, paging);
            count = documentRepository.loadDocuments_countByCodeContainsAndActiveTrueAndTypeCategoryAndSourceAndAccessIn(filters.get("code"), documentCategory, source, accessList);
            //log.info("all active documents ... category && filterStatus: " + filters.get("code") + "and company :" + source.getSocialReason());
        } else if (filters.containsKey("code") && filters.containsKey("filterStatus")) {
            pagedResult = documentRepository.findAllByCodeContainsAndActiveTrueAndTypeCategoryAndStatusInAndAccessIn(filters.get("code"), documentCategory, workFlow, accessList, paging);
            count = documentRepository.countByCodeContainsAndActiveTrueAndTypeCategoryAndStatusInAndAccessIn(filters.get("code"), documentCategory, workFlow, accessList);
            //log.info("all active documents ... category && filterStatus: " + documentCategory.getName() + " code : " + filters.get("code"));
        } else if (filters.containsKey("companyId") && filters.containsKey("filterStatus")) {
            Company source = companyRepository.findOneById(Long.valueOf(filters.get("companyId")));


            /////
            pagedResult = documentRepository.loadDocuments_findByActiveTrueAndTypeCategoryAndSourceAndStatusInAndAccessIn(documentCategory, source, workFlow, accessList, paging);
            count = documentRepository.loadDocuments_countByActiveTrueAndTypeCategoryAndSourceAndStatusInAndAccessIn(documentCategory, source, workFlow, accessList);
           // count = 100;// documentRepository.countByActiveTrueAndTypeCategoryAndSourceAndStatusInAndAccessIn(documentCategory, source,  workFlow , accessList);
            //log.info("all active documents ... category && filterStatus: " + documentCategory.getName() + "and source :" + source.getSocialReason());
        } else if (filters.containsKey("code")) {
            pagedResult = documentRepository.findAllByCodeContainsAndActiveTrueAndTypeCategoryAndAccessIn(filters.get("code"), documentCategory, accessList, paging);
            count = documentRepository.countByCodeContainsAndActiveTrueAndTypeCategoryAndAccessIn(filters.get("code"), documentCategory, accessList);
            //log.info("all active documents ... category : " + documentCategory.getName() + " code : " + filters.get("code"));
        } else if (filters.containsKey("companyId")) {
            Company source = companyRepository.findOneById(Long.valueOf(filters.get("companyId")));

            pagedResult = documentRepository.loadDocuments_findByActiveTrueAndTypeCategoryAndSourceAndAccessIn(documentCategory, source, accessList, paging);
            count = documentRepository.loadDocuments_countByActiveTrueAndTypeCategoryAndSourceAndAccessIn(documentCategory, source, accessList);

            //log.info("all active documents ... category : " + documentCategory.getName() + " company: " + source.getSocialReason());
        } else if (filters.containsKey("filterStatus")) {
            pagedResult = documentRepository.findByActiveTrueAndTypeCategoryAndStatusInAndAccessIn(documentCategory, workFlow, accessList, paging);
            count = documentRepository.countByActiveTrueAndTypeCategoryAndStatusInAndAccessIn(documentCategory, workFlow, accessList);
            //log.info("all active documents  .. category : " + documentCategory.getName() + " with filterStatus ");
        } else {
            pagedResult = documentRepository.findByActiveTrueAndTypeCategoryAndAccessIn(documentCategory, accessList, paging);
            count = documentRepository.countByActiveTrueAndTypeCategoryAndAccessIn(documentCategory, accessList);
            //log.info("all active documents ... category : " + documentCategory.getName());
        }
        return pagedResult.getContent();
    }



    private List<Document> loadDocumentsToInvoice(Pageable paging, Category documentCategory, Map<String, String> filters) {
        //log.info("load Documents ToInvoice") ;

       // companyId
        // filterStatus
        if (   filters != null && filters.containsKey("filterStatus") && filters.containsKey("companyId")) {
          //   boolean filterStatus = Boolean.parseBoolean(filters.get("filterStatus"));

            //log.info("companyId : " + filters.get("companyId") );


            Company company = companyRepository.findOneById(Long.valueOf(filters.get("companyId")));
            pagedResult = documentRepository.loadDocumentsToInvoice_ByActiveTrueAndTypeToInvoiceTrueAndTypeCategoryAndStatusInAndSourceOrDestinationAndAccessIn(documentCategory, workFlow, company, accessList, paging);
            count = documentRepository.countDocumentsToInvoice_ByActiveTrueAndTypeToInvoiceTrueAndTypeCategoryAndStatusInAndSourceOrDestinationAndAccessIn(documentCategory, workFlow, company, accessList);
            return pagedResult.getContent();

        } else if (filters != null && filters.containsKey("filterStatus") ) {
          //  boolean filterStatus = Boolean.parseBoolean(filters.get("filterStatus"));

            //log.info("filterStatus : " + filters.get("filterStatus") );

            Company company = companyRepository.findOneById(Long.valueOf(filters.get("companyId")));
            pagedResult = documentRepository.loadDocumentsToInvoice_ByActiveTrueAndTypeToInvoiceTrueAndTypeCategoryAndStatusInAndSourceOrDestinationAndAccessIn(documentCategory, workFlow, company, accessList, paging);
            count = documentRepository.countDocumentsToInvoice_ByActiveTrueAndTypeToInvoiceTrueAndTypeCategoryAndStatusInAndSourceOrDestinationAndAccessIn(documentCategory, workFlow, company, accessList);
            return pagedResult.getContent();


        } else if (filters != null &&  filters.containsKey("companyId")  ) {
            //log.info("companyId : " + filters.get("companyId") );

            boolean filterStatus = Boolean.parseBoolean(filters.get("filterStatus"));
            Company company = companyRepository.findOneById(Long.valueOf(filters.get("companyId")));
            pagedResult = documentRepository.loadDocumentsToInvoice_ByActiveTrueAndTypeToInvoiceTrueAndTypeCategoryAndStatusInAndSourceOrDestinationAndAccessIn(documentCategory, workFlow, company, accessList, paging);
            count = documentRepository.countDocumentsToInvoice_ByActiveTrueAndTypeToInvoiceTrueAndTypeCategoryAndStatusInAndSourceOrDestinationAndAccessIn(documentCategory, workFlow, company, accessList);
            return pagedResult.getContent();


        }else {
            //log.info(" else  " + "...... ");
            pagedResult = documentRepository.loadDocumentsToInvoice_ByActiveTrueAndTypeToInvoiceTrueAndTypeCategoryAndStatusInAndAccessIn(documentCategory, workFlow, accessList, paging);
            count = documentRepository.countDocumentsToInvoice_ByActiveTrueAndTypeToInvoiceTrueAndTypeCategoryAndStatusInAndAccessIn(documentCategory, workFlow, accessList);
            return pagedResult.getContent();
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

    private Map<String, String> getFilters(String[] query) throws UnsupportedEncodingException {
        //// URLDecoder.decode(query[0], StandardCharsets.UTF_8.name())
        if (query != null && java.net.URLDecoder.decode(query[0], StandardCharsets.UTF_8).contains(":")) {
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
