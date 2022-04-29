package dev.fenix.application.api.treatment;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.fenix.application.business.model.CompanyType;
import dev.fenix.application.core.model.MetaData;
import dev.fenix.application.production.treatment.model.Category;
import dev.fenix.application.production.treatment.model.Type;
import dev.fenix.application.production.treatment.repository.CategoryRepository;
import dev.fenix.application.production.treatment.repository.TypeRepository;
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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
class TypeResourceTest {
  private static final Logger log = LoggerFactory.getLogger(TypeResourceTest.class);
  @Autowired private MockMvc mockMvc;
  @Autowired private TypeRepository typeRepository;
  @Autowired private CategoryRepository categoryRepository;
  @Autowired private WebApplicationContext context;
  ObjectMapper om = new ObjectMapper();

  @Before
  private void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  @WithMockUser(username = "fenix", roles = {"USER", "ADMIN"})
  @Order(3)
  void indexType() throws Exception {

    this.mockMvc
        .perform(get("/api/document/type/index"))
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
  void saveType() throws Exception {

    Category category = categoryRepository.findTopByOrderByIdDesc();
    Type requestType = new Type();
    requestType.setCategory(category);
    requestType.setName("category Test - " + this.getRandom(0, 1000));
    requestType.setAbbreviation("BL");
    requestType.setColor("#0000000");
    requestType.setCode("xxxxx");

    requestType.setSource(CompanyType.CUSTOMER);
    requestType.setDestination(CompanyType.OUR);

    requestType.setActive(true);


    List<MetaData> metas = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      MetaData meta = new MetaData();
      meta.setCode("code" + i);
      meta.setActive(true);
      meta.setPosition("head");
      meta.setName("Name" + + i);
      meta.setRequired(true);
      meta.setType("text");
      metas.add(meta);
    }


    requestType.setMetaData(metas);


    String jsonRequest = om.writeValueAsString(requestType);
    log.info("{methodName}");
    MvcResult result =
        mockMvc
            .perform(
                post("/api/document/type/save")
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
    Type expectedType = om.readValue(resultContent, Type.class);
    Assert.assertTrue(expectedType.getId() != null);
    Assert.assertTrue(expectedType.getName().equals(expectedType.getName()));

  }

  @Test
  @WithMockUser(username = "fenix")
  @Order(2)
  void updateType() throws Exception {
    Type requestType = typeRepository.findTopByOrderByIdDesc();
    requestType.setName(requestType.getName() + " Updated Type Test");


    MetaData meta = new MetaData();
    meta.setCode("code up");
    meta.setActive(true);
    meta.setPosition("head");
    meta.setName("Name up + ");
    meta.setRequired(true);
    meta.setType("text");

    requestType.getMetaData().add(meta);

    String jsonRequest = om.writeValueAsString(requestType);
    MvcResult result =
        mockMvc.perform(
                put("/api/document/type/update")
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
    Type expectedType = om.readValue(resultContent, Type.class);
    Assert.assertTrue(expectedType.getName().contains("Updated"));
  }



  @Test
  @WithMockUser(username = "fenix")
  @Order(4)
  void deleteType() throws Exception {
    Type requestType = typeRepository.findTopByOrderByIdDesc();
    requestType.setActive(false);
    String jsonRequest = om.writeValueAsString(requestType);
    MvcResult result =
            mockMvc
                    .perform(
                            delete("/api/document/type/delete/" + requestType.getId())
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
    Type expectedType = om.readValue(resultContent, Type.class);
    Assert.assertFalse(expectedType.isActive());
  }

  private int getRandom(int min, int max) {
    int random_int = (int) Math.floor(Math.random() * (max - min + 1));
    return random_int;
  }
}
