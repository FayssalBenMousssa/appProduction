package dev.fenix.application.api.production.product;

import dev.fenix.application.production.product.model.ProductMgmtMode;
import dev.fenix.application.production.product.repository.ProductMgmtModeRepository;
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
@RequestMapping("/api/product/mgmt_mode")
public class ProductMgmtModeResource {
  private static final Logger log = LoggerFactory.getLogger(ProductMgmtModeResource.class);

  @Autowired private ProductMgmtModeRepository productMgmtModeRepository;

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
  public String index(HttpServletRequest request) {
    JSONArray jArray = new JSONArray();
    Iterable<ProductMgmtMode> productMgmtModes = productMgmtModeRepository.findAll();
    for (ProductMgmtMode productMgmtMode : productMgmtModes) {
      jArray.put(productMgmtMode.toJson());
    }
    return jArray.toString();
  }

  @RequestMapping(
      value = "/save",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> save(@Valid @RequestBody ProductMgmtMode productMgmtMode, HttpServletRequest request) {
    ProductMgmtMode savedProductMgmtMode = productMgmtModeRepository.save(productMgmtMode);

    /*  if (task.getAssignedTo() == null) {
      throw new RuntimeException("AssignedTo is mandatory");
    }*/

    return ResponseEntity.ok(savedProductMgmtMode.toJson().toString());
  }

  @RequestMapping(
      value = "/get/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> get(HttpServletRequest request, @PathVariable Long id)
      throws NotFoundException {
    //log.trace("ProductResource.get method accessed");
    ProductMgmtMode productMgmtMode =
        productMgmtModeRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Product  not found"));
    return new ResponseEntity<>(productMgmtMode.toJson().toString(), HttpStatus.OK);
  }

  @RequestMapping(
      value = "/update",
      method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> update(@Valid @RequestBody ProductMgmtMode productMgmtMode, HttpServletRequest request) {
    try {
      ProductMgmtMode updatedProductMgmtMode = productMgmtModeRepository.save(productMgmtMode);
      return new ResponseEntity<>(updatedProductMgmtMode.toJson().toString(), HttpStatus.OK);
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
      ProductMgmtMode productMgmtMode = productMgmtModeRepository.getOne(id);
      ProductMgmtMode savedProductUnit = productMgmtModeRepository.save(productMgmtMode);
      return ResponseEntity.ok(savedProductUnit.toJson().toString());
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}
