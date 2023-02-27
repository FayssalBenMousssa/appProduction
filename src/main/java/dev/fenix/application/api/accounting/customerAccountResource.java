package dev.fenix.application.api.accounting;

import dev.fenix.application.accounting.model.AccountingEntry;
import dev.fenix.application.accounting.repository.AccountingEntryRepository;
import dev.fenix.application.business.model.Company;
import dev.fenix.application.business.model.Job;
import dev.fenix.application.business.repository.CompanyRepository;
import dev.fenix.application.business.repository.JobRepository;
import javassist.NotFoundException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController()
@RequestMapping("/api/customer/account")
public class customerAccountResource {
    private static final Logger log = LoggerFactory.getLogger(customerAccountResource.class);

    @Autowired
    private AccountingEntryRepository accountingEntryRepository;
    @Autowired
    private CompanyRepository companyRepository;


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> index(HttpServletRequest request,
                                        @RequestParam(required = false) Long type,
                                        @RequestParam(defaultValue = "0") Integer page,
                                        @RequestParam(defaultValue = "200") Integer size,
                                        @RequestParam(defaultValue = "id,desc") String[] sort,
                                        @RequestParam(required = false) String[] query,
                                        @PathVariable Long id
    ) {

        Pageable paging = PageRequest.of(0, 100, Sort.by("id"));
        Company company = companyRepository.findOneById(id);
        if (company != null) {
            Page<AccountingEntry> dataStock = accountingEntryRepository.accountingEntry(company.getId(), paging);
            dataStock.getTotalElements();
            JSONObject response = new JSONObject();
            try {
                response.put("results", dataStock);
                response.put("count", dataStock.getTotalPages());
                response.put("total", dataStock.getTotalElements());
                return new ResponseEntity<>(response.toString(), HttpStatus.OK);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }


}
