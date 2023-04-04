package dev.fenix.application.api.production.supplier;

import dev.fenix.application.production.supplier.model.Supplier;
import dev.fenix.application.production.supplier.repository.SupplierRepository;
import dev.fenix.application.production.supplier.service.SupplierService;
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
@RequestMapping("/api/supplier")
public class SupplierResource {
  private static final Logger log = LoggerFactory.getLogger(SupplierResource.class);

  @Autowired private SupplierRepository supplierRepository;
  @Autowired private SupplierService supplierService;

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
    //log.trace(String.format("%s method accessed .", new Object() {}.getClass().getEnclosingMethod().getName()));
    JSONArray jArray = new JSONArray();
    // Iterable<Supplier> vendors = supplierRepository.findAll();

    Iterable<Supplier> suppliers = supplierService.getAllSuppliers(page, size, sort, query);

    for (Supplier supplier : suppliers) {
      jArray.put(supplier.toJson());
    }
    JSONObject response = new JSONObject();
    try {
      response.put("results", jArray);
      response.put("count", jArray.length());
      response.put("total", supplierService.getCountAll());

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
  public ResponseEntity<?> save(@Valid @RequestBody Supplier vendor, HttpServletRequest request) {

    //log.trace(String.format("%s method accessed .", new Object() {}.getClass().getEnclosingMethod().getName()));
    vendor.setActive(true);
    Supplier savedVendor = supplierRepository.save(vendor);
    return ResponseEntity.ok(savedVendor.toJson().toString());
  }

  @RequestMapping(
      value = "/update",
      method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> update(
      @Valid @RequestBody Supplier supplier, HttpServletRequest request) {
    try {
      //log.trace(String.format("%s method accessed .", new Object() {}.getClass().getEnclosingMethod().getName()));
      supplier.setActive(true);
      Supplier updatedVendor = supplierRepository.save(supplier);
      return new ResponseEntity<>(updatedVendor.toJson().toString(), HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(
      value = "/get/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> get(HttpServletRequest request, @PathVariable Long id)
      throws NotFoundException {
    //log.trace(String.format("%s method accessed", new Object() {}.getClass().getEnclosingMethod().getName()));
    Supplier vendor =
        supplierRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Product  not found"));
    return new ResponseEntity<>(vendor.toJson().toString(), HttpStatus.OK);
  }

  @RequestMapping(
      value = "/delete/{id}",
      method = RequestMethod.DELETE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    //log.trace(String.format("%s method accessed", new Object() {}.getClass().getEnclosingMethod().getName()));
    Supplier vendor = supplierRepository.getOne(id);
    try {
      vendor.setActive(false);
      Supplier savedVendor = supplierRepository.save(vendor);

      return new ResponseEntity<>(savedVendor.toJson().toString(), HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }



}
