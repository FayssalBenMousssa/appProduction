package dev.fenix.application.api.production.product;

import dev.fenix.application.Application;
import dev.fenix.application.production.product.model.Product;
import dev.fenix.application.production.product.repository.ProductRepository;
import dev.fenix.application.production.product.repository.ProductTypeRepository;
import dev.fenix.application.production.product.service.ProductService;
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
import java.util.concurrent.TimeUnit;

@RestController()
@RequestMapping("/api/product")
public class ProcuctResource {
  private static final Logger log = LoggerFactory.getLogger(Application.class);

  @Autowired private ProductRepository productRepository;
  @Autowired private ProductTypeRepository productTypeRepository;

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
      @RequestParam (required = false)  Long type,
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "200") Integer size,
      @RequestParam(defaultValue = "id,desc") String[] sort,
      @RequestParam(required = false) String[] query) throws InterruptedException {

    // TimeUnit.SECONDS.sleep(2);

    JSONArray jArray = new JSONArray();
    Iterable<Product> products = productService.getAllProducts(page, size, sort, query ,type );
    for (Product product : products) {
      jArray.put(product.toJson());
    }
    //return new ResponseEntity<>(jArray.toString(), HttpStatus.OK);

    JSONObject response = new JSONObject();

    try {

      response.put("results" ,jArray);

      response.put("count" , productRepository.countByActiveTrueAndProductType(productTypeRepository.findOneById(type)));

      //  response.put("count" , productRepository.count());
      return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    } catch (JSONException e) {
      e.printStackTrace();

    }
    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

   // return JSONObject.quote("Api :" + this.getClass().getSimpleName());
  }

  @RequestMapping(
      value = "/get/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> get(HttpServletRequest request, @PathVariable Long id)
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
      product.setActive(true);
      Product savedProduct = productRepository.save(product);



    //  if you want to get all data
   //   savedProduct =  productRepository.findOneById(savedProduct.getId());



      return new ResponseEntity<>(savedProduct.toJson().toString(), HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>("Not saved", HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(
      value = "/update",
      method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> update(@Valid @RequestBody Product product, HttpServletRequest request) {
    try {
      product.setActive(true);
      Product updatedProduct = productRepository.save(product);
      return new ResponseEntity<>(updatedProduct.toJson().toString(), HttpStatus.OK);
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
    Product product = productRepository.getOne(id);
    try {
      product.setActive(false);
      Product savedProduct = productRepository.save(product);
      return ResponseEntity.ok(savedProduct.toJson().toString());
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>("not deleted", HttpStatus.BAD_REQUEST);
    }
  }
}
