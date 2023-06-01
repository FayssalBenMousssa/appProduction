package dev.fenix.application.api.security;

import dev.fenix.application.api.security.models.AuthenticationRequest;
import dev.fenix.application.api.security.models.AuthenticationResponse;
import dev.fenix.application.api.security.util.JwtUtil;
import dev.fenix.application.person.repository.PersonRepository;
import dev.fenix.application.security.repository.UserRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

@RestController()
@RequestMapping("/api")
public class IndexResource {
  private static final Logger log = LoggerFactory.getLogger(IndexResource.class);
  @Autowired private AuthenticationManager authenticationManager;
  @Autowired private JwtUtil jwtTokenUtil;
  @Autowired private PersonRepository personRepository;
  @Autowired private UserRepository userRepository;
  @Autowired private UserDetailsService userDetailsService;
  @Autowired private Environment env;

  @RequestMapping(
      value = "/",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> index() throws JSONException {
    JSONObject jObject = new JSONObject();

    jObject.put("api", 1);


    return ResponseEntity.ok(jObject.toString());
  }

  @RequestMapping(
          value = "/info",
          method = RequestMethod.GET,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> info() throws JSONException {
    JSONObject jObject = new JSONObject();
    String version = env.getProperty("app.version");
    String databaseName = env.getProperty("DATABASE_NAME");
    String databaseHost = env.getProperty("DATABASE_HOST");
    jObject.put("version_backend", version);
    jObject.put("databaseHost", databaseHost);
    jObject.put("databaseName", databaseName);

    File root = new File("/");
    jObject.put("Total space", String.format("%.2f GB", (double)root.getTotalSpace() /1073741824));
    jObject.put("Free space", String.format("%.2f GB",  (double)root.getFreeSpace() /1073741824));
    jObject.put("Usable space", String.format("%.2f GB",   (double)root.getUsableSpace() /1073741824));
    MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

    jObject.put("Initial memory", String.format("%.2f GB", (double)memoryMXBean.getHeapMemoryUsage().getInit() /1073741824));
    jObject.put("Used heap memory", String.format("%.2f GB",(double)memoryMXBean.getHeapMemoryUsage().getUsed() /1073741824));
    jObject.put("Max heap memory", String.format("%.2f GB", (double)memoryMXBean.getHeapMemoryUsage().getMax() /1073741824));
    jObject.put("Committed memory", String.format("%.2f GB", (double)memoryMXBean.getHeapMemoryUsage().getCommitted() /1073741824));

    return ResponseEntity.ok(jObject.toString());
  }

  @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
  public ResponseEntity<?> createAuthenticationToken(
      @RequestBody AuthenticationRequest authenticationRequest,

      HttpServletRequest request)
      throws Exception {

    System.out.println(request);

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



  @RequestMapping(
          value = "/database",
          method = RequestMethod.GET,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> dataBase() throws JSONException {
    JSONObject jObject = new JSONObject();

    jObject.put("business.company", "business.company");
    jObject.put("business.companyType", "databaseHost");
    jObject.put("databaseName", "databaseName");
    return ResponseEntity.ok(jObject.toString());
  }
}
