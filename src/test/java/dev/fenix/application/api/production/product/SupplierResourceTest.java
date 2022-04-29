package dev.fenix.application.api.production.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.fenix.application.production.supplier.model.Supplier;
import dev.fenix.application.production.supplier.model.SupplierClassification;
import dev.fenix.application.production.supplier.repository.SupplierClassificationRepository;
import dev.fenix.application.production.supplier.repository.SupplierRepository;
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
class SupplierResourceTest {
  private static final Logger log = LoggerFactory.getLogger(SupplierResourceTest.class);
  @Autowired private MockMvc mockMvc;

  @Autowired private SupplierRepository supplierRepository;
  @Autowired private SupplierClassificationRepository supplierClassificationRepository;
  @Autowired private WebApplicationContext context;


  ObjectMapper om = new ObjectMapper();

  @Before
  private void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  @WithMockUser(username = "fenix")
  public void httpSupplierRequestTest() throws Exception {
    this.mockMvc
        .perform(get("/api/supplier"))
        .andDo(print())
        .andDo(document(
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
  void indexSupplier() throws Exception {

    this.mockMvc
        .perform(get("/api/supplier/index"))
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
  @WithMockUser(username = "fenix", roles = {"USER", "ADMIN"})
  void indexSupplierWithParams() throws Exception {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("page", "0");
    params.add("page", "0");
    params.add("size", "2");
    params.add("sort", "id,desc");

    this.mockMvc
        .perform(get("/api/supplier/index").params(params))
        .andDo(print())
        .andDo(
            document(
                "{methodName}",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                pathParameters()))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json;charset=UTF-8"));
  }

  @Test
  @Order(3)
  @WithMockUser(username = "fenix")
  void getSupplier() throws Exception {
    Supplier requestSupplier = supplierRepository.findTopByOrderByIdDesc();
    MvcResult result =
        this.mockMvc
            .perform(get("/api/supplier/get/" + requestSupplier.getId()))
            .andDo(print())
            .andDo(
                document(
                    "{methodName}",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint())))
            .andExpect(status().isOk())
            .andReturn();
    String resultContent = result.getResponse().getContentAsString();
    Supplier expectedSupplier = om.readValue(resultContent, Supplier.class);
    Assert.assertTrue(expectedSupplier.getId() != null);

    log.info(String.valueOf(expectedSupplier.getId()));
    log.info(String.valueOf(requestSupplier.getId()));

    Assert.assertTrue(expectedSupplier.getId().equals(requestSupplier.getId()));
  }

  @Test
  @WithMockUser(username = "fenix")
  @Order(1)
  void saveSupplier() throws Exception {
    Supplier requestSupplier = new Supplier();
    int random =   this.getRandom(1,999);
    requestSupplier.setCode("CODE" + random);
    requestSupplier.setSocialReason("Social Reason" + random);
    requestSupplier.setActive(true);
    requestSupplier.setEmail("requestSupplier@email.com" + random);
    requestSupplier.setIce("000000000" + random);
    requestSupplier.setTelephone("000000000" + random);
    SupplierClassification classification =   supplierClassificationRepository.findOneById(1L);
    requestSupplier.setClassification(classification);
    String jsonRequest = om.writeValueAsString(requestSupplier);
    MvcResult result =
        mockMvc
            .perform(
                post("/api/supplier/save")
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
    Supplier expectedSupplier = om.readValue(resultContent, Supplier.class);
    Assert.assertTrue(expectedSupplier.getId() != null);
    Assert.assertTrue(requestSupplier.getCode().equals(expectedSupplier.getCode()));
  }

  @Test
  @WithMockUser(username = "fenix")
  @Order(2)
  void updateSupplier() throws Exception {

    Supplier requestSupplier = supplierRepository.findTopByOrderByIdDesc();
    requestSupplier.setNote("updated");
    String jsonRequest = om.writeValueAsString(requestSupplier);
    MvcResult result =
        mockMvc
            .perform(
                put("/api/supplier/update")
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
    Supplier expectedSupplier = om.readValue(resultContent, Supplier.class);
    Assert.assertTrue(expectedSupplier.getNote().contains("updated"));
  }

  @Test
  @WithMockUser(username = "fenix")
  @Order(4)
  void deleteSupplier() throws Exception {
    Supplier requestSupplier = supplierRepository.findTopByOrderByIdDesc();
    MvcResult result =
        mockMvc
            .perform(
                delete("/api/supplier/delete/" + requestSupplier.getId())
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
    Supplier expectedSupplier = om.readValue(resultContent, Supplier.class);
    log.error( "expectedSupplier" , expectedSupplier.toJson());
    Assert.assertFalse(expectedSupplier.getActive());
  }

  private int getRandom(int min, int max) {
    int random_int = (int) Math.floor(Math.random() * (max - min + 1));
    return random_int;
  }
}
