package dev.fenix.application.api.production.product;

import dev.fenix.application.Application;
import dev.fenix.application.production.product.model.Product;
import dev.fenix.application.production.product.repository.ProductRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController()
@RequestMapping("/api/product")
public class ProcuctResource {
  private static final Logger log = LoggerFactory.getLogger(Application.class);

  @Autowired private ProductRepository productRepository;

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
    Iterable<Product> products = productRepository.findAll();
    for (Product product : products) {
      jArray.put(product.toJson());
    }
    return jArray.toString();
  }

  @RequestMapping(
      value = "/save",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> save(@Valid @RequestBody Product product, HttpServletRequest request) {

    log.info(product.toJson().toString());


    try {


      Product savedProduct = productRepository.save(product);
      return ResponseEntity.ok(savedProduct.toJson().toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
