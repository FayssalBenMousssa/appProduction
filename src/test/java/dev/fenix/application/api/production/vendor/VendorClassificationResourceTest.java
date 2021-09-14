package dev.fenix.application.api.production.vendor;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.fenix.application.production.vendor.model.VendorClassification;
import dev.fenix.application.production.vendor.repository.VendorClassificationRepository;
import org.junit.Assert;
import org.junit.Before;
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
class VendorClassificationResourceTest {
  private static final Logger log = LoggerFactory.getLogger(VendorClassificationResourceTest.class);
  @Autowired private MockMvc mockMvc;
  @Autowired private VendorClassificationRepository vendorClassificationRepository;
  @Autowired private WebApplicationContext context;
  ObjectMapper om = new ObjectMapper();

  @Before
  private void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  @WithMockUser(
      username = "fenix",
      roles = {"USER", "ADMIN"})
  void index() throws Exception {

    this.mockMvc
        .perform(get("/api/vendor/classification/index"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser(username = "fenix")
  void save() throws Exception {
    VendorClassification requestClassification = new VendorClassification();
    requestClassification.setName("Name " + this.getRandom(0, 1000));
    requestClassification.setActive(true);
    requestClassification.setCode(String.valueOf(this.getRandom(0, 1000)));
    String jsonRequest = om.writeValueAsString(requestClassification);
    log.info("{methodName}");
    // String jsonRequest = requestClassification.toJson().toString();
    MvcResult result =
        mockMvc
            .perform(
                post("/api/vendor/classification/save")
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
    VendorClassification expectedClassification =
        om.readValue(resultContent, VendorClassification.class);
    Assert.assertTrue(expectedClassification.getId() != null);
    Assert.assertTrue(expectedClassification.getName().equals(requestClassification.getName()));
  }

  @Test
  @WithMockUser(username = "fenix")
  void update() throws Exception {
    VendorClassification requestClassification =
        vendorClassificationRepository.findTopByOrderByIdDesc();
    requestClassification.setName(requestClassification.getName() + "Updated");
    String jsonRequest = om.writeValueAsString(requestClassification);
    MvcResult result =
        mockMvc
            .perform(
                put("/api/vendor/classification/update")
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
    VendorClassification expectedClassification =
        om.readValue(resultContent, VendorClassification.class);
    // Assert.assertTrue(expectedClassification.getId()== requestClassification.getId());

    Assert.assertTrue(expectedClassification.getName().contains("Updated"));
  }

  @Test
  void delete() {}

  private int getRandom(int min, int max) {
    int random_int = (int) Math.floor(Math.random() * (max - min + 1));
    return random_int;
  }
}
