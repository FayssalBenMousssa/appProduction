package dev.fenix.application.api.production.objective;


import dev.fenix.application.production.objective.model.Objective;
import dev.fenix.application.production.objective.repository.ObjectiveStaffRepository;
import dev.fenix.application.production.objective.service.ObjectiveStaffService;
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
import java.text.ParseException;

@RestController()
@RequestMapping("/api/objective/staff")
public class ObjectiveResource {
  private static final Logger log = LoggerFactory.getLogger(ObjectiveResource.class);

  @Autowired private ObjectiveStaffRepository objectiveStaffRepository;
  @Autowired private ObjectiveStaffService objectiveStaffService;

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
  public ResponseEntity index(
      HttpServletRequest request,
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "200") Integer size,
      @RequestParam(defaultValue = "id,desc") String[] sort,
      @RequestParam(required = false) String[] query) throws InterruptedException, ParseException {
    JSONArray jArray = new JSONArray();

    Iterable<Objective> objectiveStaffs = objectiveStaffService.getAllObjectiveStaffs(page, size, sort, query);
    for (Objective objectiveStaff : objectiveStaffs) {
      jArray.put(objectiveStaff.toJson());
    }
    JSONObject response = new JSONObject();
    try {
      response.put("results", jArray);
      response.put("count", jArray.length());
      response.put("total", objectiveStaffService.getCountAll());
      return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
  }

  @RequestMapping(
      value = "/save",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> save(@Valid @RequestBody Objective objective, HttpServletRequest request) {
    objective.setActive(true);
    Objective savedObjectiveStaff = objectiveStaffRepository.save(objective);
    return ResponseEntity.ok(savedObjectiveStaff.toJson().toString());
  }

  @RequestMapping(
      value = "/get/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> get(HttpServletRequest request, @PathVariable Long id)
      throws NotFoundException {
    ////log.trace("ProductResource.get method accessed");
    Objective objectiveStaff =
        objectiveStaffRepository.findById(id).orElseThrow(() -> new NotFoundException("Product  not found"));
    return new ResponseEntity<>(objectiveStaff.toJson().toString(), HttpStatus.OK);
  }

  @RequestMapping(
      value = "/update",
      method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> update(@Valid @RequestBody Objective objectiveStaff, HttpServletRequest request) {
    try {
      objectiveStaff.setActive(true);
      Objective updatedObjectiveStaff = objectiveStaffRepository.save(objectiveStaff);
      return new ResponseEntity<>(updatedObjectiveStaff.toJson().toString(), HttpStatus.OK);
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
      Objective objectiveStaff = objectiveStaffRepository.getOne(id);
      objectiveStaff.setActive(false);
      Objective deletedObjectiveStaff = objectiveStaffRepository.save(objectiveStaff);
      return ResponseEntity.ok(deletedObjectiveStaff.toJson().toString());
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}
