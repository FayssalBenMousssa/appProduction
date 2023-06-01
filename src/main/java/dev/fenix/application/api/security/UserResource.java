package dev.fenix.application.api.security;

import dev.fenix.application.business.model.Enterprise;
import dev.fenix.application.business.repository.EnterpriseRepository;
import dev.fenix.application.person.model.Person;
import dev.fenix.application.person.repository.PersonRepository;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@RestController()
@RequestMapping("/api/security/")


@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserResource {

    private static final Logger log = LoggerFactory.getLogger(UserResource.class);
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

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
            value = "/user/authorisation/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> authorisation(HttpServletRequest request) throws InterruptedException {

        /// TimeUnit.SECONDS.sleep(5);
        JSONArray jArray = new JSONArray();
        Iterable<Person> people = personRepository.findPersons(true);
        if (people != null) {
            people.forEach(person -> {
                if (person.getUserAccount() != null) {
                    jArray.put(person.toSmallJsonUser());
                }

            });
            return new ResponseEntity<>(jArray.toString(), HttpStatus.OK);
        }

        return new ResponseEntity<>("BAD_REQUEST", HttpStatus.BAD_REQUEST);


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
    public ResponseEntity<String> readByUsername(@PathVariable("username") String username
            , @RequestParam(required = false) boolean optimise
            ,@RequestParam(required = false) boolean first_login
    ) {
        User user = userRepository.findOneByUserName(username);
        if (user == null) {
            throw new RuntimeException("Invalid  person Id : " + username);
        } else {
            if (optimise) {
                return ResponseEntity.ok(user.getPerson().toSmallJsonUser().toString());
            } else if (first_login) {
                return ResponseEntity.ok(user.getPerson().loginJson().toString());
            }
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


    @RequestMapping(
            value = "user/status",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> userStatus(@RequestBody Person person, HttpServletRequest request) {
        if (person != null) {
            person = personRepository.getPersonById(person.getId());
            person.getUserAccount().setActive(!person.getUserAccount().isActive());
            Person savedPerson = personRepository.save(person);
            return ResponseEntity.ok(savedPerson.toSmallJsonUser().toString());

        }
        return new ResponseEntity<>("BAD_REQUEST", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/user/changePassword")
    public ResponseEntity changePasswordUser(
                                         @RequestParam("oldPassword") String oldPassword,
                                         @RequestParam("newPassword") String newPassword,
                                         @RequestParam("email") String email) {
        if (this.changePassword(oldPassword, newPassword, email)) {
            return new ResponseEntity<>("change_password.valid", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("change_password.invalid", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(
            value = "user/role/update",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> userUpdateRoles(@RequestBody Person person, HttpServletRequest request) {
        if (person != null & person.getUserAccount() != null) {

            Set<Role> roles = person.getUserAccount().getRoles();
            Set<Role> db_roles = new HashSet<>();
            for (Role role : roles) {
                role = roleRepository.getOne(role.getId());
                db_roles.add(role);
            }
            person = personRepository.getPersonById(person.getId());
            person.getUserAccount().setRoles(db_roles);

            Person savedPerson = personRepository.save(person);
            return new ResponseEntity<>(savedPerson.toSmallJsonUser().toString(), HttpStatus.OK);
        }
        return new ResponseEntity<>("BAD_REQUEST", HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(
            value = "user/enterprise/select",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody

    public ResponseEntity<?> setLogInEnterprise(@RequestBody Enterprise enterprise, HttpServletRequest request) {
        User user = this.getCurrentUser();
        Enterprise localEnterprise = this.enterpriseRepository.getOne(enterprise.getId());

        boolean isInUserEnterprises = user.getEnterprises().contains(localEnterprise);


        log.info("user != null" + (user != null));
        log.info("localEnterprise " + (localEnterprise != null));
        log.info("isInUserEnterprises " + isInUserEnterprises);

        if (user != null && localEnterprise != null &&   user.hasEnterprise(localEnterprise)){
            user.setLogInEnterprise(localEnterprise);
            userRepository.save(user);
            return new ResponseEntity<>(user.getPerson().toJson().toString(), HttpStatus.OK);
        }

        return new ResponseEntity<>("BAD_REQUEST", HttpStatus.BAD_REQUEST);
    }

    public boolean changePassword(String oldPassword, String newPassword, String email) {
        User user = userRepository.findByEmailIgnoreCase(email);


        if (user == null){
            return false;
        }
        boolean passwordIsOk = passwordEncoder.matches(oldPassword, user.getPassword() );
        if (passwordIsOk) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }

        return false;
    }

    private User getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        User user = userRepository.findOneByUserName(username);
        return user;
    }
}
