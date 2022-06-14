package dev.fenix.application.api.production.product;

import dev.fenix.application.production.product.model.Formula;
import dev.fenix.application.production.product.repository.FormulaRepository;
import dev.fenix.application.production.product.service.FormulaService;
import javassist.NotFoundException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController()
@RequestMapping("/api/product/formula")
public class FormulaResource {
  private static final Logger log = LoggerFactory.getLogger(FormulaResource.class);

  @Autowired private FormulaRepository formulaRepository;
  @Autowired private FormulaService formulaService;

  @RequestMapping(
      value = {"/", ""},
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public String index() {
    return JSONObject.quote("Api :" + this.getClass().getSimpleName());
  }

  @RequestMapping(
      value = "/index",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> index(
      HttpServletRequest request,
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "200") Integer size,
      @RequestParam(defaultValue = "name,asc") String[] sort,
      @RequestParam(required = false) String[] query) {

    JSONArray jArray = new JSONArray();
    log.trace("PackagingResource.index method accessed");

    Iterable<Formula> formulas = formulaService.getAllFormula(page, size, sort, query);
    for (Formula formula : formulas) {
      jArray.put(formula.toJson());
    }

    JSONObject response = new JSONObject();

    try {
      response.put("results", jArray);
      response.put("count", jArray.length());
      response.put("total", formulaService.getCountAll());
      return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
  }

  @RequestMapping(
      value = "/save",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> save(@Valid @RequestBody Formula formula, HttpServletRequest request) {
    formula.setActive(true);
    Formula savedFormula = formulaRepository.save(formula);

    /*  if (task.getAssignedTo() == null) {
      throw new RuntimeException("AssignedTo is mandatory");
    }*/

    return ResponseEntity.ok(savedFormula.toJson().toString());
  }

  @RequestMapping(
      value = "/get/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> get(HttpServletRequest request, @PathVariable Long id)
      throws NotFoundException {
    log.trace("ProductResource.get method accessed");
    Formula formula =
        formulaRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Product  not found"));
    return new ResponseEntity<>(formula.toJson().toString(), HttpStatus.OK);
  }

  @RequestMapping(
      value = "/update",
      method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> update(@Valid @RequestBody Formula formula, HttpServletRequest request) {
    try {
      formula.setActive(true);
      Formula updatedFormula = formulaRepository.save(formula);
      return new ResponseEntity<>(updatedFormula.toJson().toString(), HttpStatus.OK);
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
      Formula formula = formulaRepository.getOne(id);
      formula.setActive(false);
      Formula savedProductUnit = formulaRepository.save(formula);
      return ResponseEntity.ok(savedProductUnit.toJson().toString());
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}
