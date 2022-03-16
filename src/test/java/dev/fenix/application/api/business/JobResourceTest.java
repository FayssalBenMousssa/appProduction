package dev.fenix.application.api.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.fenix.application.business.model.Job;
import dev.fenix.application.business.repository.JobRepository;
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
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
class JobResourceTest {
  private static final Logger log = LoggerFactory.getLogger(JobResourceTest.class);
  @Autowired private MockMvc mockMvc;
  @Autowired private JobRepository jobRepository;
  @Autowired private WebApplicationContext context;
  ObjectMapper om = new ObjectMapper();

  @Before
  private void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  @WithMockUser(username = "fenix", roles = {"USER", "ADMIN"})
  @Order(3)
  void indexJob() throws Exception {

    this.mockMvc
        .perform(get("/api/staff/job/index"))
        .andDo(print()).andDo(
                    document(
                            "{methodName}",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint())))
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser(username = "fenix")
  @Order(1)
  void saveJob() throws Exception {
    Job requestJob = new Job();
    requestJob.setName("saveJob Test " + this.getRandom(0, 1000));
    requestJob.setActive(true);

    String jsonRequest = om.writeValueAsString(requestJob);
    log.info("{methodName}");
    MvcResult result =
        mockMvc
            .perform(
                post("/api/staff/job/save")
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
    Job expectedJob = om.readValue(resultContent, Job.class);
    Assert.assertTrue(expectedJob.getId() != null);
    Assert.assertTrue(expectedJob.getName().equals(expectedJob.getName()));

  }

  @Test
  @WithMockUser(username = "fenix")
  @Order(2)
  void updateJob() throws Exception {
    Job requestJob = jobRepository.findTopByOrderByIdDesc();
    requestJob.setName(requestJob.getName() + " Updated Job Test");
    String jsonRequest = om.writeValueAsString(requestJob);
    MvcResult result =
        mockMvc.perform(
                put("/api/staff/job/update")
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
    Job expectedJob = om.readValue(resultContent, Job.class);
    Assert.assertTrue(expectedJob.getName().contains("Updated"));
  }

  @Test
  @WithMockUser(username = "fenix")
  @Order(4)
  void deleteJob() throws Exception{
    Job requestJob = jobRepository.findTopByOrderByIdDesc();
    requestJob.setActive(false);
    String jsonRequest = om.writeValueAsString(requestJob);
    MvcResult result =
            mockMvc.perform(
                    delete("/api/staff/job/delete/" + requestJob.getId() )

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
    Job expectedJob = om.readValue(resultContent, Job.class);
    Assert.assertTrue(expectedJob.getActive().equals(false));

  }

  private int getRandom(int min, int max) {
    int random_int = (int) Math.floor(Math.random() * (max - min + 1));
    return random_int;
  }
}
