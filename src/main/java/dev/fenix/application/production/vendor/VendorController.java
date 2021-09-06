package dev.fenix.application.production.vendor;

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
@RequestMapping("/vendor")
public class VendorController {

  private static final Logger log = LoggerFactory.getLogger(VendorController.class);
  @Autowired private PersonRepository personRepository;
  @GetMapping("/")
  public  String hi(){
    return "hello ";
  }


}
