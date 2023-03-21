package dev.fenix.application.api.accounting;

import dev.fenix.application.accounting.model.AccountingEntry;
import dev.fenix.application.accounting.model.Letter;
import dev.fenix.application.accounting.model.LetterCase;
import dev.fenix.application.accounting.model.LetteringCustomer;
import dev.fenix.application.accounting.repository.AccountingEntryRepository;
import dev.fenix.application.accounting.repository.LetteringCustomerRepository;
import dev.fenix.application.business.model.Company;
import dev.fenix.application.business.repository.CompanyRepository;
import dev.fenix.application.production.customer.model.Customer;
import dev.fenix.application.production.customer.repository.CustomerRepository;
import dev.fenix.application.production.payment.model.PaymentCustomer;
import dev.fenix.application.production.payment.repository.PaymentCustomerRepository;
import dev.fenix.application.production.treatment.model.Document;
import dev.fenix.application.production.treatment.repository.DocumentRepository;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController()
@RequestMapping("/api/customer/account")
public class CustomerAccountResource {
    private static final Logger log = LoggerFactory.getLogger(CustomerAccountResource.class);

    @Autowired
    private AccountingEntryRepository accountingEntryRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LetteringCustomerRepository letteringCustomerRepository  ;

    @Autowired
    private DocumentRepository documentRepository  ;

    @Autowired
    private PaymentCustomerRepository paymentCustomerRepository  ;


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

        Pageable paging = PageRequest.of(page, size, Sort.by("id"));
        Company company = companyRepository.findOneById(id);
        if (company != null) {

            Page<AccountingEntry> dataStock = accountingEntryRepository.accountingEntry(company.getId(), paging);
            dataStock.getTotalElements();
            JSONObject response = new JSONObject();
            log.info(dataStock.getTotalElements() + " total ");
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

    @RequestMapping(
            value = "letter/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> letter(HttpServletRequest request, @RequestParam  String letter, @PathVariable Long id
    ) {
        Company company = companyRepository.findOneById(id);
        if (company != null && !letter.isEmpty() ) {
            List<AccountingEntry> dataStock = accountingEntryRepository.accountingEntryByLetter(company.getId(), letter);
            JSONObject response = new JSONObject();
            try {
                JSONArray jArray = new JSONArray();
                dataStock.forEach(stockCount -> jArray.put(stockCount.toJson()));
                response.put("results", jArray);
                return new ResponseEntity<>(response.toString(), HttpStatus.OK);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(
            value = "letter/attached/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> attachedLetter(HttpServletRequest request,  @PathVariable Long id
    ) {
        Customer company = customerRepository.findOneById(id);
        if (company != null ) {
            List<LetteringCustomer> dataLetteringCustomer = letteringCustomerRepository.findByCustomerAndLetterLetterCaseOrderByIdDesc(company,LetterCase.lowercase);
            JSONObject response = new JSONObject();
            try {
                JSONArray jArray = new JSONArray();
                dataLetteringCustomer.forEach(letteringCustomer -> jArray.put(letteringCustomer.toJson()));
                response.put("results", jArray);
                return new ResponseEntity<>(response.toString(), HttpStatus.OK);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/lettering", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    //@Transactional
    public ResponseEntity<?> lettering(@Valid @RequestBody LetteringCustomer letteringCustomer, HttpServletRequest request) throws Exception {


        Set<Document> documentList = new HashSet<>();
        letteringCustomer.getDocuments().forEach(document -> {
            documentList.add(documentRepository.getOne(document.getId()));
            log.error("document Repository");
        });
        letteringCustomer.setDocuments(documentList);


        Set<PaymentCustomer> paymentCustomerSet   = new HashSet<>();
        letteringCustomer.getPaymentsCustomer().forEach(paymentCustomer -> {
            paymentCustomerSet.add(paymentCustomerRepository.getOne(paymentCustomer.getId()));
            log.error("getTotalPaymentsCustomer :  "  + paymentCustomer.getId());

        });

        letteringCustomer.setPaymentsCustomer(paymentCustomerSet);
        letteringCustomer.getTotalPaymentsCustomer();


        if(letteringCustomer.isBalanced()) {
            log.error("Lettrage  "  + letteringCustomer.isBalanced() );
            LetteringCustomer letteringCustomer_db =  letteringCustomerRepository.findTopOneByCustomerAndLetterLetterCaseOrderByIdDesc(letteringCustomer.getCustomer(), LetterCase.uppercase);
            String letterCode  ;
            if (letteringCustomer_db  != null) {
                letterCode  = letteringCustomer_db.getLetter().getCode();
                letterCode =  getString(  letterCode,    "uppercase");
            }else {
                letterCode = "AAA";
            }
            Letter letter = new Letter();
            letter.setCode(letterCode);
            letter.setLetterCase(LetterCase.uppercase);
            letteringCustomer.setLetter(letter);

        }else {
            LetteringCustomer letteringCustomer_db =  letteringCustomerRepository.findTopOneByCustomerAndLetterLetterCaseOrderByIdDesc(letteringCustomer.getCustomer(), LetterCase.lowercase);
            String letterCode  ;
            if (letteringCustomer_db  != null) {
                letterCode  = letteringCustomer_db.getLetter().getCode();
                letterCode =  getString(  letterCode,    "lowercase");
            }else {
                letterCode = "aaa";
            }
            Letter letter = new Letter();
            letter.setCode(letterCode);
            letter.setLetterCase(LetterCase.lowercase);
            letteringCustomer.setLetter(letter);
        }



         log.trace(String.valueOf(letteringCustomer.toJson()));
         LetteringCustomer   savedLetteringCustomer  = letteringCustomerRepository.save(letteringCustomer);
         log.trace(String.valueOf(savedLetteringCustomer.toJson()));
        return new ResponseEntity<>("String.valueOf(savedLetteringCustomer.toJson())", HttpStatus.OK);
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
                ((int) ch[0] < minChar || (int) ch[1] < minChar || (int) ch[2] < minChar) || ((int) ch[0] >= maxChar & (int) ch[1] >= maxChar & (int) ch[2] >= maxChar)
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
