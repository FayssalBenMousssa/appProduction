package dev.fenix.application.api.production.product;

import dev.fenix.application.Application;
import dev.fenix.application.api.util.JwtUtil;
import dev.fenix.application.person.repository.PersonRepository;
import dev.fenix.application.security.repository.UserRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/product/packaging")
public class PackagingResource {
  private static final Logger log = LoggerFactory.getLogger(Application.class);


  @RequestMapping(
      value = "/",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public String index() {
    return JSONObject.quote("Api");
  }


}
