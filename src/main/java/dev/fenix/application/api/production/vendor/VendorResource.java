package dev.fenix.application.api.production.vendor;

import dev.fenix.application.production.vendor.model.Address;
import dev.fenix.application.production.vendor.model.Vendor;
import dev.fenix.application.production.vendor.repository.VendorRepository;
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
@RequestMapping("/api/vendor")
public class VendorResource {
  private static final Logger log = LoggerFactory.getLogger(VendorResource.class);

  @Autowired private VendorRepository vendorRepository;

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
    JSONArray jArray = new JSONArray();
    Iterable<Vendor> vendors = vendorRepository.findAll();
    for (Vendor vendor : vendors) {
      jArray.put(vendor.toJson());
    }
    JSONObject response = new JSONObject();
    try {
      response.put("results", jArray);
      response.put("count", vendorRepository.count());
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
  public ResponseEntity<?> save(
          @Valid @RequestBody Vendor vendor, HttpServletRequest request) {
    log.trace("{methodName} method accessed");
    vendor.setActive(true);
    Vendor savedVendor = vendorRepository.save(vendor);
    return ResponseEntity.ok(savedVendor.toJson().toString());
  }

}
