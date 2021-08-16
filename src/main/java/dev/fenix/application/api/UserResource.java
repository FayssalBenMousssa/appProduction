package dev.fenix.application.api;

import dev.fenix.application.Application;
import dev.fenix.application.person.model.Person;
import dev.fenix.application.person.repository.PersonRepository;
import dev.fenix.application.security.exception.UserFoundException;
import dev.fenix.application.security.model.User;
import dev.fenix.application.security.repository.UserRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@RestController()
@RequestMapping("/api/security")
@Validated
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserResource {

  private static final Logger log = LoggerFactory.getLogger(Application.class);
  @Autowired private PersonRepository personRepository;
  @Autowired private UserRepository userRepository;

  @RequestMapping(
      value = "/",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public String index(HttpServletRequest request) {

    /// Get User from request
    Principal principal = request.getUserPrincipal();

    JSONArray jArray = new JSONArray();
    JSONObject jObject = new JSONObject();

    try {
      jObject.put("security", jArray);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return jObject.toString();
  }

  @RequestMapping(
      value = "/status",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public String status(HttpServletRequest request) {

    return "Yes";
  }

  @RequestMapping(
      value = "/user/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> read(@PathVariable("id") Long id) {
    Person person = personRepository.getPersonById(id);

    if (person == null) {
      throw new RuntimeException("Invalid  person Id : " + id);
    } else {
      return ResponseEntity.ok(person._toJson().toString());
    }
  }

  @RequestMapping(
      value = "/username/{username}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> readByUsername(@PathVariable("username") String username) {
    User user = userRepository.findOneByUserName(username);
    if (user == null) {
      throw new RuntimeException("Invalid  person Id : " + username);
    } else {
      return ResponseEntity.ok(user.getPerson()._toJson().toString());
    }
  }

  @RequestMapping(
      value = "/adduser",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> createUser(
      @Valid @RequestBody Person person, HttpServletRequest request) {

    if (person.getUser() == null) {
      throw new RuntimeException("user is mandatory");
    }
    User registeredUserName = userRepository.findOneByUserName(person.getUser().getUserName());
    if (registeredUserName != null) {
      throw new UserFoundException(
          "There is already a person registered with " + person.getUser().getUserName());
    }

    User registeredUserEmail = userRepository.findOneByEmail(person.getUser().getEmail());
    if (registeredUserEmail != null) {
      throw new UserFoundException(
          "There is already a person registered with " + person.getUser().getEmail());
    }
    person.getUser().CryptPassword();
    Person savedPerson = personRepository.save(person);
    return ResponseEntity.ok(savedPerson._toJson().toString());
  }
}
