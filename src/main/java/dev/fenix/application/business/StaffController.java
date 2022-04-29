package dev.fenix.application.business;

import dev.fenix.application.app.service.ApplicationData;
import dev.fenix.application.business.model.Staff;
import dev.fenix.application.business.repository.JobRepository;
import dev.fenix.application.business.repository.StaffRepository;
import dev.fenix.application.business.service.JobService;
import dev.fenix.application.person.repository.PersonRepository;
import dev.fenix.application.person.service.PersonService;
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

@Controller
@RequestMapping("business/staff")
public class StaffController {
  private static final Logger log = LoggerFactory.getLogger(StaffController.class);

  @Autowired private StaffRepository staffRepository;

  @Autowired private JobRepository jobRepository;

  @Autowired private PersonRepository personRepository;

  @Autowired private PersonService personService;

  @Autowired private JobService jobService;

  @GetMapping("/index")
  public String showStaffList(@PageableDefault(size = 100) Pageable pageable, Model model) {
    ApplicationData data = new ApplicationData();
    model.addAttribute("data", data);
    Page<Staff> page = staffRepository.findAll(pageable);
    model.addAttribute("page", page);
    model.addAttribute("count", staffRepository.count());

    // model.addAttribute("staffs", staffRepository.findAll());
    return "business/staff/index";
  }

  // add user
  @GetMapping("/add")
  public String showAddForm(Staff staff, Model model) {

    model.addAttribute("jobs", jobService.getJobs());
    model.addAttribute("persons", personService.getPersons());
    ApplicationData data = new ApplicationData();

    if (staff.getStartDate() == null) {
      staff.setStartDate(new Date());
    }

    model.addAttribute("data", data);
    return "business/staff/add-staff";
  }

  @PostMapping("/addstaff")
  public String addStaff(@Valid Staff staff, BindingResult result, Model model) {
    ApplicationData data = new ApplicationData();
    model.addAttribute("data", data);

    if (result.hasErrors()) {

      model.addAttribute("jobs", jobService.getJobs());
      model.addAttribute("persons", personService.getPersons());
      return "business/staff/add-staff";
    }

    staffRepository.save(staff);
    return "redirect:/business/staff/index";
  }

  // edit user
  @GetMapping("/edit/{id}")
  public String showUpdateForm(@PathVariable("id") Long id, Model model) {
    ApplicationData data = new ApplicationData();
    model.addAttribute("jobs", jobService.getJobs());
    model.addAttribute("persons", personService.getPersons());
    model.addAttribute("data", data);
    Staff staff =
        staffRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid staff Id:" + id));
    model.addAttribute("staff", staff);
    return "business/staff/update-staff";
  }

  @PostMapping("/update/{id}")
  public String updateStaff(
      @PathVariable("id") Long id, @Valid Staff staff, BindingResult result, Model model) {
    ApplicationData data = new ApplicationData();
    model.addAttribute("data", data);
    if (result.hasErrors()) {
      model.addAttribute("jobs", jobService.getJobs());
      model.addAttribute("persons", personService.getPersons());
      staff.setId(id);

      return "business/staff/update-staff";
    }

    staffRepository.save(staff);
    return "redirect:/business/staff/index";
  }

  /// delete
  @GetMapping("/delete/{id}")
  public String deleteStaff(@PathVariable("id") Long id, Model model) {
    Staff staff =
        staffRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    staffRepository.delete(staff);
    return "redirect:/business/staff/index";
  }
}
