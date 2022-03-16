package dev.fenix.application.api.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.fenix.application.business.model.Job;
import dev.fenix.application.business.model.Staff;
import dev.fenix.application.business.repository.JobRepository;
import dev.fenix.application.business.repository.StaffRepository;
import dev.fenix.application.person.model.Gender;
import dev.fenix.application.person.model.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
class StaffResourceTest {
  private static final Logger log = LoggerFactory.getLogger(StaffResourceTest.class);
  @Autowired private MockMvc mockMvc;
  @Autowired private JobRepository jobRepository;
  @Autowired private StaffRepository staffRepository;
  @Autowired private WebApplicationContext context;
  ObjectMapper om = new ObjectMapper();

  @Before
  private void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  @WithMockUser(username = "fenix")
  public void httpRequestTest() throws Exception {
    this.mockMvc
        .perform(get("/api/staff/"))
        .andDo(print())
        .andDo(
            document(
                "{methodName}",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
        .andExpect(status().isOk());
  }

  @Test
  @Order(3)
  @WithMockUser(
      username = "fenix",
      roles = {"USER", "ADMIN"})
  void indexStaff() throws Exception {

    this.mockMvc
        .perform(get("/api/staff/index"))
        .andDo(print())
        .andDo(
            document(
                "{methodName}",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
        .andExpect(status().isOk());
  }

  @Test
  @Order(5)
  @WithMockUser(
      username = "fenix",
      roles = {"USER", "ADMIN"})
  void indexStaffWithParams() throws Exception {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("page", "0");

    params.add("page", "0");
    params.add("size", "2");
    params.add("sort", "id,desc");
    params.add("query", "person_fullName:search");

    this.mockMvc
        .perform(get("/api/staff/index").params(params))
        .andDo(print())
        .andDo(
            document(
                "{methodName}",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                pathParameters()))
        .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
    ;
  }

  @Test
  @Order(3)
  @WithMockUser(username = "fenix")
  void getStaff() throws Exception {
    Staff requestStaff = staffRepository.findTopByOrderByIdDesc();
    MvcResult result =
        this.mockMvc
            .perform(get("/api/staff/get/" + requestStaff.getId()))
            .andDo(print())
            .andDo(
                document(
                    "{methodName}",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint())))
            .andExpect(status().isOk())
            .andReturn();
    String resultContent = result.getResponse().getContentAsString();
    Staff expectedStaff = om.readValue(resultContent, Staff.class);
    Assert.assertTrue(expectedStaff.getId() != null);

    log.info(String.valueOf(expectedStaff.getId()));
    log.info(String.valueOf(requestStaff.getId()));

    Assert.assertTrue(expectedStaff.getId().equals(requestStaff.getId()));
  }

  @Test
  @WithMockUser(username = "fenix")
  @Order(1)
  void saveStaff() throws Exception {
    Staff requestStaff = new Staff();
    Person person = new Person();
    person.setFirstName("FirstName Test saveStaff" + this.getRandom(0, 1000));
    person.setLastName("LastName Test saveStaff" + this.getRandom(0, 1000));
    person.setGender(Gender.MALE);

    String birthDate = "1985-03-17";
    String startDate = "2000-03-17";
    String endDate = "2022-03-17";

    person.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(birthDate));
    requestStaff.setPerson(person);
    Job job = jobRepository.findTopByOrderByIdDesc();
    requestStaff.setJob(job);
    requestStaff.setActive(true);
    requestStaff.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(startDate));
    requestStaff.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
    String jsonRequest = om.writeValueAsString(requestStaff);
    log.info("{methodName}");
    MvcResult result =
        mockMvc
            .perform(
                post("/api/staff/save")
                    .content(jsonRequest)
                    .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andDo(print())
            .andDo(
                document(
                    "{methodName}",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint())))
            .andExpect(status().isOk())
            .andReturn();
    String resultContent = result.getResponse().getContentAsString();
    Staff expectedStaff = om.readValue(resultContent, Staff.class);
    Assert.assertTrue(expectedStaff.getId() != null);
    Assert.assertTrue(
        requestStaff.getPerson().getFullName().equals(expectedStaff.getPerson().getFullName()));
  }

  @Test
  @WithMockUser(username = "fenix")
  @Order(2)
  void updateStaff() throws Exception {
    Staff requestStaff = staffRepository.findTopByOrderByIdDesc();

    requestStaff.setEndDate(new Date());
    requestStaff.getPerson().setLastName("updateStaff LastName");
    requestStaff.getPerson().setFirstName("search");

    String jsonRequest = om.writeValueAsString(requestStaff);
    MvcResult result =
        mockMvc
            .perform(
                put("/api/staff/update")
                    .content(jsonRequest)
                    .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andDo(print())
            .andDo(
                document(
                    "{methodName}",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint())))
            .andExpect(status().isOk())
            .andReturn();
    String resultContent = result.getResponse().getContentAsString();
    Staff expectedStaff = om.readValue(resultContent, Staff.class);
    Assert.assertTrue(expectedStaff.getPerson().getLastName().contains("updateStaff LastName"));
  }

  @Test
  @WithMockUser(username = "fenix")
  @Order(4)
  void deleteStaff() throws Exception {
    Staff requestStaff = staffRepository.findTopByOrderByIdDesc();
    requestStaff.setActive(false);
    String jsonRequest = om.writeValueAsString(requestStaff);
    MvcResult result =
        mockMvc
            .perform(
                delete("/api/staff/delete/" + requestStaff.getId())
                    .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andDo(print())
            .andDo(
                document(
                    "{methodName}",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint())))
            .andExpect(status().isOk())
            .andReturn();
    String resultContent = result.getResponse().getContentAsString();
    Staff expectedStaff = om.readValue(resultContent, Staff.class);
    Assert.assertTrue(expectedStaff.getActive().equals(false));
  }

  private int getRandom(int min, int max) {
    int random_int = (int) Math.floor(Math.random() * (max - min + 1));
    return random_int;
  }
}
