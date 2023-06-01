package dev.fenix.application.api.application;

import dev.fenix.application.app.model.Intervention;
import dev.fenix.application.app.model.Response;
import dev.fenix.application.app.repository.InterventionRepository;
import dev.fenix.application.security.model.User;
import dev.fenix.application.security.repository.UserRepository;
import javassist.NotFoundException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/api/application/intervention")
public class InterventionResource {
    private static final Logger log = LoggerFactory.getLogger(InterventionResource.class);

    @Autowired
    private InterventionRepository interventionRepository;
    @Autowired
    private UserRepository userRepository;

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
    public ResponseEntity index(
            HttpServletRequest request,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "200") Integer size,
            @RequestParam(defaultValue = "id,desc") String[] sort,
            @RequestParam(required = false) String[] query)
            throws InterruptedException {
        JSONArray jArray = new JSONArray();
        Pageable paging = PageRequest.of(page, size);
        Page pageData = interventionRepository.findAll(paging);
        List<Intervention> data = pageData.getContent();
        for (Intervention intervention : data) {
            jArray.put(intervention.toJson());
        }
        JSONObject response = new JSONObject();
        try {
            response.put("results", jArray);
            response.put("count", jArray.length());
            response.put("total", pageData.getTotalElements());
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
    public ResponseEntity<?> save(@Valid @RequestBody Intervention intervention, HttpServletRequest request) {


        try {
            intervention.setUser(this.getCurrentUser());
            Intervention savedIntervention = interventionRepository.save(intervention);
            return ResponseEntity.ok(savedIntervention.toJson().toString());
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
        Intervention intervention = interventionRepository.findById(id).orElseThrow(() -> new NotFoundException("Product  not found"));
        return new ResponseEntity<>(intervention.toJson().toString(), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/update",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> update(@Valid @RequestBody Intervention intervention, HttpServletRequest request) {
        try {
            Intervention localIntervention = interventionRepository.getOne(intervention.getId());
            for (Response response : intervention.getResponses()) {
                if (response.getUser() == null) {
                 log.info(response.toJson().toString());
                    response.setUser(this.getCurrentUser());
                    localIntervention.getResponses().add(response);
                }
            }
            log.info(localIntervention.toJson().toString());
           // localIntervention.setResponses(intervention.getResponses());
            localIntervention.setInterventionStatuses(intervention.getInterventionStatuses());

            Intervention updatedIntervention = interventionRepository.save(localIntervention);
            return new ResponseEntity<>(updatedIntervention.toJson().toString(), HttpStatus.OK);
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
            Intervention intervention = interventionRepository.getOne(id);
            interventionRepository.delete(intervention);
            return ResponseEntity.ok("true");
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
