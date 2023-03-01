package dev.fenix.application.api.accounting;

import dev.fenix.application.accounting.model.AccountingEntry;
import dev.fenix.application.accounting.model.LetteringCustomer;
import dev.fenix.application.accounting.repository.AccountingEntryRepository;
import dev.fenix.application.business.model.Company;
import dev.fenix.application.business.model.Job;
import dev.fenix.application.business.repository.CompanyRepository;
import dev.fenix.application.business.repository.JobRepository;
import dev.fenix.application.production.customer.model.Customer;
import javassist.NotFoundException;
import org.jetbrains.annotations.NotNull;
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
                JSONArray jArray = new JSONArray();
                dataStock.getContent().forEach(stockCount -> jArray.put(stockCount.toJson()));
                response.put("results", jArray);
                response.put("count", jArray.length());
                response.put("total", dataStock.getTotalElements());
                return new ResponseEntity<>(response.toString(), HttpStatus.OK);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/lettering", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> lettering(@Valid @RequestBody LetteringCustomer letteringCustomer, HttpServletRequest request) {

       log.trace(letteringCustomer.toJson().toString());

       /* customer.setActive(true);
        Customer savedCustomer = customerRepository.save(customer);*/
        // return ResponseEntity.ok(savedCustomer.toJson().toString());


        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }


    private static String CharLettering(String letter, String letterCase) throws Exception {
        return getString(letter, letterCase);
    }

    @NotNull
    public static String getString(String letter, String letterCase) throws Exception {
        char[] ch = letter.trim().toCharArray();
        int minChar = letterCase == "uppercase" ? 65 : 97;
        int maxChar = letterCase == "uppercase" ? 90 : 122;
        if (
                ((int) ch[0] < minChar || (int) ch[1] < minChar || (int) ch[2] < minChar)
                        || ((int) ch[0] >= maxChar & (int) ch[1] >= maxChar & (int) ch[2] >= maxChar)
        ) {
            throw new Exception(letter + " outside the allowed range !");
        }
        if ((int) ch[2] < maxChar) {
            return ("" + ch[0] + ch[1] + (char) (ch[2] + 1));
        } else if ((int) ch[1] < maxChar) {
            return ("" + ch[0] + (char) (ch[1] + 1) + (letterCase == "uppercase" ? "A" : "a"));
        } else {
            return ("" + (char) (ch[0] + 1) + (letterCase == "uppercase" ? "A" : "a") + (letterCase == "uppercase" ? "A" : "a"));
        }
    }

}
