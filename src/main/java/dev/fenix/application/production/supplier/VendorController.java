package dev.fenix.application.production.supplier;

import dev.fenix.application.person.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
