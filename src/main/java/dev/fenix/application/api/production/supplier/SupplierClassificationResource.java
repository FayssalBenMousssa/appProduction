package dev.fenix.application.api.production.supplier;

import dev.fenix.application.production.supplier.model.SupplierClassification;
import dev.fenix.application.production.supplier.repository.SupplierClassificationRepository;
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
@RequestMapping("/api/supplier/classification")
public class SupplierClassificationResource {
  private static final Logger log = LoggerFactory.getLogger(SupplierClassificationResource.class);

  @Autowired private SupplierClassificationRepository supplierClassificationRepository;


  @RequestMapping(
      value = "/index",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public String index(HttpServletRequest request) {
    JSONArray jArray = new JSONArray();
    log.trace("{methodName} method accessed");
    Iterable<SupplierClassification> supplierClassification = supplierClassificationRepository.findByActiveTrue();
    for (SupplierClassification classification : supplierClassification) {
      jArray.put(classification.toJson());
    }
    return jArray.toString();
  }


  @RequestMapping(
          value = "/get/{id}",
          method = RequestMethod.GET,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> get(HttpServletRequest request, @PathVariable Long id) throws NotFoundException {
    log.trace("ProductResource.get method accessed");
    SupplierClassification SupplierClassification = supplierClassificationRepository.findById(id).orElseThrow(() -> new NotFoundException("Product  not found"));
    return new ResponseEntity<>(SupplierClassification.toJson().toString(), HttpStatus.OK);
  }

  @RequestMapping(
      value = "/save",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> save(
          @Valid @RequestBody SupplierClassification supplierClassification, HttpServletRequest request) {
    log.trace("{methodName} method accessed");
   supplierClassification.setActive(true);
    SupplierClassification savedSupplierClassification = supplierClassificationRepository.save(supplierClassification);
    return ResponseEntity.ok(savedSupplierClassification.toJson().toString());
  }


  @RequestMapping(
          value = "/update",
          method = RequestMethod.PUT,
          produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> update(@Valid @RequestBody SupplierClassification supplierClassification, HttpServletRequest request) {
    try {
      log.trace("{methodName} method accessed");
     supplierClassification.setActive(true);
      SupplierClassification updatedSupplierClassification = supplierClassificationRepository.save(supplierClassification);
      return new ResponseEntity<>(updatedSupplierClassification.toJson().toString(), HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>("Not updated", HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(
          value = "/delete/{id}",
          method = RequestMethod.DELETE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {

    try {
      log.trace("{methodName} method accessed");
      SupplierClassification supplierClassification = supplierClassificationRepository.getOne(id);
       supplierClassification.setActive(false);
      SupplierClassification savedSupplierClassification = supplierClassificationRepository.save(supplierClassification);
      return ResponseEntity.ok("active : " +  savedSupplierClassification.getActive());
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>("not deleted", HttpStatus.BAD_REQUEST);
    }
  }

}
