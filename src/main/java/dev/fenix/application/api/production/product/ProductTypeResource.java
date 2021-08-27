package dev.fenix.application.api.production.product;

import dev.fenix.application.Application;
import dev.fenix.application.production.product.model.ProductLine;
import dev.fenix.application.production.product.model.ProductType;
import dev.fenix.application.production.product.repository.ProductTypeRepository;
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
@RequestMapping("/api/product/type")
public class ProductTypeResource {
  private static final Logger log = LoggerFactory.getLogger(Application.class);
  @Autowired private ProductTypeRepository productTypeRepository;

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
    Iterable<ProductType> productTypes = productTypeRepository.findAll();
    for (ProductType productType : productTypes) {
      jArray.put(productType.toJson());
    }
    return jArray.toString();
  }

  @RequestMapping(
      value = "/save",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> save(
      @Valid @RequestBody ProductType productType, HttpServletRequest request) {
    ProductType savedType = productTypeRepository.save(productType);

    /*  if (task.getAssignedTo() == null) {
      throw new RuntimeException("AssignedTo is mandatory");
    }*/

    return ResponseEntity.ok(savedType.toJson().toString());
  }


  @RequestMapping(
          value = "/update",
          method = RequestMethod.PUT,
          produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> update(@Valid @RequestBody ProductType productType, HttpServletRequest request) {
    try {
      productType.setActive(true);
      ProductType updatedProductType = productTypeRepository.save(productType);
      return new ResponseEntity<>(updatedProductType.toJson().toString(), HttpStatus.OK);
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
      ProductType productType = productTypeRepository.getOne(id);
      productType.setActive(false);
      ProductType savedProductType = productTypeRepository.save(productType);
      return ResponseEntity.ok(savedProductType.toJson().toString());
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>("not deleted", HttpStatus.BAD_REQUEST);
    }
  }



}
