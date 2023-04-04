package dev.fenix.application.api.production.product;

import dev.fenix.application.production.product.model.CategoryPrice;
import dev.fenix.application.production.product.repository.CategoryPriceRepository;
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
@RequestMapping("/api/product/price/category")
public class CategoryPriceResource {
  private static final Logger log = LoggerFactory.getLogger(CategoryPriceResource.class);

  @Autowired private CategoryPriceRepository categoryPriceRepository;

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
    Iterable<CategoryPrice> categoryPrices = categoryPriceRepository.findByActiveTrue();
    for (CategoryPrice categoryPrice : categoryPrices) {
      jArray.put(categoryPrice.toJson());
    }
    return jArray.toString();
  }

  @RequestMapping(
      value = "/save",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> save(@Valid @RequestBody CategoryPrice categoryPrice, HttpServletRequest request) {
    categoryPrice.setActive(true);
    CategoryPrice savedCategoryPrice = categoryPriceRepository.save(categoryPrice);

    /*  if (task.getAssignedTo() == null) {
      throw new RuntimeException("AssignedTo is mandatory");
    }*/

    return ResponseEntity.ok(savedCategoryPrice.toJson().toString());
  }

  @RequestMapping(
      value = "/get/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> get(HttpServletRequest request, @PathVariable Long id)
      throws NotFoundException {
    //log.trace("ProductResource.get method accessed");
    CategoryPrice categoryPrice =
        categoryPriceRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Product  not found"));
    return new ResponseEntity<>(categoryPrice.toJson().toString(), HttpStatus.OK);
  }

  @RequestMapping(
      value = "/update",
      method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> update(@Valid @RequestBody CategoryPrice categoryPrice, HttpServletRequest request) {
    try {
      categoryPrice.setActive(true);
      CategoryPrice updatedCategoryPrice = categoryPriceRepository.save(categoryPrice);
      return new ResponseEntity<>(updatedCategoryPrice.toJson().toString(), HttpStatus.OK);
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
      CategoryPrice categoryPrice = categoryPriceRepository.getOne(id);
      categoryPrice.setActive(false);
      CategoryPrice savedProductUnit = categoryPriceRepository.save(categoryPrice);
      return ResponseEntity.ok(savedProductUnit.toJson().toString());
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}
