package dev.fenix.application.api.production.product;

import dev.fenix.application.Application;
import dev.fenix.application.production.product.model.Classification;
import dev.fenix.application.production.product.model.Product;
import dev.fenix.application.production.product.repository.ClassificationRepository;
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
@RequestMapping("/api/product/classification")
public class ClassificationResource {

  private static final Logger log = LoggerFactory.getLogger(Application.class);
  @Autowired private ClassificationRepository classificationRepository;

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
    Iterable<Classification> classifications = classificationRepository.findByActiveTrue();
    for (Classification classification : classifications) {
      jArray.put(classification.toJson());
    }
    return jArray.toString();
  }

  @RequestMapping(
      value = "/save",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> save(
      @Valid @RequestBody Classification classification, HttpServletRequest request) {
    classification.setActive(true);
    Classification savedClassification = classificationRepository.save(classification);

    /*  if (task.getAssignedTo() == null) {
      throw new RuntimeException("AssignedTo is mandatory");
    }*/

    return ResponseEntity.ok(savedClassification.toJson().toString());
  }


  @RequestMapping(
          value = "/get/{id}",
          method = RequestMethod.GET,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> get(HttpServletRequest request, @PathVariable Long id)
          throws NotFoundException {
    Classification classification=
            classificationRepository
                    .findById(id)
                    .orElseThrow(() -> new NotFoundException("Product  not found"));

    return new ResponseEntity<>(classification.toJson().toString(), HttpStatus.OK);
  }

  @RequestMapping(
          value = "/update",
          method = RequestMethod.PUT,
          produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> update(@Valid @RequestBody Classification classification, HttpServletRequest request) {
    try {
      classification.setActive(true);
      Classification updatedClassification = classificationRepository.save(classification);
      return new ResponseEntity<>(updatedClassification.toJson().toString(), HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>("Not updated", HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(
          value = "/delete/{id}",
          method = RequestMethod.DELETE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    Classification classification = classificationRepository.getOne(id);
    try {
      classification.setActive(false);
      Classification savedClassification = classificationRepository.save(classification);
      return ResponseEntity.ok(savedClassification.toJson().toString());
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>("not deleted", HttpStatus.BAD_REQUEST);
    }
  }
}
