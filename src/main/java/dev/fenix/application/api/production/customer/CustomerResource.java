package dev.fenix.application.api.production.customer;


import dev.fenix.application.production.customer.model.Customer;
import dev.fenix.application.production.customer.repository.CustomerRepository;
import dev.fenix.application.production.customer.service.CustomerService;
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

@RestController()
@RequestMapping("/api/customer")
public class CustomerResource {
  private static final Logger log = LoggerFactory.getLogger(CustomerResource.class);

  @Autowired private CustomerRepository customerRepository;
  @Autowired private CustomerService customerService;

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
      @RequestParam(required = false) Long type,
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "200") Integer size,
      @RequestParam(defaultValue = "id,desc") String[] sort,
      @RequestParam(required = false) String[] query)
      throws InterruptedException {
    log.trace(
        String.format(
            "%s method accessed .", new Object() {}.getClass().getEnclosingMethod().getName()));
    JSONArray jArray = new JSONArray();
    // Iterable<Customer> customers = customerRepository.findAll();

    Iterable<Customer> customers = customerService.getAllCustomers(page, size, sort, query);

    for (Customer customer : customers) {
      jArray.put(customer.toJson());
    }
    JSONObject response = new JSONObject();
    try {
      response.put("results", jArray);
      response.put("count", jArray.length());
      response.put("total", customerService.getCountAll());

      return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
  }

  @RequestMapping(
      value = "/save",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> save(@Valid @RequestBody Customer customer, HttpServletRequest request) {

    log.trace(
        String.format(
            "%s method accessed .", new Object() {}.getClass().getEnclosingMethod().getName()));
    customer.setActive(true);
    Customer savedCustomer = customerRepository.save(customer);
    return ResponseEntity.ok(savedCustomer.toJson().toString());
  }

  @RequestMapping(
      value = "/update",
      method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> update(
      @Valid @RequestBody Customer customer, HttpServletRequest request) {
    try {
      log.trace(
          String.format(
              "%s method accessed .", new Object() {}.getClass().getEnclosingMethod().getName()));
      customer.setActive(true);
      Customer updatedCustomer = customerRepository.save(customer);
      return new ResponseEntity<>(updatedCustomer.toJson().toString(), HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>("Not updated", HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(
      value = "/get/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> get(HttpServletRequest request, @PathVariable Long id)
      throws NotFoundException {
    log.trace(
        String.format(
            "%s method accessed", new Object() {}.getClass().getEnclosingMethod().getName()));
    Customer customer =
        customerRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Product  not found"));
    return new ResponseEntity<>(customer.toJson().toString(), HttpStatus.OK);
  }

  @RequestMapping(
      value = "/delete/{id}",
      method = RequestMethod.DELETE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    log.trace(String.format("%s method accessed", new Object() {}.getClass().getEnclosingMethod().getName()));
    Customer customer = customerRepository.getOne(id);
    try {
      customer.setActive(false);
      Customer deletedCustomer = customerRepository.save(customer);
      return new ResponseEntity<>(deletedCustomer.toJson().toString(), HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>("not deleted", HttpStatus.BAD_REQUEST);
    }
  }
}
