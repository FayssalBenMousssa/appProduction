package dev.fenix.application.api.security;

import dev.fenix.application.business.model.Enterprise;
import dev.fenix.application.business.repository.EnterpriseRepository;
import dev.fenix.application.business.repository.StaffRepository;
import dev.fenix.application.configuration.database.DBContextHolder;
import dev.fenix.application.configuration.database.DBEnum;
import dev.fenix.application.person.model.Person;
import dev.fenix.application.person.repository.PersonRepository;
import dev.fenix.application.security.exception.UserFoundException;
import dev.fenix.application.security.model.Action;
import dev.fenix.application.security.model.Role;
import dev.fenix.application.security.model.Setting;
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
import java.util.Random;
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
    private StaffRepository staffRepository;

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
        DBContextHolder.setCurrentDb(DBEnum.MAIN);
        /// TimeUnit.SECONDS.sleep(5);
        JSONArray jArray = new JSONArray();
        Iterable<Person> people = personRepository.findPersons(true);
        if (people != null) {
            people.forEach(person -> {
                if (person.getUserAccount() != null) {
                    jArray.put(person.toSmallJsonUser());
                    // documentDBContextHolder.getCurrentDb().toString());
                    // documentperson.getFullName() + " " + person.getUserAccount().getEnterprises().size());
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
            value = "/user/selected_db",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> selectedDb(HttpServletRequest request) {
        User user = this.getCurrentUser();

        if (user == null) {
            throw new RuntimeException("Invalid user ");
        } else {
            return ResponseEntity.ok(user.getLogInEnterprise().toJson().toString());
        }
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
            , @RequestParam(required = false) boolean first_login
    ) {
        User user = userRepository.findOneByUserName(username);
        int globalId = user.getPerson().getGlobalId();

        if (user == null) {
            throw new RuntimeException("Invalid  person username : " + username);
        } else {

            if (user.getEnterprises().size() == 1) {
                user.setLogInEnterprise(user.getEnterprises().iterator().next());
                DBContextHolder.setCurrentDb(user.getLogInEnterprise().getEnterpriseDatabase());
            }
            if (optimise) {
                return ResponseEntity.ok(user.getPerson().toSmallJsonUser().toString());
            } else if (first_login) {
                DBContextHolder.setCurrentDb(DBEnum.CANELIA);
                Person person = personRepository.findByGlobalId(globalId);

                log.info(this.getCurrentUser().getUserName() + " has authenticate ");
                for (Setting setting : person.getUserAccount().getSettings()) {
                    log.info( "Setting : " + setting.getName() + "  " + setting.getValue());
                }

                return ResponseEntity.ok(person.loginJson().toString());
            }
            return ResponseEntity.ok(user.getPerson().toJson().toString());
        }
    }

    @RequestMapping(
            value = "/adduser",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> createUser(@Valid @RequestBody Person person, HttpServletRequest request) {


        DBContextHolder.setCurrentDb(DBEnum.MAIN);

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

        Random random = new Random();
        int randomNumber = random.nextInt(1000000);

        person.setGlobalId(randomNumber);

        DBContextHolder.setCurrentDb(DBEnum.MAIN);
        person.getUserAccount().CryptPassword();
        Person savedPerson = personRepository.save(person);

        DBContextHolder.setCurrentDb(DBEnum.OVOFRAIS);
        personRepository.save(person);

        DBContextHolder.setCurrentDb(DBEnum.FRAISCAPRICES);
        personRepository.save(person);

        DBContextHolder.setCurrentDb(DBEnum.CANELIA);
        personRepository.save(person);

        return ResponseEntity.ok(savedPerson.toJson().toString());
    }


    @RequestMapping(
            value = "user/status",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> userStatus(@RequestBody Person person, HttpServletRequest request) {
        if (person != null) {
            DBContextHolder.setCurrentDb(DBEnum.MAIN);
            person = personRepository.getPersonById(person.getId());
            int globalId = person.getGlobalId();
            person.getUserAccount().setActive(!person.getUserAccount().isActive());
            personRepository.save(person);

            DBContextHolder.setCurrentDb(DBEnum.OVOFRAIS);
            Person ovoPerson = personRepository.findByGlobalId(globalId);
            ovoPerson.getUserAccount().setActive(!ovoPerson.getUserAccount().isActive());
            personRepository.save(ovoPerson);


            DBContextHolder.setCurrentDb(DBEnum.FRAISCAPRICES);
            Person fcPerson = personRepository.findByGlobalId(globalId);
            fcPerson.getUserAccount().setActive(!fcPerson.getUserAccount().isActive());
            personRepository.save(fcPerson);


            DBContextHolder.setCurrentDb(DBEnum.CANELIA);
            Person caPerson = personRepository.findByGlobalId(globalId);
            caPerson.getUserAccount().setActive(!caPerson.getUserAccount().isActive());
            personRepository.save(caPerson);

            return ResponseEntity.ok(caPerson.toSmallJsonUser().toString());

        }
        return new ResponseEntity<>("BAD_REQUEST", HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(
            value = "user/staff",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> userStaff(@RequestBody Person person, HttpServletRequest request) {
        if (person != null) {
            Person personDB = personRepository.getPersonById(person.getId());

            personDB.setStaffs(person.getStaffs());

            Person savedPerson = personRepository.save(personDB);
            DBContextHolder.setCurrentDb(DBEnum.OVOFRAIS);
            personRepository.save(personDB);
            DBContextHolder.setCurrentDb(DBEnum.FRAISCAPRICES);
            personRepository.save(personDB);
            DBContextHolder.setCurrentDb(DBEnum.CANELIA);
            personRepository.save(personDB);


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
            DBContextHolder.setCurrentDb(DBEnum.MAIN);
            person = personRepository.getPersonById(person.getId());
            int globalId = person.getGlobalId();

            DBContextHolder.setCurrentDb(DBEnum.MAIN);
            Person mainPerson = personRepository.findByGlobalId(globalId);
            mainPerson.getUserAccount().setRoles(db_roles);
            personRepository.save(mainPerson);

            DBContextHolder.setCurrentDb(DBEnum.OVOFRAIS);
            Person ovoPerson = personRepository.findByGlobalId(globalId);
            ovoPerson.getUserAccount().setRoles(db_roles);
            personRepository.save(ovoPerson);

            DBContextHolder.setCurrentDb(DBEnum.FRAISCAPRICES);
            Person fcPerson = personRepository.findByGlobalId(globalId);
            fcPerson.getUserAccount().setRoles(db_roles);
            personRepository.save(fcPerson);

            DBContextHolder.setCurrentDb(DBEnum.CANELIA);
            Person caPerson = personRepository.findByGlobalId(globalId);
            caPerson.getUserAccount().setRoles(db_roles);
            personRepository.save(caPerson);


            return new ResponseEntity<>(caPerson.toSmallJsonUser().toString(), HttpStatus.OK);
        }
        return new ResponseEntity<>("BAD_REQUEST", HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(
            value = "user/enterprise/update",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> userUpdateEnterprises(@RequestBody User user, HttpServletRequest request) {

        if (user.getId() != null) {
            User dbUser = userRepository.findOneByUserName(user.getUserName());
            int globalId = dbUser.getPerson().getGlobalId();

            Set<Enterprise> enterprises = user.getEnterprises();


            DBContextHolder.setCurrentDb(DBEnum.MAIN);
            Person mainPerson = personRepository.findByGlobalId(globalId);
            mainPerson.getUserAccount().setEnterprises(enterprises);
            personRepository.save(mainPerson);

            DBContextHolder.setCurrentDb(DBEnum.OVOFRAIS);
            Person ovoPerson = personRepository.findByGlobalId(globalId);
            ovoPerson.getUserAccount().setEnterprises(enterprises);
            personRepository.save(ovoPerson);


            DBContextHolder.setCurrentDb(DBEnum.FRAISCAPRICES);
            Person fcPerson = personRepository.findByGlobalId(globalId);
            fcPerson.getUserAccount().setEnterprises(enterprises);
            personRepository.save(fcPerson);


            DBContextHolder.setCurrentDb(DBEnum.CANELIA);
            Person caPerson = personRepository.findByGlobalId(globalId);
            caPerson.getUserAccount().setEnterprises(enterprises);
            personRepository.save(caPerson);


            return new ResponseEntity<>(caPerson.toSmallJson().toString(), HttpStatus.OK);
        }


        return new ResponseEntity<>("BAD_REQUEST", HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(
            value = "user/enterprise/select",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody

    public ResponseEntity<?> setLogInEnterprise(@RequestBody Enterprise enterprise, HttpServletRequest request) {

        DBContextHolder.setCurrentDb(DBEnum.MAIN);
        User user = this.getCurrentUser();
        Enterprise localEnterprise = this.enterpriseRepository.getOne(enterprise.getId());
        if (user != null && localEnterprise != null && user.hasEnterprise(localEnterprise)) {
            int globalId = user.getPerson().getGlobalId();

            user.setLogInEnterprise(localEnterprise);


            Person mainPerson = personRepository.findByGlobalId(globalId);
            if (mainPerson != null && mainPerson.getUserAccount() != null) {
                mainPerson.getUserAccount().setLogInEnterprise(localEnterprise);
                personRepository.save(mainPerson);
            }


            DBContextHolder.setCurrentDb(DBEnum.OVOFRAIS);
            Person ovoPerson = personRepository.findByGlobalId(globalId);
            if (ovoPerson != null && ovoPerson.getUserAccount() != null) {
                ovoPerson.getUserAccount().setLogInEnterprise(localEnterprise);
                personRepository.save(ovoPerson);
            }


            DBContextHolder.setCurrentDb(DBEnum.FRAISCAPRICES);
            Person fcPerson = personRepository.findByGlobalId(globalId);
            if (fcPerson != null && fcPerson.getUserAccount() != null) {
                fcPerson.getUserAccount().setLogInEnterprise(localEnterprise);
                personRepository.save(fcPerson);
            }


            DBContextHolder.setCurrentDb(DBEnum.CANELIA);
            Person caPerson = personRepository.findByGlobalId(globalId);
            if (caPerson != null && caPerson.getUserAccount() != null) {
                caPerson.getUserAccount().setLogInEnterprise(localEnterprise);
                personRepository.save(caPerson);
            }


            // boolean isInUserEnterprises = user.getEnterprises().contains(localEnterprise);


            //// document"isInUserEnterprises " + isInUserEnterprises);


            return new ResponseEntity<>(user.getPerson().toJson().toString(), HttpStatus.OK);
        }

        return new ResponseEntity<>("BAD_REQUEST", HttpStatus.BAD_REQUEST);
    }

    public boolean changePassword(String oldPassword, String newPassword, String email) {
        User user = userRepository.findByEmailIgnoreCase(email);
        if (user == null) {
            return false;
        }
        boolean passwordIsOk = passwordEncoder.matches(oldPassword, user.getPassword());
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
