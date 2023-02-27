package dev.fenix.application.security;

import dev.fenix.application.app.service.ApplicationData;
import dev.fenix.application.person.model.Person;
import dev.fenix.application.person.repository.PersonRepository;
import dev.fenix.application.security.model.Role;
import dev.fenix.application.security.model.User;
import dev.fenix.application.security.repository.RoleRepository;
import dev.fenix.application.security.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("security")
public class UserController {
  private static final Logger log = LoggerFactory.getLogger(UserController.class);
  @Autowired private UserRepository userRepository;
  @Autowired private RoleRepository roleRepository;
  @Autowired private PersonRepository personRepository;

  @GetMapping("/index")
  public String showUserList(@PageableDefault(size = 5) Pageable pageable, Model model) {
    ApplicationData data = new ApplicationData();
    model.addAttribute("data", data);

    model.addAttribute("count", personRepository.count());

    Page<Person> page = personRepository.findAllByOrderByIdDesc(pageable);
    model.addAttribute("page", page);

    // log.info(data.getAuth().getAuthorities().toString());
    // log.info(String.valueOf(data.getAuth().isAuthenticated()));

    return "security/index";
  }

  /*
    @GetMapping("/index")
  public String showTaskList(@PageableDefault(size = 10) Pageable pageable, Model model) {
    ApplicationData data = new ApplicationData();
    model.addAttribute("data", data);
    Page<Task> page = taskRepository.findAllByOrderByIdDesc(pageable);
    model.addAttribute("page", page);
    model.addAttribute("count", taskRepository.count());
    return "task/index";
  }
   */

  // add user
  @GetMapping("/signup")
  public String showSignUpForm(Person person, Model model) {
    ApplicationData data = new ApplicationData();
    model.addAttribute("data", data);
    List<Role> roles = roleRepository.findAll();

    model.addAttribute("roles", roles);
    return "security/add-user";
  }

  @PostMapping("/adduser")
  public String addUser(@Valid Person person, BindingResult result, Model model) {
    ApplicationData data = new ApplicationData();
    model.addAttribute("data", data);
    User registeredUserName = userRepository.findOneByUserName(person.getUserAccount().getUserName());
    if (registeredUserName != null) {
      result.rejectValue(
          "user.userName",
          "error.user.userName",
          "There is already a person registered with " + person.getUserAccount().getUserName());
    }

    User registeredUserEmail = userRepository.findOneByEmail(person.getUserAccount().getEmail());
    if (registeredUserEmail != null) {
      result.rejectValue(
          "user.email  ",
          "error.user.email",
          "There is already a person registered with " + person.getUserAccount().getEmail());
    }

    if (result.hasErrors()) {
      model.addAttribute("roles", roleRepository.findAll());
      result.getAllErrors().forEach(error -> log.info(error.toString()));
      return "security/add-user";
    }
    person.getUserAccount().CryptPassword();
    personRepository.save(person);
    return "redirect:/security/index";
  }

  // edit person
  @GetMapping("/edit/{id}")
  public String showUpdateForm(@PathVariable("id") Long id, Model model) {
    ApplicationData data = new ApplicationData();
    model.addAttribute("data", data);
    Person person =
        personRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid person Id:" + id));
    model.addAttribute("roles", roleRepository.findAll());
    model.addAttribute("person", person);
    return "security/update-user";
  }

  @PostMapping("/update/{id}")
  public String updateUser(
      @PathVariable("id") Long id, @Valid Person person, BindingResult result, Model model) {
    ApplicationData data = new ApplicationData();
    model.addAttribute("data", data);

    log.info(person.getUserAccount().getUserpassword());

    // log.info(person.toString());
    if (result.hasErrors()) {
      model.addAttribute("roles", roleRepository.findAll());
      result.getAllErrors().forEach(error -> log.info(error.toString()));
      person.setId(id);
      return "security/update-user";
    }
    // log.info(person.toString());

    User user = personRepository.getPersonById(person.getId()).getUserAccount();
    Date date = new Date();
    user.setModifyDate(date);

    person.setUserAccount(user);

    personRepository.save(person);
    return "redirect:/security/index";
  }

  /// delete
  @GetMapping("/delete/{id}")
  public String deleteUser(@PathVariable("id") Long id, Model model) {
    Person person =
        personRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid  person Id:" + id));
    personRepository.delete(person);
    return "redirect:/security/index";
  }

  /// delete
  @GetMapping("/activate/{id}")
  public String activateUser(@PathVariable("id") Long id, Model model) {
    Person person = personRepository.getPersonById(id);
    /*.orElseThrow(() -> new IllegalArgumentException("Invalid  person Id:" + id));*/
    person.getUserAccount().setActive(!person.getUserAccount().isActive());
    Date date = new Date();

    person.getUserAccount().setModifyDate(date);

    personRepository.save(person);

    return "redirect:/security/index";
  }
}
