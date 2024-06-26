package dev.fenix.application.production.customer;


import dev.fenix.application.business.model.Staff;
import dev.fenix.application.business.repository.StaffRepository;
import dev.fenix.application.production.customer.model.Customer;
import dev.fenix.application.production.customer.repository.CustomerRepository;
import dev.fenix.application.production.customer.service.CustomerService;
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
import java.util.Date;

@RestController()
@RequestMapping("/api/customer")


public class CustomerResource {
    private static final Logger log = LoggerFactory.getLogger(CustomerResource.class);


    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final UserRepository userRepository;
    private final StaffRepository staffRepository;

    @Autowired
    public CustomerResource(CustomerRepository customerRepository, CustomerService customerService, UserRepository userRepository, StaffRepository staffRepository) {
        this.customerRepository = customerRepository;
        this.customerService = customerService;
        this.userRepository = userRepository;
        this.staffRepository = staffRepository;
    }


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
            @RequestParam(required = false) Long type,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "200") Integer size,
            @RequestParam(defaultValue = "id,desc") String[] sort,
            @RequestParam(required = false) String[] query) throws InterruptedException {
        JSONArray jArray = new JSONArray();


        Iterable<Customer> customers = customerService.getAllCustomers(page, size, sort, query);
        for (Customer customer : customers) {
            jArray.put(customer.toJson());
        }
        JSONObject response = new JSONObject();
        try {
            response.put("results", jArray);
            response.put("count", jArray.length());
            response.put("total", customerService.getCountAll());
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
    public ResponseEntity<String> indexUser(
            HttpServletRequest request,
            @RequestParam(required = false) Long type,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "1000") Integer size,
            @RequestParam(defaultValue = "id,desc") String[] sort,
            @RequestParam(defaultValue = "no_version") String version,
            @RequestParam(defaultValue = "no_device_id") String deviceId,
            @RequestParam(required = false) String[] query) throws InterruptedException {
        JSONArray jArray = new JSONArray();
        // Iterable<Customer> customers = customerService.getAllCustomers(page, size, sort, query);
        User user = this.getCurrentUser();
        Staff staff = user.getPerson().getStaffs().get(0);
        Pageable paging = PageRequest.of(page, size);


        Page<Customer> customerPage = customerRepository.findByCustomerStaff_Staff_IdAndCustomerStaff_StartDateBeforeAndCustomerStaff_EndDateNullOrCustomerStaff_EndDateAfter(staff.getId(), new Date(), new Date(), paging);

        for (Customer customer : customerPage) {
            jArray.put(customer.toJson());
        }



        log.info(user.getUserName() + ": version " + version);
        log.info(user.getUserName() + ": has  " + jArray.length() + " customers");
        log.info(user.getUserName() + ": deviceId " + deviceId );



        JSONObject response = new JSONObject();
        try {
            response.put("results", jArray);
            response.put("count", jArray.length());
            response.put("total", customerPage.getTotalElements());
            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(
            value = "/index/staff",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> indexStaff(
            HttpServletRequest request,
            @RequestParam(required = true) Long staff_id, // Updated to required = true
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "1000") Integer size,
            @RequestParam(defaultValue = "id,desc") String[] sort,
            @RequestParam(required = false) String[] query) throws NotFoundException {
        Staff staff = staffRepository.findById(staff_id).orElseThrow(() -> new NotFoundException("staff not found"));
        Pageable paging = PageRequest.of(page, size);
        JSONArray jArray = new JSONArray();
        Page<Customer> customerPage = customerRepository.findByCustomerStaff_Staff_IdAndCustomerStaff_StartDateBeforeAndCustomerStaff_EndDateNullOrCustomerStaff_EndDateAfter(staff.getId(), new Date(), new Date(), paging);

        for (Customer customer : customerPage) {
            System.out.println(customer.getSocialReason());

            jArray.put(customer.toJson());
        }
        JSONObject response = new JSONObject();
        try {
            response.put("results", jArray);
            response.put("count", jArray.length());
            response.put("total", customerPage.getTotalElements());
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
    public ResponseEntity<?> save(@Valid @RequestBody Customer customer, HttpServletRequest request) {


        customer.setActive(true);
        Customer savedCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(savedCustomer.toJson().toString());
    }

    @RequestMapping(
            value = "/update",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> update(
            @Valid @RequestBody Customer customer, HttpServletRequest request) throws Exception {


        try {
            customer.setActive(true);
            Customer updatedCustomer = customerRepository.save(customer);
            return new ResponseEntity<>(updatedCustomer.toJson().toString(), HttpStatus.OK);
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
        //log.trace(

        Customer customer =
                customerRepository
                        .findById(id)
                        .orElseThrow(() -> new NotFoundException("Customer not found with id: " + id));
        return new ResponseEntity<>(customer.toJson().toString(), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/delete/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        //log.trace(String.format("%s method accessed", new Object() {}.getClass().getEnclosingMethod().getName()));
        Customer customer = customerRepository.getOne(id);
        try {
            customer.setActive(false);
            Customer deletedCustomer = customerRepository.save(customer);
            return new ResponseEntity<>(deletedCustomer.toJson().toString(), HttpStatus.OK);
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
