package dev.fenix.application.api.production.treatment;

import dev.fenix.application.production.logistic.model.Depot;
import dev.fenix.application.production.logistic.repository.DepotRepository;
import dev.fenix.application.production.payment.model.PaymentMethod;
import dev.fenix.application.production.payment.repository.PaymentMethodRepository;
import dev.fenix.application.production.treatment.model.Category;
import javassist.NotFoundException;
import org.json.JSONArray;
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
@RequestMapping("/api/payment/method")
public class PaymentMethodResource {
  private static final Logger log = LoggerFactory.getLogger(PaymentMethodResource.class);

  @Autowired private PaymentMethodRepository paymentMethodRepository;

  @RequestMapping(
      value = {"/", ""},
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public String index() {
    return JSONObject.quote("Api :" + this.getClass().getSimpleName());
  }

  @RequestMapping(value = "/index", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public String index(HttpServletRequest request) {
    JSONArray jArray = new JSONArray();
    Iterable<PaymentMethod> paymentMethods = paymentMethodRepository.findByActiveTrue();
    for (PaymentMethod paymentMethod : paymentMethods) {
      jArray.put(paymentMethod.toJson());
    }
    return jArray.toString();
  }

  @RequestMapping(
      value = "/save",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> save(@Valid @RequestBody PaymentMethod paymentMethod, HttpServletRequest request) {
    paymentMethod.setActive(true);
    PaymentMethod savedPaymentMethod = paymentMethodRepository.save(paymentMethod);
    return ResponseEntity.ok(savedPaymentMethod.toJson().toString());
  }

  @RequestMapping(
      value = "/get/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> get(HttpServletRequest request, @PathVariable Long id)
      throws NotFoundException {
    log.trace("ProductResource.get method accessed");
    PaymentMethod paymentMethod =
        paymentMethodRepository.findById(id).orElseThrow(() -> new NotFoundException("Product  not found"));
    return new ResponseEntity<>(paymentMethod.toJson().toString(), HttpStatus.OK);
  }

  @RequestMapping(
      value = "/update",
      method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> update(@Valid @RequestBody PaymentMethod paymentMethod, HttpServletRequest request) {
    try {
      paymentMethod.setActive(true);
      PaymentMethod updatedPaymentMethod = paymentMethodRepository.save(paymentMethod);
      return new ResponseEntity<>(updatedPaymentMethod.toJson().toString(), HttpStatus.OK);
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
      PaymentMethod paymentMethod = paymentMethodRepository.getOne(id);
      paymentMethod.setActive(false);
      PaymentMethod deletedPaymentMethod = paymentMethodRepository.save(paymentMethod);
      return ResponseEntity.ok(deletedPaymentMethod.toJson().toString());
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}
