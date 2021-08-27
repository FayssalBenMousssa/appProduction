package dev.fenix.application.business;

import dev.fenix.application.app.service.ApplicationData;
import dev.fenix.application.business.model.Job;
import dev.fenix.application.business.repository.JobRepository;
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
@RequestMapping("/business/job")
public class JobController {

  @Autowired private JobRepository jobRepository;

  @GetMapping("/index")
  public String showJobList(@PageableDefault(size = 20) Pageable pageable, Model model) {
    ApplicationData data = new ApplicationData();
    model.addAttribute("data", data);
    Page<Job> page = jobRepository.findAll(pageable);
    model.addAttribute("page", page);
    model.addAttribute("count", jobRepository.count());

    // model.addAttribute("jobs", jobRepository.findAll());
    return "business/job/index";
  }

  // add user
  @GetMapping("/add")
  public String showAddForm(Job job, Model model) {
    ApplicationData data = new ApplicationData();
    model.addAttribute("data", data);
    return "business/job/add-job";
  }

  @PostMapping("/addjob")
  public String addJob(@Valid Job job, BindingResult result, Model model) {
    ApplicationData data = new ApplicationData();
    model.addAttribute("data", data);

    if (result.hasErrors()) {
      return "business/job/add-job";
    }

    jobRepository.save(job);
    return "redirect:/business/job/index";
  }

  // edit user
  @GetMapping("/edit/{id}")
  public String showUpdateForm(@PathVariable("id") Long id, Model model) {
    ApplicationData data = new ApplicationData();
    model.addAttribute("data", data);
    Job job =
        jobRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid job Id:" + id));
    model.addAttribute("job", job);
    return "business/job/update-job";
  }

  @PostMapping("/update/{id}")
  public String updateJob(
      @PathVariable("id") Long id, @Valid Job job, BindingResult result, Model model) {
    ApplicationData data = new ApplicationData();
    model.addAttribute("data", data);
    if (result.hasErrors()) {
      job.setId(id);

      return "business/job/update-job";
    }

    jobRepository.save(job);
    return "redirect:/business/job/index";
  }

  /// delete
  @GetMapping("/delete/{id}")
  public String deleteJob(@PathVariable("id") Long id, Model model) {
    Job job =
        jobRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    jobRepository.delete(job);
    return "redirect:/business/job/index";
  }
}
