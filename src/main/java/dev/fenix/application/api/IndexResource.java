package dev.fenix.application.api;

import dev.fenix.application.Application;
import dev.fenix.application.api.models.AuthenticationRequest;
import dev.fenix.application.api.models.AuthenticationResponse;
import dev.fenix.application.api.util.JwtUtil;
import dev.fenix.application.person.repository.PersonRepository;
import dev.fenix.application.security.repository.UserRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api")
public class IndexResource {
  private static final Logger log = LoggerFactory.getLogger(IndexResource.class);
  @Autowired private AuthenticationManager authenticationManager;
  @Autowired private JwtUtil jwtTokenUtil;
  @Autowired private PersonRepository personRepository;

  @Autowired private UserRepository userRepository;
  @Autowired private UserDetailsService userDetailsService;


  @RequestMapping(
      value = "/",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public String index() {

    userRepository.count();

    return JSONObject.quote( "Users : " +  userRepository.count());
  }




  @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
  public ResponseEntity<?> createAuthenticationToken(
      @RequestBody AuthenticationRequest authenticationRequest) throws Exception {

    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              authenticationRequest.getUsername(), authenticationRequest.getPassword()));
    } catch (BadCredentialsException e) {
      throw new Exception("Incorrect username or password", e);
    }
    final UserDetails userDetails =
        userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    final String jwt = jwtTokenUtil.generateToken(userDetails);
    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }
}
