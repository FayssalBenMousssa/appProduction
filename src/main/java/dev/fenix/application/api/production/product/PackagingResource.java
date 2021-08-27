package dev.fenix.application.api.production.product;

import dev.fenix.application.Application;
import dev.fenix.application.production.product.model.Packaging;
import dev.fenix.application.production.product.model.Product;
import dev.fenix.application.production.product.repository.PackagingRepository;
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
@RequestMapping("/api/product/packaging")
public class PackagingResource {
  private static final Logger log = LoggerFactory.getLogger(Application.class);

  @Autowired private PackagingRepository packagingRepository;

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
    Iterable<Packaging> packages = packagingRepository.findByActiveTrue();
    for (Packaging packaging : packages) {
      jArray.put(packaging.toJson());
    }
    return jArray.toString();
  }

  @RequestMapping(
      value = "/save",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> save(
      @Valid @RequestBody Packaging packaging, HttpServletRequest request) {
    Packaging savedPackaging = packagingRepository.save(packaging);
    return ResponseEntity.ok(savedPackaging.toJson().toString());
  }


  @RequestMapping(
          value = "/update",
          method = RequestMethod.PUT,
          produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> update(@Valid @RequestBody Packaging packaging, HttpServletRequest request) {
    try {
      packaging.setActive(true);
      Packaging packagingProduct = packagingRepository.save(packaging);
      return new ResponseEntity<>(packagingProduct.toJson().toString(), HttpStatus.OK);
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
      Packaging packaging = packagingRepository.getOne(id);
      packaging.setActive(false);
      Packaging savedPackaging = packagingRepository.save(packaging);
      return ResponseEntity.ok(savedPackaging.toJson().toString());
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>("not deleted", HttpStatus.BAD_REQUEST);
    }
  }
}
