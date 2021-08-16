package dev.fenix.application.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class loginController {

  @RequestMapping(
      value = "login",
      method = {RequestMethod.GET, RequestMethod.POST})
  public String login() {

    return "security/login";
  }
}
