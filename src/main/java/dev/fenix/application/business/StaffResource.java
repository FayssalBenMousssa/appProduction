package dev.fenix.application.business;

import dev.fenix.application.business.model.Staff;
import dev.fenix.application.business.repository.StaffRepository;
import dev.fenix.application.business.service.StaffService;
import dev.fenix.application.configuration.database.DBContextHolder;
import dev.fenix.application.configuration.database.DBEnum;
import dev.fenix.application.person.model.Person;
import dev.fenix.application.person.repository.PersonRepository;
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
@RequestMapping("/api/staff")
public class StaffResource {
  private static final Logger log = LoggerFactory.getLogger(StaffResource.class);

  @Autowired private StaffRepository staffRepository;
  @Autowired private StaffService staffService;

  @Autowired
  private PersonRepository personRepository;

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
      @RequestParam(required = false) String[] query)
      throws InterruptedException {
    JSONArray jArray = new JSONArray();

    Iterable<Staff> staffs = staffService.getAllStaffs(page, size, sort, query);
    for (Staff staff : staffs) {
      jArray.put(staff.toSmallJson());
    }
    JSONObject response = new JSONObject();
    try {
      response.put("results", jArray);
      response.put("count", jArray.length());
      response.put("total", staffService.getCountAll());
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
  public ResponseEntity<?> save(@Valid @RequestBody Staff staff, HttpServletRequest request) {

    DBEnum db = DBContextHolder.getCurrentDb();
    DBContextHolder.setCurrentDb(DBEnum.MAIN);
    Person person =  personRepository.getPersonById(staff.getPerson().getId());
    int globalId = person.getGlobalId();
    staff.setActive(true);
    DBContextHolder.setCurrentDb(db);
    Person dbPerson = personRepository.findByGlobalId(globalId);
    staff.setPerson(dbPerson);
    Staff savedStaff = staffRepository.save(staff);
    return ResponseEntity.ok(savedStaff.toJson().toString());
  }

  @RequestMapping(
      value = "/get/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> get(HttpServletRequest request, @PathVariable Long id)
      throws NotFoundException {
    ////log.trace("ProductResource.get method accessed");
    Staff staff =
        staffRepository.findById(id).orElseThrow(() -> new NotFoundException("Product  not found"));
    return new ResponseEntity<>(staff.toJson().toString(), HttpStatus.OK);
  }

  @RequestMapping(
      value = "/update",
      method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> update(@Valid @RequestBody Staff staff, HttpServletRequest request) {
    try {
      staff.setActive(true);
      DBContextHolder.setCurrentDb(DBEnum.MAIN);
      Staff updatedStaff = staffRepository.save(staff);
      DBContextHolder.setCurrentDb(DBEnum.OVOFRAIS);
      staffRepository.save(staff);
      DBContextHolder.setCurrentDb(DBEnum.FRAISCAPRICES);
      staffRepository.save(staff);
      DBContextHolder.setCurrentDb(DBEnum.CANELIA);
      staffRepository.save(staff);
      return new ResponseEntity<>(updatedStaff.toJson().toString(), HttpStatus.OK);
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
      Staff staff = staffRepository.getOne(id);
      staff.setActive(false);
      DBContextHolder.setCurrentDb(DBEnum.MAIN);
      Staff deletedStaff = staffRepository.save(staff);
      DBContextHolder.setCurrentDb(DBEnum.OVOFRAIS);
      staffRepository.save(staff);
      DBContextHolder.setCurrentDb(DBEnum.FRAISCAPRICES);
      staffRepository.save(staff);
      DBContextHolder.setCurrentDb(DBEnum.CANELIA);
      staffRepository.save(staff);
      return ResponseEntity.ok(deletedStaff.toJson().toString());
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}
