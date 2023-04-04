package dev.fenix.application.api.production.treatment;

import dev.fenix.application.production.payment.model.Bank;
import dev.fenix.application.production.payment.repository.BankRepository;
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
@RequestMapping("/api/payment/bank")
public class BankResource {
  private static final Logger log = LoggerFactory.getLogger(BankResource.class);

  @Autowired private BankRepository bankRepository;

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
    Iterable<Bank> banks = bankRepository.findByActiveTrue();
    for (Bank bank : banks) {
      jArray.put(bank.toJson());
    }
    return jArray.toString();
  }

  @RequestMapping(
      value = "/save",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> save(@Valid @RequestBody Bank bank, HttpServletRequest request) {
    bank.setActive(true);
    Bank savedBank = bankRepository.save(bank);
    return ResponseEntity.ok(savedBank.toJson().toString());
  }

  @RequestMapping(
      value = "/get/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> get(HttpServletRequest request, @PathVariable Long id)
      throws NotFoundException {
    //log.trace("ProductResource.get method accessed");
    Bank bank =
        bankRepository.findById(id).orElseThrow(() -> new NotFoundException("Product  not found"));
    return new ResponseEntity<>(bank.toJson().toString(), HttpStatus.OK);
  }

  @RequestMapping(
      value = "/update",
      method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> update(@Valid @RequestBody Bank bank, HttpServletRequest request) {
    try {
      bank.setActive(true);
      Bank updatedBank = bankRepository.save(bank);
      return new ResponseEntity<>(updatedBank.toJson().toString(), HttpStatus.OK);
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
      Bank bank = bankRepository.getOne(id);
      bank.setActive(false);
      Bank deletedBank = bankRepository.save(bank);
      return ResponseEntity.ok(deletedBank.toJson().toString());
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}
