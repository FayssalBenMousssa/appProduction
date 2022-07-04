package dev.fenix.application.api.production.treatment;

import dev.fenix.application.business.repository.CompanyRepository;
import dev.fenix.application.production.treatment.model.Action;
import dev.fenix.application.production.treatment.model.Document;
import dev.fenix.application.production.treatment.model.DocumentLog;
import dev.fenix.application.production.treatment.model.Type;
import dev.fenix.application.production.treatment.repository.DocumentLogRepository;
import dev.fenix.application.production.treatment.repository.DocumentRepository;
import dev.fenix.application.production.treatment.repository.TypeRepository;
import dev.fenix.application.production.treatment.service.DocumentService;
import dev.fenix.application.security.model.User;
import dev.fenix.application.security.repository.UserRepository;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/api/document")
public class DocumentResource {
  private static final Logger log = LoggerFactory.getLogger(DocumentResource.class);

  @Autowired private DocumentRepository documentRepository;
  @Autowired private TypeRepository documentTypeRepository;
  @Autowired private DocumentService documentService;
  @Autowired private UserRepository userRepository;
  @Autowired private CompanyRepository companyRepository;
  @Autowired private DocumentLogRepository documentLogRepository;

  @RequestMapping(
      value = {"/", ""},
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public String index() {
    log.trace("DocumentResource.index/ method accessed");
    return JSONObject.quote("Api :" + this.getClass().getSimpleName());
  }

  @RequestMapping(
      value = "/index",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> index(
      HttpServletRequest request,
      @RequestParam(required = false) Long type,
      @RequestParam(required = false) Long category,
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "200") Integer size,
      @RequestParam(defaultValue = "id,desc") String[] sort,
      @RequestParam(required = false) String[] query)
      throws InterruptedException {

    log.trace("DocumentResource.index method accessed");
    long startTime = System.nanoTime();
    JSONArray jArray = new JSONArray();
    List<Document> documents =
        documentService.getAllDocuments(page, size, sort, query, type, category);
    long queryTime = System.nanoTime();
    log.info("queryTime : " + String.valueOf((queryTime - startTime) / 1000000));
    for (Document document : documents) {
      jArray.put(document.toSmallJson());
    }
    JSONObject response = new JSONObject();
    long endTime = System.nanoTime();
    long duration = (endTime - startTime);
    log.info("durationTime : " + String.valueOf((duration) / 1000000));

    try {
      response.put("results", jArray);
      response.put("total_type", documentService.getCount());
      response.put("count", jArray.length());
      response.put("total", documentService.getCountAll());
      response.put("duration", (endTime - startTime));
      return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    } catch (JSONException e) {
      e.printStackTrace();
    }

    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
  }

  @RequestMapping(
      value = "/get/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> get(HttpServletRequest request, @PathVariable Long id)
      throws NotFoundException {
    log.trace("DocumentResource.get method accessed");
    Document document =
        documentRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Document  not found"));
    return new ResponseEntity<>(document.toJson().toString(), HttpStatus.OK);
  }

  @RequestMapping(
      value = "/get/logs/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> getTrace(HttpServletRequest request, @PathVariable Long id)
      throws NotFoundException {
    List<DocumentLog> documentLogs = documentLogRepository.findByDocumentId(id);

    JSONArray jArray = new JSONArray();

    for (DocumentLog documentLog : documentLogs) {
      jArray.put(documentLog.toJson());
    }
    JSONObject response = new JSONObject();
    try {
      response.put("results", jArray);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return new ResponseEntity<>(response.toString(), HttpStatus.OK);
  }

  @RequestMapping(
      value = "/save",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> save(@Valid @RequestBody Document document, HttpServletRequest request) {
    log.trace("DocumentResource.save method accessed");
    try {
      document.setName(document.getType().getName() + " " + this.getNewCode(document.getType()));
      document.setCode(this.getNewCode(document.getType()));
      document.setActive(true);
      document.setLogs(getDocumentLogs(document, Action.ADD));
      Document savedDocument = documentRepository.save(document);
      return new ResponseEntity<>(savedDocument.toJson().toString(), HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(
      value = "/update",
      method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> update(
      @Valid @RequestBody Document document, HttpServletRequest request) {
    log.trace("DocumentResource.update method accessed");
    try {

      document.setLogs(getDocumentLogs(document, Action.EDIT));
      document.setActive(true);

      if (document.getRelated() != null) {
        for (Document documentRelated : document.getRelated()) {
          if (documentRelated.getCode() == null) {
            documentRelated.setCode(this.getNewCode(documentRelated.getType()));
          }
        }
      }

      Document updatedDocument = documentRepository.save(document);
      return new ResponseEntity<>(updatedDocument.toJson().toString(), HttpStatus.OK);
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
    log.trace("DocumentResource.delete method accessed");
    Document document = documentRepository.getOne(id);
    try {

      document.setLogs(getDocumentLogs(document, Action.DELETE));
      document.setActive(false);
      Document savedDocument = documentRepository.save(document);
      return ResponseEntity.ok(savedDocument.toJson().toString());
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  private String getNewCode(Type type) {
    int count = documentRepository.countByType(type);
    return type.getAbbreviation() + "." + String.format("%07d", count + 1);
  }

  private List<DocumentLog> getDocumentLogs(Document document, Action action) {

    List<DocumentLog> logs = new ArrayList();
    if(action == Action.DELETE ||  action == Action.EDIT) {
      Document oldDocument = documentRepository.getOne(document.getId());
      logs = oldDocument.getLogs();
      final StringBuilder builder = new StringBuilder();
      document.getDifferent(oldDocument).forEach((val)->{builder.append(val + ","); });
      String messages = builder.toString();
      DocumentLog documentLog = new DocumentLog(action, document, messages, this.getCurrentUser().getUserName());
      logs.add(documentLog);
    }else if(action == Action.ADD ) {
      DocumentLog documentLog = new DocumentLog(action, document, null,this.getCurrentUser().getUserName());
      logs.add(documentLog);
    }

    return logs;
  }

  private User getCurrentUser() {
    UserDetails userDetails =
        (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = userDetails.getUsername();
    User user = userRepository.findOneByUserName(username);
    return user;
  }
}
