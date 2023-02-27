package dev.fenix.application.api.production.treatment;

import dev.fenix.application.production.payment.model.PaymentStatus;
import dev.fenix.application.production.payment.repository.PaymentStatusRepository;
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
@RequestMapping("/api/payment/status")
public class PaymentStatusResource {
  private static final Logger log = LoggerFactory.getLogger(PaymentStatusResource.class);

  @Autowired private PaymentStatusRepository paymentStatusRepository;

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
    Iterable<PaymentStatus> paymentsStatus = paymentStatusRepository.findByActiveTrue();
    for (PaymentStatus paymentStatus : paymentsStatus) {
      jArray.put(paymentStatus.toJson());
    }
    return jArray.toString();
  }

  @RequestMapping(
      value = "/save",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> save(@Valid @RequestBody PaymentStatus paymentStatus, HttpServletRequest request) {
    paymentStatus.setActive(true);
    PaymentStatus savedPaymentStatus = paymentStatusRepository.save(paymentStatus);
    return ResponseEntity.ok(savedPaymentStatus.toJson().toString());
  }

  @RequestMapping(
      value = "/get/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> get(HttpServletRequest request, @PathVariable Long id)
      throws NotFoundException {
    log.trace("ProductResource.get method accessed");
    PaymentStatus paymentStatus =
            paymentStatusRepository.findById(id).orElseThrow(() -> new NotFoundException("Product  not found"));
    return new ResponseEntity<>(paymentStatus.toJson().toString(), HttpStatus.OK);
  }

  @RequestMapping(
      value = "/update",
      method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> update(@Valid @RequestBody PaymentStatus paymentStatus, HttpServletRequest request) {
    try {
      paymentStatus.setActive(true);
      PaymentStatus updatedPaymentStatus = paymentStatusRepository.save(paymentStatus);
      return new ResponseEntity<>(updatedPaymentStatus.toJson().toString(), HttpStatus.OK);
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
      PaymentStatus paymentStatus = paymentStatusRepository.getOne(id);
      paymentStatus.setActive(false);
      PaymentStatus deletedPaymentStatus = paymentStatusRepository.save(paymentStatus);
      return ResponseEntity.ok(deletedPaymentStatus.toJson().toString());
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}
