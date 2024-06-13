package dev.fenix.application.production.treatment;

import dev.fenix.application.production.treatment.model.PrintModel;
import dev.fenix.application.production.treatment.repository.PrintModelRepository;
import dev.fenix.application.security.model.User;
import dev.fenix.application.security.repository.UserRepository;
import javassist.NotFoundException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;



@RestController()
@RequestMapping("/api/document/print_model")
public class PrintModelResource {
  private static final Logger log = LoggerFactory.getLogger(PrintModelResource.class);
  @Autowired
  private UserRepository userRepository;
  @Autowired private PrintModelRepository printModelRepository;

  @RequestMapping(
      value = {"/", ""},
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public String index() {
    return JSONObject.quote("Api :" + this.getClass().getSimpleName());
  }

  @RequestMapping(value = "/index", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public String index(HttpServletRequest request) {
    JSONArray jArray = new JSONArray();
    Iterable<PrintModel> printModels = printModelRepository.findAll();
    for (PrintModel printModel : printModels) {
      jArray.put(printModel.toJson());
    }
    return jArray.toString();
  }

  @RequestMapping(
      value = "/save",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> save(@Valid @RequestBody PrintModel printModel, HttpServletRequest request) {
    printModel.setUserAccount(this.getCurrentUser());
    PrintModel savedPrintModel = printModelRepository.save(printModel);
    return ResponseEntity.ok(savedPrintModel.toJson().toString());
  }

  @RequestMapping(
      value = "/get/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> get(HttpServletRequest request, @PathVariable Long id)
      throws NotFoundException {
    //log.trace("ProductResource.get method accessed");
    PrintModel printModel = printModelRepository.findById(id).orElseThrow(() -> new NotFoundException("Product  not found"));
    return new ResponseEntity<>(printModel.toJson().toString(), HttpStatus.OK);
  }

  @RequestMapping(
      value = "/update",
      method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> update(@Valid @RequestBody PrintModel printModel, HttpServletRequest request) {
    try {
      printModel.setUserAccount(this.getCurrentUser());
      PrintModel updatedPrintModel = printModelRepository.save(printModel);
      return new ResponseEntity<>(updatedPrintModel.toJson().toString(), HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(
      value = "/delete/{id}",
      method = RequestMethod.DELETE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {

    try {
      PrintModel printModel = printModelRepository.getOne(id);
      PrintModel deletedPrintModel = printModelRepository.save(printModel);
      return ResponseEntity.ok(deletedPrintModel.toJson().toString());
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  private User getCurrentUser() {
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = userDetails.getUsername();
    User user = userRepository.findOneByUserName(username);
    return user;
  }
}
