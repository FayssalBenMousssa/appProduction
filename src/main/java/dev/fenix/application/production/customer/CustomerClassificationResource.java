package dev.fenix.application.production.customer;

import dev.fenix.application.production.customer.model.CustomerClassification;
import dev.fenix.application.production.customer.repository.CustomerClassificationRepository;
import javassist.NotFoundException;
import org.json.JSONArray;
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
@RequestMapping("/api/customer/classification")
public class CustomerClassificationResource {
  private static final Logger log = LoggerFactory.getLogger(CustomerClassificationResource.class);

  @Autowired private CustomerClassificationRepository customerClassificationRepository;

  @RequestMapping(
      value = "/index",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public String index(HttpServletRequest request) {
    JSONArray jArray = new JSONArray();
    // //log.trace("{methodName} method accessed");
    Iterable<CustomerClassification> customerClassification =
        customerClassificationRepository.findByActiveTrue();
    for (CustomerClassification classification : customerClassification) {
      jArray.put(classification.toJson());
    }
    return jArray.toString();
  }

  @RequestMapping(
      value = "/get/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> get(HttpServletRequest request, @PathVariable Long id)
      throws NotFoundException {
    //log.trace("ProductResource.get method accessed");
    CustomerClassification CustomerClassification =
        customerClassificationRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Product  not found"));
    return new ResponseEntity<>(CustomerClassification.toJson().toString(), HttpStatus.OK);
  }

  @RequestMapping(
      value = "/save",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> save(
      @Valid @RequestBody CustomerClassification customerClassification,
      HttpServletRequest request) {
    // //log.trace("{methodName} method accessed");
    customerClassification.setActive(true);
    CustomerClassification savedCustomerClassification =
        customerClassificationRepository.save(customerClassification);
    return ResponseEntity.ok(savedCustomerClassification.toJson().toString());
  }

  @RequestMapping(
      value = "/update",
      method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> update(
      @Valid @RequestBody CustomerClassification customerClassification,
      HttpServletRequest request) {
    try {
      // //log.trace("{methodName} method accessed");
      customerClassification.setActive(true);
      CustomerClassification updatedCustomerClassification =
          customerClassificationRepository.save(customerClassification);
      return new ResponseEntity<>(updatedCustomerClassification.toJson().toString(), HttpStatus.OK);
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
      // //log.trace("{methodName} method accessed");
      CustomerClassification customerClassification = customerClassificationRepository.getOne(id);
      customerClassification.setActive(false);
      CustomerClassification savedCustomerClassification =
          customerClassificationRepository.save(customerClassification);
      return ResponseEntity.ok("active : " + savedCustomerClassification.getActive());
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}
