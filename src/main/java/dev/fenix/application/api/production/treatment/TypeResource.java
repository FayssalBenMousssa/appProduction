package dev.fenix.application.api.production.treatment;

import dev.fenix.application.production.treatment.model.Type;
import dev.fenix.application.production.treatment.repository.DocumentRepository;
import dev.fenix.application.production.treatment.repository.TypeRepository;
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
@RequestMapping("/api/document/type")
public class TypeResource {
  private static final Logger log = LoggerFactory.getLogger(TypeResource.class);
  @Autowired private TypeRepository typeRepository;
  @Autowired private DocumentRepository documentRepository;

  @RequestMapping(
      value = "/index",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public String index(HttpServletRequest request) {
    JSONArray jArray = new JSONArray();
    // log.trace("{methodName} method accessed");

    Iterable<Type> type = typeRepository.findByActiveTrue();
    for (Type classification : type) {
      jArray.put(classification.toJson());
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
    Type Type =
        typeRepository.findById(id).orElseThrow(() -> new NotFoundException("Type  not found"));
    return new ResponseEntity<>(Type.toJson().toString(), HttpStatus.OK);
  }

  @RequestMapping(
      value = "/save",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> save(@Valid @RequestBody Type type, HttpServletRequest request) {
    // log.trace("{methodName} method accessed");
    type.setActive(true);
    Type savedType = typeRepository.save(type);
    return ResponseEntity.ok(savedType.toJson().toString());
  }

  @RequestMapping(
      value = "/update",
      method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> update(@Valid @RequestBody Type type, HttpServletRequest request) {
    try {
      // log.trace("{methodName} method accessed");
      type.setActive(true);
      Type updatedType = typeRepository.save(type);
      return new ResponseEntity<>(updatedType.toJson().toString(), HttpStatus.OK);
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
      Type type = typeRepository.getOne(id);
      type.setActive(false);
      Type savedType = typeRepository.save(type);

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
    Iterable<Type> types = typeRepository.findByActiveTrue();
    for (Type type : types) {
      int countDocs = documentRepository.countByActiveTrueAndType(type);
      JSONObject typeCont = new JSONObject();
      try {
        typeCont.put("type",type.toJson());
        typeCont.put("count_document",countDocs);
      } catch (JSONException e) {
        return new ResponseEntity<>(  e.getMessage(), HttpStatus.BAD_REQUEST);
      }
      jArray.put(typeCont);
    }
    try {
      summary.put("count" , typeRepository.countByActiveTrue());
      summary.put("types" , jArray);
    } catch (JSONException e) {
      return new ResponseEntity<>(  e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return ResponseEntity.ok(summary.toString());

  }
}
