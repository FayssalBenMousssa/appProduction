package dev.fenix.application.production.treatment;

import dev.fenix.application.production.payment.model.PaymentCustomer;
import dev.fenix.application.production.payment.repository.PaymentCustomerRepository;
import dev.fenix.application.production.payment.service.PaymentCustomerService;
import dev.fenix.application.production.treatment.model.Document;
import dev.fenix.application.production.treatment.repository.DocumentRepository;
import dev.fenix.application.security.model.User;
import dev.fenix.application.security.repository.UserRepository;
import javassist.NotFoundException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/api/payment/customer")
public class PaymentCustomerResource {
    private static final Logger log = LoggerFactory.getLogger(PaymentCustomerResource.class);

    private final PaymentCustomerRepository paymentCustomerRepository;
    private final PaymentCustomerService paymentCustomerService;

    private final DocumentRepository documentRepository;
    private final UserRepository userRepository;

    public PaymentCustomerResource(PaymentCustomerRepository paymentCustomerRepository, PaymentCustomerService paymentCustomerService, DocumentRepository documentRepository, UserRepository userRepository) {
        this.paymentCustomerRepository = paymentCustomerRepository;
        this.paymentCustomerService = paymentCustomerService;
        this.documentRepository = documentRepository;

        this.userRepository = userRepository;
    }

    @RequestMapping(
            value = {"/", ""},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String index() {
        return JSONObject.quote("Api :" + this.getClass().getSimpleName());
    }


    @RequestMapping(
            value = "/index",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> index(
            HttpServletRequest request,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "200") Integer size,
            @RequestParam(defaultValue = "id,desc") String[] sort,
            @RequestParam(required = false) String[] query)
            throws InterruptedException {
        //log.trace(String.format("%s method accessed .", new Object() {}.getClass().getEnclosingMethod().getName()));
        JSONArray jArray = new JSONArray();
        // Iterable<Customer> customers = customerRepository.findAll();
        Iterable<PaymentCustomer> paymentCustomers = paymentCustomerService.getAllPaymentCustomers(page, size, sort, query);
        for (PaymentCustomer paymentCustomer : paymentCustomers) {
            jArray.put(paymentCustomer.toJson());
        }
        JSONObject response = new JSONObject();
        try {
            response.put("results", jArray);
            response.put("count", jArray.length());
            response.put("total", paymentCustomerService.getCount());

            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(
            value = "/save",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> save(@Valid @RequestBody PaymentCustomer paymentCustomer, HttpServletRequest request) {
        paymentCustomer.setActive(true);


        List<PaymentCustomer> dbPaymentCustomer =  paymentCustomerRepository.findByCodeLike(paymentCustomer.getCode());

        if (!dbPaymentCustomer.isEmpty()) {
            log.warn("PaymentCustomer "+paymentCustomer.getCode()+" is already saved");
            return ResponseEntity.ok("PaymentCustomer "+paymentCustomer.getCode()+" is already saved");
        }

        PaymentCustomer savedPaymentCustomer = paymentCustomerRepository.save(paymentCustomer);
        String username = this.getCurrentUser().getUserName();
        log.info("Payment received  from user: {} with code {}", username ,savedPaymentCustomer.getCode());
        try {
            updateDocumentIsSynchronised(savedPaymentCustomer);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        log.info("Payment saved successfully with ID: {}", savedPaymentCustomer.getId());
        return ResponseEntity.ok(savedPaymentCustomer.toJson().toString());
    }

    public String extractCodeWithoutPrefix(String code) {
        String codeWithoutPrefix = null;
        if (code.length() == 22) {
            codeWithoutPrefix = code.substring(4, code.length() - 2);
        } else if (code.length() == 23) {
            codeWithoutPrefix = code.substring(4, code.length() - 3);
        } else if (code.length() == 20) {
            codeWithoutPrefix = code.replace("Reg.", "");
        } else {
            return null;
        }
        return codeWithoutPrefix;
    }

    public void updateDocumentIsSynchronised(PaymentCustomer paymentCustomer) {
       // log.info("updateDocumentIsSynchronised method called with paymentCustomer code " + paymentCustomer.getCode());
        String codeWithoutPrefix = this.extractCodeWithoutPrefix(paymentCustomer.getCode());
        if (codeWithoutPrefix != null) {
            List<Document> documents = documentRepository.findByCodeLikeIgnoreCaseAndActiveTrue(codeWithoutPrefix);
            if (!documents.isEmpty()) {
                Document document = documents.get(0);
                if (document != null) {
                    // log.info("Found document with ID " + document.getId() + " and code " + document.getCode());
                    if (document.getIsSynchronised()) {
                        // log.info("Updating document with ID " + document.getId() + " and code " + document.getCode() + " to isSynchronised = false");
                        document.setIsSynchronised(false);
                        documentRepository.save(document);
                        log.info("Document with ID " + document.getId() + " and code " + document.getCode() + " updated to isSynchronised = false");
                    } else {
                         log.warn("Document with ID " + document.getId() + " and code " + document.getCode() + " already has isSynchronised = false");
                    }
                } else {
                     log.warn("No document found with code " + codeWithoutPrefix);
                }
            }else {
                 log.warn("The documents list is empty.");
            }
        }else {
            log.warn("The documents list is empty.");
        }

    }


    @RequestMapping(
            value = "/get/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> get(HttpServletRequest request, @PathVariable Long id)
            throws NotFoundException {
        //log.trace("ProductResource.get method accessed");
        PaymentCustomer paymentCustomer =
                paymentCustomerRepository.findById(id).orElseThrow(() -> new NotFoundException("Product  not found"));
        return new ResponseEntity<>(paymentCustomer.toJson().toString(), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/update",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> update(@Valid @RequestBody PaymentCustomer paymentCustomer, HttpServletRequest request) {
        try {
            paymentCustomer.setActive(true);
            PaymentCustomer updatedPaymentCustomer = paymentCustomerRepository.save(paymentCustomer);
            return new ResponseEntity<>(updatedPaymentCustomer.toJson().toString(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(
            value = "/delete/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        try {
            PaymentCustomer paymentCustomer = paymentCustomerRepository.getOne(id);
            paymentCustomer.setActive(false);
            PaymentCustomer deletedPaymentCustomer = paymentCustomerRepository.save(paymentCustomer);
            return ResponseEntity.ok(deletedPaymentCustomer.toJson().toString());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    private User getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        User user = userRepository.findOneByUserName(username);
        return user;
    }
}
