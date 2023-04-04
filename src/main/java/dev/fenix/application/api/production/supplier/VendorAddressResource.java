package dev.fenix.application.api.production.supplier;

import dev.fenix.application.core.model.Address;
import dev.fenix.application.core.repository.AddressRepository;
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
@RequestMapping("/api/vendor/address")
public class VendorAddressResource {
  private static final Logger log = LoggerFactory.getLogger(VendorAddressResource.class);

  @Autowired private AddressRepository vendorAddressRepository;

  @RequestMapping(
      value = "/index",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public String index(HttpServletRequest request) {
    JSONArray jArray = new JSONArray();
    //log.trace("{methodName}  method accessed");
    Iterable<Address> addresses = vendorAddressRepository.findByActiveTrue();

    for (Address classification : addresses) {
      jArray.put(classification.toJson());
    }

    return jArray.toString();
  }

  @RequestMapping(
      value = "/save",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> save(@Valid @RequestBody Address address, HttpServletRequest request) {
    // //log.trace("{methodName} method accessed");
    address.setActive(true);
    Address savedAddress = vendorAddressRepository.save(address);
    return ResponseEntity.ok(savedAddress.toJson().toString());
  }

  @RequestMapping(
      value = "/update",
      method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> update(@Valid @RequestBody Address address, HttpServletRequest request) {
    try {
      // //log.trace("{methodName} method accessed");
      address.setActive(true);
      Address updatedAddress = vendorAddressRepository.save(address);
      return new ResponseEntity<>(updatedAddress.toJson().toString(), HttpStatus.OK);
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
      Address address = vendorAddressRepository.getOne(id);
      address.setActive(false);
      Address deletedAddress = vendorAddressRepository.save(address);

      return ResponseEntity.ok("active : " + deletedAddress.isActive());
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}
