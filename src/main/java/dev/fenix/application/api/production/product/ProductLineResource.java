package dev.fenix.application.api.production.product;

import dev.fenix.application.Application;
import dev.fenix.application.production.product.model.ProductLine;
import dev.fenix.application.production.product.model.ProductionUnit;
import dev.fenix.application.production.product.repository.ProductLineRepository;
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
@RequestMapping("/api/product/line")
public class ProductLineResource {
  private static final Logger log = LoggerFactory.getLogger(Application.class);
  @Autowired private ProductLineRepository productLineRepository;

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
    Iterable<ProductLine> productLines = productLineRepository.findAll();
    for (ProductLine productLine : productLines) {
      jArray.put(productLine.toJson());
    }
    return jArray.toString();
  }

  @RequestMapping(
      value = "/save",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> save(
      @Valid @RequestBody ProductLine productLine, HttpServletRequest request) {

    /*  if (task.getAssignedTo() == null) {
      throw new RuntimeException("AssignedTo is mandatory");
    }*/
    productLine.setActive(true);
    ProductLine saveProductLine = productLineRepository.save(productLine);
    return ResponseEntity.ok(saveProductLine.toJson().toString());
  }


  @RequestMapping(
          value = "/update",
          method = RequestMethod.PUT,
          produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> update(@Valid @RequestBody ProductLine productionUnit, HttpServletRequest request) {
    try {
      productionUnit.setActive(true);
      ProductLine updatedProductLine = productLineRepository.save(productionUnit);
      return new ResponseEntity<>(updatedProductLine.toJson().toString(), HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>("Not saved", HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(
          value = "/delete/{id}",
          method = RequestMethod.DELETE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {

    try {
      ProductLine productionUnit = productLineRepository.getOne(id);
      productionUnit.setActive(false);
      ProductLine savedProductUnit = productLineRepository.save(productionUnit);
      return ResponseEntity.ok(savedProductUnit.toJson().toString());
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>("not deleted", HttpStatus.BAD_REQUEST);
    }
  }



}
