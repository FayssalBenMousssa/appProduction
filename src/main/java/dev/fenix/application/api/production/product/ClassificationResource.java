package dev.fenix.application.api.production.product;

import dev.fenix.application.production.product.model.Classification;
import dev.fenix.application.production.product.repository.ClassificationRepository;
import dev.fenix.application.production.product.repository.ProductRepository;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController()
@RequestMapping("/api/product/classification")
public class ClassificationResource {

  private static final Logger log = LoggerFactory.getLogger(ClassificationResource.class);
  @Autowired private ClassificationRepository classificationRepository;
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
  public String index(HttpServletRequest request, @RequestParam(defaultValue = "0") Long level)
      throws NotFoundException {
    JSONArray jArray = new JSONArray();
    Iterable<Classification> classifications;

    if (level != null) {
      //  Classification parent =  classificationRepository.findById(level).orElseThrow(() -> new
      // NotFoundException("Classification  not found"));
      classifications = classificationRepository.findByActiveTrueAndLevel(level);
    } else {
      classifications = classificationRepository.findByActiveTrue();
    }

    for (Classification classification : classifications) {
      // log.info("Level : " + classification.getLevel());
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

    log.info(String.valueOf(classification));

  if (classification.getParent() != null) {
      Classification parent = classificationRepository.getOne(classification.getParent().getId());
      classification.setLevel(parent.getLevel() + 1);
    } else {
      classification.setLevel(0);
    }

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
    Classification classification =
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
  public ResponseEntity<?> update(
      @Valid @RequestBody Classification classification, HttpServletRequest request) {
    try {
      classification.setActive(true);

      Classification updatedClassification = classificationRepository.save(classification);
      return new ResponseEntity<>(updatedClassification.toJson().toString(), HttpStatus.OK);
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
    Classification classification = classificationRepository.getOne(id);
    try {
      classification.setActive(false);
      Classification savedClassification = classificationRepository.save(classification);
      log.info(savedClassification.getName() + " Removed");
      savedClassification
          .getChildren()
          .forEach(
              classificationChild -> {
                classificationChild.setActive(false);
                Classification savedClassificationChild =
                    classificationRepository.save(classificationChild);
                log.info(savedClassificationChild.getName() + " Removed");
                savedClassificationChild
                    .getChildren()
                    .forEach(
                        classificationChildOfChild -> {
                          classificationChildOfChild.setActive(false);
                          Classification savedClassificationChildOfChild =
                              classificationRepository.save(classificationChildOfChild);
                          log.info(savedClassificationChildOfChild.getName() + " Removed");
                        });
              });
      return ResponseEntity.ok(savedClassification.toJson().toString());
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(
      value = {"/info", ""},
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity info() {
    try {
      JSONArray info = new JSONArray();
      JSONObject information = new JSONObject();
      SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
      Date date = new Date();
      information.put("date", formatter.format(date));
      List<Classification> listClassification = classificationRepository.findAllByLevel(0l);

      /// start level 0
      for (Classification classification : listClassification) {

        JSONObject classificationInfo = new JSONObject();
        // classificationInfo.put("count",
        // productRepository.countByActiveTrueAndClassification(classificationRepository.findOneById(classification.getId())));
        classificationInfo.put("name", classification.getName());
        classificationInfo.put("id", classification.getId());
        //  classificationInfo.put("level", classification.getLevel());

        /// start level 1
        JSONObject classificationLevel1 = new JSONObject();
        for (Classification level1 : classification.getChildren()) {
          // classificationLevel1.put("count",
          // productRepository.countByActiveTrueAndClassification(classificationRepository.findOneById(level.getId())));
          classificationLevel1.put("name", level1.getName());
          classificationLevel1.put("id", level1.getId());
          classificationLevel1.put("level", level1.getLevel());

          JSONObject classificationLevel2 = new JSONObject();
          for (Classification level2 : classification.getChildren()) {
            classificationLevel2.put(
                "count",
                productRepository.countByActiveTrueAndClassification(
                    classificationRepository.findOneById(level2.getId())));
            classificationLevel2.put("name", level2.getName());
            classificationLevel2.put("id", level2.getId());
            classificationLevel2.put("level", level2.getLevel());
          }
        }
        /// end level 1

        classificationInfo.put("children", classificationLevel1);

        info.put(classificationInfo);
      }
      /// end level 0

      return new ResponseEntity<>(info.toString(), HttpStatus.OK);
    } catch (JSONException e) {
      e.printStackTrace();
      return new ResponseEntity<>("BAD_REQUEST", HttpStatus.BAD_REQUEST);
    }
  }
}
