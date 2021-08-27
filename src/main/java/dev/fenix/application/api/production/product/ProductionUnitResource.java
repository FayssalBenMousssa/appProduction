package dev.fenix.application.api.production.product;

import dev.fenix.application.Application;
import dev.fenix.application.production.product.model.Product;
import dev.fenix.application.production.product.model.ProductionUnit;
import dev.fenix.application.production.product.repository.ProductionUnitRepository;
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
@RequestMapping("/api/product/unit")
public class ProductionUnitResource {
  private static final Logger log = LoggerFactory.getLogger(Application.class);

  @Autowired private ProductionUnitRepository productionUnitRepository;

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
    Iterable<ProductionUnit> productionUnits = productionUnitRepository.findAll();
    for (ProductionUnit productionUnit : productionUnits) {
      jArray.put(productionUnit.toJson());
    }
    return jArray.toString();
  }

  @RequestMapping(
      value = "/save",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> save(
      @Valid @RequestBody ProductionUnit productionUnit, HttpServletRequest request) {
    ProductionUnit savedProductionUnit = productionUnitRepository.save(productionUnit);

    /*  if (task.getAssignedTo() == null) {
      throw new RuntimeException("AssignedTo is mandatory");
    }*/

    return ResponseEntity.ok(savedProductionUnit.toJson().toString());
  }

  @RequestMapping(
          value = "/update",
          method = RequestMethod.PUT,
          produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> update(@Valid @RequestBody ProductionUnit productionUnit, HttpServletRequest request) {
    try {
      productionUnit.setActive(true);
      ProductionUnit updatedProductionUnit = productionUnitRepository.save(productionUnit);
      return new ResponseEntity<>(updatedProductionUnit.toJson().toString(), HttpStatus.OK);
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
      ProductionUnit productionUnit = productionUnitRepository.getOne(id);
      productionUnit.setActive(false);
      ProductionUnit savedProductUnit = productionUnitRepository.save(productionUnit);
      return ResponseEntity.ok(savedProductUnit.toJson().toString());
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>("not deleted", HttpStatus.BAD_REQUEST);
    }
  }


}
