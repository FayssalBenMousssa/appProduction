package dev.fenix.application.person;

import dev.fenix.application.app.service.ApplicationData;
import dev.fenix.application.person.model.Person;
import dev.fenix.application.person.repository.PersonRepository;
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

@Controller
@RequestMapping("/person")
public class PersonController {

  private static final Logger log = LoggerFactory.getLogger(PersonController.class);
  @Autowired private PersonRepository personRepository;

  @GetMapping("/index")
  public String showPersonList(@PageableDefault(size = 50) Pageable pageable, Model model) {
    ApplicationData data = new ApplicationData();
    model.addAttribute("data", data);
    Page<Person> page = personRepository.findAll(pageable);
    model.addAttribute("page", page);
    model.addAttribute("count", personRepository.count());

    // model.addAttribute("persons", personRepository.findAll());
    return "person/index";
  }

  // add person
  @GetMapping("/add")
  public String showAddForm(Person person, Model model) {
    ApplicationData data = new ApplicationData();
    model.addAttribute("data", data);
    return "person/add-person";
  }

  @PostMapping("/addperson")
  public String addPerson(@Valid Person person, BindingResult result, Model model) {
    ApplicationData data = new ApplicationData();
    model.addAttribute("data", data);

    if (result.hasErrors()) {
      result.getAllErrors().forEach(error -> log.info(error.toString()));
      return "person/add-person";
    }

    personRepository.save(person);
    return "redirect:/person/index";
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
    model.addAttribute("person", person);
    return "person/update-person";
  }

  @PostMapping("/update/{id}")
  public String updatePerson(
      @PathVariable("id") Long id, @Valid Person person, BindingResult result, Model model) {
    ApplicationData data = new ApplicationData();
    model.addAttribute("data", data);
    if (result.hasErrors()) {
      person.setId(id);

      return "person/update-person";
    }

    personRepository.save(person);
    return "redirect:/person/index";
  }

  /// delete
  @GetMapping("/delete/{id}")
  public String deletePerson(@PathVariable("id") Long id, Model model) {
    Person person =
        personRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid person Id:" + id));
    personRepository.delete(person);
    return "redirect:/person/index";
  }
}
