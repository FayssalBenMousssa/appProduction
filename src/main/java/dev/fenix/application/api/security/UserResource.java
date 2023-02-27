package dev.fenix.application.api.security;

import dev.fenix.application.person.model.Person;
import dev.fenix.application.person.repository.PersonRepository;
import dev.fenix.application.production.logistic.model.Depot;
import dev.fenix.application.production.product.model.Tax;
import dev.fenix.application.security.exception.UserFoundException;
import dev.fenix.application.security.model.Action;
import dev.fenix.application.security.model.Role;
import dev.fenix.application.security.model.User;
import dev.fenix.application.security.repository.ActionRepository;
import dev.fenix.application.security.repository.RoleRepository;
import dev.fenix.application.security.repository.UserRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@RestController()
@RequestMapping("/api/security")

@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserResource {

  private static final Logger log = LoggerFactory.getLogger(UserResource.class);
  @Autowired private PersonRepository personRepository;
  @Autowired private UserRepository userRepository;
  @Autowired private RoleRepository roleRepository;
  @Autowired private ActionRepository actionRepository;

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
          value = "/role/index",
          method = RequestMethod.GET,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public String roles(HttpServletRequest request) {
    JSONArray jArray = new JSONArray();
    Iterable<Role> roles = roleRepository.findAll();
    for (Role role : roles) {
      jArray.put(role.toSmallJson());
    }
    return jArray.toString();
  }

  @RequestMapping(
          value = "/role/update",
          method = RequestMethod.PUT,
          produces = MediaType.APPLICATION_JSON_VALUE
         )
  @ResponseBody
  public ResponseEntity<?> update(@Valid @RequestBody Role role, HttpServletRequest request) {

    try {
      Role updatedRole = roleRepository.save(role);
      return new ResponseEntity<>(updatedRole.toSmallJson().toString(), HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(
          value = "/role/get/{id}",
          method = RequestMethod.GET,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> readRole(@PathVariable("id") Long id) {
    Role role = roleRepository.getOne(id);

    if (role == null) {
      throw new RuntimeException("Invalid  role Id : " + id);
    } else {
      return ResponseEntity.ok(role.toSmallJson().toString());
    }
  }

  @RequestMapping(
          value = "/action/index",
          method = RequestMethod.GET,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public String actions(HttpServletRequest request) {
    JSONArray jArray = new JSONArray();
    Iterable<Action> actions = actionRepository.findAll();
    for (Action action : actions) {
      jArray.put(action.toJson());
    }
    return jArray.toString();
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
      return ResponseEntity.ok(person.toJson().toString());
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
      return ResponseEntity.ok(user.getPerson().toJson().toString());
    }
  }

  @RequestMapping(
      value = "/adduser",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> createUser(
      @Valid @RequestBody Person person, HttpServletRequest request) {

    if (person.getUserAccount() == null) {
      throw new RuntimeException("user is mandatory");
    }
    User registeredUserName = userRepository.findOneByUserName(person.getUserAccount().getUserName());
    if (registeredUserName != null) {
      throw new UserFoundException(
          "There is already a person registered with " + person.getUserAccount().getUserName());
    }

    User registeredUserEmail = userRepository.findOneByEmail(person.getUserAccount().getEmail());
    if (registeredUserEmail != null) {
      throw new UserFoundException(
          "There is already a person registered with " + person.getUserAccount().getEmail());
    }
    person.getUserAccount().CryptPassword();
    Person savedPerson = personRepository.save(person);
    return ResponseEntity.ok(savedPerson.toJson().toString());
  }
}
