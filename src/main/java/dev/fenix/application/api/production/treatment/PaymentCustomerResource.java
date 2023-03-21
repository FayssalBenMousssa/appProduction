package dev.fenix.application.api.production.treatment;

import dev.fenix.application.production.customer.model.Customer;
import dev.fenix.application.production.payment.model.PaymentCustomer;
import dev.fenix.application.production.payment.model.PaymentMethod;
import dev.fenix.application.production.payment.repository.PaymentCustomerRepository;
import dev.fenix.application.production.payment.repository.PaymentMethodRepository;
import dev.fenix.application.production.payment.service.PaymentCustomerService;
import javassist.NotFoundException;
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
import java.util.concurrent.TimeUnit;

@RestController()
@RequestMapping("/api/payment/customer")
public class PaymentCustomerResource {
  private static final Logger log = LoggerFactory.getLogger(PaymentCustomerResource.class);

  @Autowired private PaymentCustomerRepository paymentCustomerRepository;
  @Autowired private PaymentCustomerService paymentCustomerService;

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
    log.trace(String.format("%s method accessed .", new Object() {}.getClass().getEnclosingMethod().getName()));
    JSONArray jArray = new JSONArray();
    // Iterable<Customer> customers = customerRepository.findAll();
    Iterable<PaymentCustomer  > paymentCustomers = paymentCustomerService.getAllPaymentCustomers(page, size, sort, query);
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
    PaymentCustomer savedPaymentCustomer = paymentCustomerRepository.save(paymentCustomer);
    return ResponseEntity.ok(savedPaymentCustomer.toJson().toString());
  }

  @RequestMapping(
      value = "/get/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> get(HttpServletRequest request, @PathVariable Long id)
      throws NotFoundException {
    log.trace("ProductResource.get method accessed");
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
}
