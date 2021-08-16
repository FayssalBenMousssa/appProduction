package dev.fenix.application.security;

import dev.fenix.application.app.model.Exception;
import dev.fenix.application.app.repository.AppExceptionRepository;
import dev.fenix.application.app.service.ApplicationData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("security/exception")
public class ExceptionController {
  private static final Logger log = LoggerFactory.getLogger(ExceptionController.class);

  @Autowired private AppExceptionRepository exceptionRepository;

  @GetMapping("/index")
  public String showExceptionList(@PageableDefault(size = 20) Pageable pageable, Model model) {
    ApplicationData data = new ApplicationData();
    model.addAttribute("data", data);
    Page<Exception> page = exceptionRepository.findAllByOrderByIdDesc(pageable);
    model.addAttribute("page", page);

    model.addAttribute("count", exceptionRepository.count());
    return "security/exception/index";
  }
}
