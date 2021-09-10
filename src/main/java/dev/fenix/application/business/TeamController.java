package dev.fenix.application.business;

import dev.fenix.application.app.service.ApplicationData;
import dev.fenix.application.business.model.Team;
import dev.fenix.application.business.repository.TeamRepository;
import dev.fenix.application.person.repository.PersonRepository;
import dev.fenix.application.person.service.PersonService;
import dev.fenix.application.security.UserController;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("business/team")
public class TeamController {
  private static final Logger log = LoggerFactory.getLogger(TeamController.class);
  @Autowired private TeamRepository teamRepository;
  @Autowired private PersonRepository personRepository;
  @Autowired private PersonService personService;

  @GetMapping("/index")
  public String showTeamList(@PageableDefault(size = 50) Pageable pageable, Model model) {
    ApplicationData data = new ApplicationData();
    model.addAttribute("data", data);
    Page<Team> page = teamRepository.findTeamPerson(pageable);
    model.addAttribute("page", page);
    model.addAttribute("count", teamRepository.count());
    // model.addAttribute("teams", teamRepository.findAll());
    return "business/team/index";
  }

  // add user
  @GetMapping("/add")
  public String showAddForm(Team team, Model model) {
    ApplicationData data = new ApplicationData();
    model.addAttribute("data", data);
    model.addAttribute("persons", personService.getPersons());
    return "business/team/add-team";
  }

  @PostMapping("/addteam")
  public String addTeam(@Valid Team team, BindingResult result, Model model) {
    if (result.hasErrors()) {
      ApplicationData data = new ApplicationData();
      /// TODO FIND A BETTER WAY
      List<Long> selected = new ArrayList<Long>();
      team.getPeople().forEach(person -> selected.add(person.getId()));
      model.addAttribute("selected", selected);
      model.addAttribute("data", data);
      model.addAttribute("persons", personService.getPersons());

      return "business/team/add-team";
    }
    teamRepository.save(team);
    return "redirect:/business/team/index";
  }

  // edit user
  @GetMapping("/edit/{id}")
  public String showUpdateForm(@PathVariable("id") Long id, Model model) {
    ApplicationData data = new ApplicationData();

    Team team =
        teamRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid team Id:" + id));
    List<Long> selected = new ArrayList<Long>();
    team.getPeople().forEach(person -> selected.add(person.getId()));

    model.addAttribute("selected", selected);
    model.addAttribute("data", data);
    model.addAttribute("team", team);
    model.addAttribute("persons", personService.getPersons());

    return "business/team/update-team";
  }

  @PostMapping("/update/{id}")
  public String updateTeam(
      @PathVariable("id") Long id, @Valid Team team, BindingResult result, Model model) {
    ApplicationData data = new ApplicationData();
    model.addAttribute("data", data);
    if (result.hasErrors()) {

      List<Long> selected = new ArrayList<Long>();
      team.getPeople().forEach(person -> selected.add(person.getId()));
      model.addAttribute("selected", selected);
      model.addAttribute("persons", personService.getPersons());
      team.setId(id);
      return "business/team/update-team";
    }

    teamRepository.save(team);
    return "redirect:/business/team/index";
  }

  /// delete
  @GetMapping("/delete/{id}")
  public String deleteTeam(@PathVariable("id") Long id, Model model) {
    Team team =
        teamRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    teamRepository.delete(team);
    return "redirect:/business/team/index";
  }
}
