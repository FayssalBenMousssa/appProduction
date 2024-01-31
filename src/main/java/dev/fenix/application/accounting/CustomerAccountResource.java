package dev.fenix.application.accounting;

import dev.fenix.application.accounting.model.AccountingEntry;
import dev.fenix.application.accounting.model.Letter;
import dev.fenix.application.accounting.model.LetterCase;
import dev.fenix.application.accounting.model.LetteringCustomer;
import dev.fenix.application.accounting.repository.AccountingEntryRepository;
import dev.fenix.application.accounting.repository.LetteringCustomerRepository;
import dev.fenix.application.accounting.service.AccountingEntryService;
import dev.fenix.application.business.model.Company;
import dev.fenix.application.business.repository.CompanyRepository;
import dev.fenix.application.production.customer.model.Customer;
import dev.fenix.application.production.customer.repository.CustomerRepository;
import dev.fenix.application.production.payment.model.PaymentCustomer;
import dev.fenix.application.production.payment.repository.PaymentCustomerRepository;
import dev.fenix.application.production.treatment.model.Document;
import dev.fenix.application.production.treatment.model.Status;
import dev.fenix.application.production.treatment.repository.DocumentRepository;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
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
    private LetteringCustomerRepository letteringCustomerRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private PaymentCustomerRepository paymentCustomerRepository;

    @Autowired
    private AccountingEntryService accountingEntryService;


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> index(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "200") Integer size, @RequestParam(defaultValue = "id,desc") String[] sort, @RequestParam(required = false) String[] query, @PathVariable Long id) {
        List<AccountingEntry> data = accountingEntryService.getAllAccountingEntry(id, page, size, sort, query);
        JSONObject response = new JSONObject();
        try {
            JSONArray jArray = new JSONArray();
            data.forEach(stockCount -> jArray.put(stockCount.toJson()));
            response.put("results", jArray);
            response.put("count", jArray.length());
            response.put("total", accountingEntryService.getCount());
            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /* summary/{id} */

    @RequestMapping(
            value = "/summary/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> Summary(HttpServletRequest request, @RequestParam Date endDate, @RequestParam Date startDate, @PathVariable Long id) throws JSONException {
        Company company = companyRepository.findOneById(id);
        if (company != null) {
            Double ranDocumentDebit = accountingEntryRepository.sumRanDocDebit(company, startDate);
            Double ranPaymentCredit = accountingEntryRepository.sumRanPayment(company, startDate);

            Double periodicBalanceDocumentDebit = accountingEntryRepository.periodicBalanceDocumentDebit(company, startDate ,endDate);
            Double periodicBalancePaymentCredit = accountingEntryRepository.periodicBalancePaymentCredit(company, startDate ,endDate);

            List<Status> workFlow = List.of(Status.APPROVED);
            Double saleNotCashed = accountingEntryRepository.saleNotCashed(company, workFlow);

            JSONObject response = new JSONObject();

            response.put("ranDocumentDebit", ranDocumentDebit != null ? ranDocumentDebit : 0 );
            response.put("ranPaymentCredit", ranPaymentCredit != null ? ranPaymentCredit : 0 );
            response.put("periodicBalanceDocumentDebit", periodicBalanceDocumentDebit != null ? periodicBalanceDocumentDebit : 0 );
            response.put("periodicBalancePaymentCredit", periodicBalancePaymentCredit != null ? periodicBalancePaymentCredit : 0 );
            response.put("saleNotCashed", saleNotCashed != null ? saleNotCashed : 0);

            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        }


        return new ResponseEntity<>("BAD_REQUEST", HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(
            value = "letter/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> letter(HttpServletRequest request, @RequestParam String letter, @PathVariable Long id
    ) {
        Company company = companyRepository.findOneById(id);
        if (company != null && !letter.isEmpty()) {
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
    public ResponseEntity<String> attachedLetter(HttpServletRequest request, @PathVariable Long id
    ) {
        Customer company = customerRepository.findOneById(id);
        if (company != null) {
            List<LetteringCustomer> dataLetteringCustomer = letteringCustomerRepository.findByCustomerAndLetterLetterCaseOrderByIdDesc(company, LetterCase.lowercase);
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


        /// transform from preLettering to Lettering
        if (letteringCustomer.isTransform()) {
            //log.info("transform from preLettering to Lettering ");
            LetteringCustomer transformLetteringCustomer;
            transformLetteringCustomer = letteringCustomer;
            letteringCustomerRepository.deleteById(letteringCustomer.getId());
            transformLetteringCustomer.setLetter(null);
            transformLetteringCustomer.setId(null);
            letteringCustomer = transformLetteringCustomer;


            //log.info(letteringCustomer.toJson().toString());

        }

        /// attach preLettering
        if (letteringCustomer.getLetter() != null) {
            //log.info("attach preLettering   ");
            LetteringCustomer newLetteringCustomer = letteringCustomerRepository.getOne(letteringCustomer.getId());
            for (Document document : newLetteringCustomer.getDocuments()) {
                letteringCustomer.getDocuments().add(document);
            }
            for (PaymentCustomer paymentCustomer : newLetteringCustomer.getPaymentsCustomer()) {
                letteringCustomer.getPaymentsCustomer().add(paymentCustomer);
            }
            letteringCustomerRepository.save(letteringCustomer);
            return new ResponseEntity<>(letteringCustomer.toJson().toString(), HttpStatus.OK);
        }


        Set<Document> documentList = new HashSet<>();
        letteringCustomer.getDocuments().forEach(document -> {
            documentList.add(documentRepository.getOne(document.getId()));
        });
        letteringCustomer.setDocuments(documentList);

        Set<PaymentCustomer> paymentCustomerSet = new HashSet<>();
        letteringCustomer.getPaymentsCustomer().forEach(paymentCustomer -> {
            paymentCustomerSet.add(paymentCustomerRepository.getOne(paymentCustomer.getId()));
        });
        letteringCustomer.setPaymentsCustomer(paymentCustomerSet);
        letteringCustomer.getTotalPaymentsCustomer();

        /// Lettering ...
        if (letteringCustomer.isBalanced()) {
            //log.info("Lettering ...   ");
            LetteringCustomer letteringCustomer_db = letteringCustomerRepository.findTopOneByCustomerAndLetterLetterCaseOrderByIdDesc(letteringCustomer.getCustomer(), LetterCase.uppercase);
            String letterCode;
            if (letteringCustomer_db != null) {
                letterCode = letteringCustomer_db.getLetter().getCode();
                letterCode = getString(letterCode, "uppercase");
            } else {
                letterCode = "AAA";
            }
            Letter letter = new Letter();
            letter.setCode(letterCode);
            letter.setLetterCase(LetterCase.uppercase);
            letteringCustomer.setLetter(letter);

        }
        /// preLettering ...
        else {
            //log.info("preLettering ...   ");
            LetteringCustomer letteringCustomer_db = letteringCustomerRepository.findTopOneByCustomerAndLetterLetterCaseOrderByIdDesc(letteringCustomer.getCustomer(), LetterCase.lowercase);
            String letterCode;
            if (letteringCustomer_db != null) {
                letterCode = letteringCustomer_db.getLetter().getCode();
                letterCode = getString(letterCode, "lowercase");
            } else {
                letterCode = "aaa";
            }
            Letter letter = new Letter();
            letter.setCode(letterCode);
            letter.setLetterCase(LetterCase.lowercase);
            letteringCustomer.setLetter(letter);
        }
        LetteringCustomer savedLetteringCustomer = letteringCustomerRepository.save(letteringCustomer);
        return new ResponseEntity<>(savedLetteringCustomer.toJson().toString(), HttpStatus.OK);
    }


    private static String CharLettering(String letter, String letterCase) throws Exception {
        return getString(letter, letterCase);
    }

    @NotNull
    public static String getString(String letter, String letterCase) throws Exception {
        char[] ch = letter.trim().toCharArray();
        int minChar = letterCase.equals("uppercase") ? 65 : 97;
        int maxChar = letterCase.equals("uppercase") ? 90 : 122;
        if (
                ((int) ch[0] < minChar || (int) ch[1] < minChar || (int) ch[2] < minChar) || ((int) ch[0] >= maxChar & (int) ch[1] >= maxChar & (int) ch[2] >= maxChar)
        ) {
            throw new Exception(letter + "outside the allowed range !");
        }
        if ((int) ch[2] < maxChar) {
            return ("" + ch[0] + ch[1] + (char) (ch[2] + 1));
        } else if ((int) ch[1] < maxChar) {
            return ("" + ch[0] + (char) (ch[1] + 1) + (letterCase.equals("uppercase") ? "A" : "a"));
        } else {
            return ("" + (char) (ch[0] + 1) + (letterCase.equals("uppercase") ? "A" : "a") + (letterCase.equals("uppercase") ? "A" : "a"));
        }
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            LetteringCustomer letteringCustomer = letteringCustomerRepository.getOne(id);
            letteringCustomerRepository.delete(letteringCustomer);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(
            value = "/delete/lettering/document/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteLetteringDocument(@PathVariable("id") Long id, @RequestBody Document document) {
        if (document != null) {
            document = documentRepository.getOne(document.getId());
            document.setLetteringCustomer(null);
            documentRepository.save(document);
            return ResponseEntity.ok(true);
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);

    }

    @RequestMapping(
            value = "/delete/lettering/payment_customer/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteLinePaymentCustomer(@PathVariable("id") Long id, @RequestBody PaymentCustomer paymentCustomer) {
        if (paymentCustomer != null) {
            paymentCustomer = paymentCustomerRepository.getOne(paymentCustomer.getId());
            paymentCustomer.setLetteringCustomer(null);
            paymentCustomerRepository.save(paymentCustomer);
            return ResponseEntity.ok(true);
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }
}
