package dev.fenix.application.api.production.product;

import dev.fenix.application.Application;
import dev.fenix.application.production.product.model.Product;
import dev.fenix.application.production.product.repository.ProductRepository;
import dev.fenix.application.production.product.service.ProductService;
import javassist.NotFoundException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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
  @Autowired private ProductService productService;

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
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "50") Integer size,
      @RequestParam(defaultValue = "id,desc") String[] sort,
      @RequestParam(required = false) String[] query) {
    JSONArray jArray = new JSONArray();
    Iterable<Product> products = productService.getAllProducts(page, size, sort, query);
    for (Product product : products) {
      jArray.put(product.toJson());
    }
    return new ResponseEntity<>(jArray.toString(), HttpStatus.OK);
  }

  @RequestMapping(
      value = "/detail/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> detail(HttpServletRequest request, @PathVariable Long id)
      throws NotFoundException {

    Product product =
        productRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Product  not found"));

    return new ResponseEntity<>(product.toJson().toString(), HttpStatus.OK);
  }

  @RequestMapping(
      value = "/save",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> save(@Valid @RequestBody Product product, HttpServletRequest request) {

    try {

      Product savedProduct = productRepository.save(product);
      return ResponseEntity.ok(savedProduct.toJson().toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private Sort.Direction getSortDirection(String direction) {
    if (direction.equals("asc")) {
      return Sort.Direction.ASC;
    } else if (direction.equals("desc")) {
      return Sort.Direction.DESC;
    }
    return Sort.Direction.ASC;
  }
}
