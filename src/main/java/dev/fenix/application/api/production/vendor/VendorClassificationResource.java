package dev.fenix.application.api.production.vendor;

import dev.fenix.application.production.vendor.model.VendorClassification;
import dev.fenix.application.production.vendor.repository.VendorClassificationRepository;
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
@RequestMapping("/api/vendor/classification")
public class VendorClassificationResource {
  private static final Logger log = LoggerFactory.getLogger(VendorClassificationResource.class);

  @Autowired private VendorClassificationRepository vendorClassificationRepository;


  @RequestMapping(
      value = "/index",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public String index(HttpServletRequest request) {
    JSONArray jArray = new JSONArray();
    log.trace("VendorClassificationResource.index  method accessed");
    Iterable<VendorClassification> vendorClassification = vendorClassificationRepository.findByActiveTrue();
    for (VendorClassification classification : vendorClassification) {
      jArray.put(classification.toJson());
    }
    return jArray.toString();
  }


  @RequestMapping(
      value = "/save",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> save(
      @Valid @RequestBody VendorClassification vendorClassification, HttpServletRequest request) {
    log.trace("VendorClassificationResource.save method accessed");
   vendorClassification.setActive(true);
    VendorClassification savedVendorClassification = vendorClassificationRepository.save(vendorClassification);
    return ResponseEntity.ok(savedVendorClassification.toJson().toString());
  }


  @RequestMapping(
          value = "/update",
          method = RequestMethod.PUT,
          produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> update(@Valid @RequestBody VendorClassification vendorClassification, HttpServletRequest request) {
    try {
      log.trace("VendorClassificationResource.update method accessed");
     vendorClassification.setActive(true);
      VendorClassification updatedVendorClassification = vendorClassificationRepository.save(vendorClassification);
      return new ResponseEntity<>(updatedVendorClassification.toJson().toString(), HttpStatus.OK);
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
      log.trace("VendorClassificationResource.delete method accessed");
      VendorClassification vendorClassification = vendorClassificationRepository.getOne(id);
       vendorClassification.setActive(false);
      VendorClassification savedVendorClassification = vendorClassificationRepository.save(vendorClassification);
      return ResponseEntity.ok("active : " +  savedVendorClassification.getActive());
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>("not deleted", HttpStatus.BAD_REQUEST);
    }
  }

}
