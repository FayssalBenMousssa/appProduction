package dev.fenix.application.api.production.treatment;

import dev.fenix.application.production.treatment.model.Category;
import dev.fenix.application.production.treatment.model.Type;
import dev.fenix.application.production.treatment.repository.CategoryRepository;
import dev.fenix.application.production.treatment.repository.DocumentRepository;
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

@RestController()
@RequestMapping("/api/document/type/category")
public class CategoryResource {
  private static final Logger log = LoggerFactory.getLogger(CategoryResource.class);

  @Autowired private CategoryRepository categoryRepository ;
  @Autowired private DocumentRepository documentRepository;
  @RequestMapping(
      value = "/index",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public String index(HttpServletRequest request) {
    JSONArray jArray = new JSONArray();
    // log.trace("{methodName} method accessed");
    Iterable<Category> categories = categoryRepository.findByActiveTrue();
    for (Category category : categories) {
      jArray.put(category.toJson());
    }
    return jArray.toString();
  }

  @RequestMapping(
      value = "/get/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> get(HttpServletRequest request, @PathVariable Long id)
      throws NotFoundException {
    log.trace("ProductResource.get method accessed");
    Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Product  not found"));
    return new ResponseEntity<>(category.toJson().toString(), HttpStatus.OK);
  }

  @RequestMapping(
      value = "/save",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> save(@Valid @RequestBody Category category, HttpServletRequest request) {
    // log.trace("{methodName} method accessed");
    category.setActive(true);
    Category  savedCategory = categoryRepository.save(category);
    return ResponseEntity.ok(savedCategory.toJson().toString());
  }

  @RequestMapping(
      value = "/update",
      method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> update(@Valid @RequestBody Category category, HttpServletRequest request) {
    try {
      // log.trace("{methodName} method accessed");
      category.setActive(true);
      Category updatedCategory = categoryRepository.save(category);
      return new ResponseEntity<>(updatedCategory.toJson().toString(), HttpStatus.OK);
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
      Category category = categoryRepository.getOne(id);
      category.setActive(false);
      Category savedType = categoryRepository.save(category);

      return ResponseEntity.ok(savedType.toJson().toString());

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }


  @RequestMapping(
          value = "/summary",
          method = RequestMethod.GET,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity summary(HttpServletRequest request) {
    JSONObject summary = new JSONObject();

    JSONArray jArray = new JSONArray();
    Iterable<Category> categories  = categoryRepository.findByActiveTrue();
    for (Category category : categories) {
      int countDocs = documentRepository.countByActiveTrueAndTypeCategory(category);
      JSONObject categoryCont = new JSONObject();
      try {
        categoryCont.put("category",category.toJson());
        categoryCont.put("count_document",countDocs);
      } catch (JSONException e) {
        return new ResponseEntity<>(  e.getMessage(), HttpStatus.BAD_REQUEST);
      }
      jArray.put(categoryCont);
    }
    try {
      summary.put("count" , categoryRepository.countByActiveTrue());
      summary.put("categories" , jArray);
    } catch (JSONException e) {
      return new ResponseEntity<>(  e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return ResponseEntity.ok(summary.toString());

  }
}
