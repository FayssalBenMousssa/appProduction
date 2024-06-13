package dev.fenix.application.production.visit;


import dev.fenix.application.app.model.ResponseData;
import dev.fenix.application.business.model.Staff;
import dev.fenix.application.production.visit.model.Visit;
import dev.fenix.application.production.visit.model.VisitAttachment;
import dev.fenix.application.production.visit.repository.VisitRepository;
import dev.fenix.application.production.visit.service.AttachmentVisitService;
import dev.fenix.application.production.visit.service.VisitService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;

@RestController()
@RequestMapping("/api/customer/visit")
public class VisitResource {
    private static final Logger log = LoggerFactory.getLogger(VisitResource.class);

    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VisitService visitService;

    @Autowired
    private AttachmentVisitService attachmentService;

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
    public ResponseEntity index(HttpServletRequest request,
                                @RequestParam(defaultValue = "0") Integer page,
                                @RequestParam(defaultValue = "200") Integer size,
                                @RequestParam(defaultValue = "id,desc") String[] sort,
                                @RequestParam(required = false) String[] query


    ) throws ParseException {


        JSONArray jArray = new JSONArray();

        Iterable<Visit> visits = visitService.getAllVisits(page, size, sort, query);
        for (Visit visit : visits) {
            jArray.put(visit.toJson());
        }
        JSONObject response = new JSONObject();
        try {
            response.put("results", jArray);
            response.put("count", jArray.length());
            response.put("total", visitService.getCountAll());
            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);


    }


    @RequestMapping(
            value = "/index/user",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String indexUser(HttpServletRequest request) {

        JSONArray jArray = new JSONArray();
        User user = this.getCurrentUser();
        Staff staff = user.getPerson().getStaffs().get(0);
        log.error(staff.getPerson().getFullName());
        Iterable<Visit> visits = visitRepository.findByActiveTrueAndStaff(staff);
        for (Visit visit : visits) {
            jArray.put(visit.toJson());
        }
        log.error(jArray.toString());
        return jArray.toString();
    }



    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("id") String id, @RequestParam(required = false) String attachmentType) throws Exception {
        VisitAttachment attachment;
        Visit visit = visitRepository.getOne(Long.valueOf(id));

        attachment = attachmentService.saveAttachment(file, visit, attachmentType);
        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/visit/download/").path(attachment.getId()).toUriString();

      //  if (  file.getContentType().equals("image/png") ) {
            visit.setVisitAttachment(attachment);
            visitRepository.save(visit);
       // }

        ResponseData response = new ResponseData(attachment.getFileName(), downloadURL, file.getContentType(), file.getSize(), attachment.getId());
        return ResponseEntity.ok(response);
    }




    @RequestMapping(
            value = "/save",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> save(@Valid @RequestBody Visit visit, HttpServletRequest request) {


        try {
            visit.setActive(true);
            Visit savedVisit = visitRepository.save(visit);
            return ResponseEntity.ok(savedVisit.toJson().toString());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(
            value = "/get/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> get(HttpServletRequest request, @PathVariable Long id)
            throws NotFoundException {
        Visit visit = visitRepository.findById(id).orElseThrow(() -> new NotFoundException("Product  not found"));
        return new ResponseEntity<>(visit.toJson().toString(), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/update",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> update(@Valid @RequestBody Visit visit, HttpServletRequest request) {
        try {
            visit.setActive(true);
            Visit updatedVisit = visitRepository.save(visit);
            return new ResponseEntity<>(updatedVisit.toJson().toString(), HttpStatus.OK);
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
            Visit visit = visitRepository.getOne(id);
            visit.setActive(false);
            Visit deletedVisit = visitRepository.save(visit);
            return ResponseEntity.ok(deletedVisit.toJson().toString());
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
