package dev.fenix.application.api.production.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.fenix.application.business.repository.JobRepository;
import dev.fenix.application.business.repository.StaffRepository;
import dev.fenix.application.production.customer.model.Customer;
import dev.fenix.application.production.customer.model.CustomerClassification;
import dev.fenix.application.production.customer.repository.CustomerClassificationRepository;
import dev.fenix.application.production.customer.repository.CustomerRepository;
import dev.fenix.application.production.product.repository.FormulaRepository;
import dev.fenix.application.production.product.repository.ProductRepository;
import dev.fenix.application.production.product.repository.SiUnitRepository;
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
class CustomerResourceTest {
  private static final Logger log = LoggerFactory.getLogger(CustomerResourceTest.class);
  @Autowired private MockMvc mockMvc;
  @Autowired private JobRepository jobRepository;
  @Autowired private StaffRepository staffRepository;
  @Autowired private FormulaRepository formulaRepository;
  @Autowired private ProductRepository productRepository;
  @Autowired private SiUnitRepository siUnitRepository;
  @Autowired private CustomerRepository customerRepository;
  @Autowired private CustomerClassificationRepository customerClassificationRepository;
  @Autowired private WebApplicationContext context;


  ObjectMapper om = new ObjectMapper();

  @Before
  private void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  @WithMockUser(username = "fenix")
  public void httpCustomerRequestTest() throws Exception {
    this.mockMvc
        .perform(get("/api/customer"))
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
  void indexCustomer() throws Exception {

    this.mockMvc
        .perform(get("/api/customer/index"))
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
  void indexCustomerWithParams() throws Exception {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("page", "0");
    params.add("page", "0");
    params.add("size", "2");
    params.add("sort", "id,desc");

    this.mockMvc
        .perform(get("/api/customer/index").params(params))
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
  void getCustomer() throws Exception {
    Customer requestCustomer = customerRepository.findTopByOrderByIdDesc();
    MvcResult result =
        this.mockMvc
            .perform(get("/api/customer/get/" + requestCustomer.getId()))
            .andDo(print())
            .andDo(
                document(
                    "{methodName}",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint())))
            .andExpect(status().isOk())
            .andReturn();
    String resultContent = result.getResponse().getContentAsString();
    Customer expectedCustomer = om.readValue(resultContent, Customer.class);
    Assert.assertTrue(expectedCustomer.getId() != null);

    log.info(String.valueOf(expectedCustomer.getId()));
    log.info(String.valueOf(requestCustomer.getId()));

    Assert.assertTrue(expectedCustomer.getId().equals(requestCustomer.getId()));
  }

  @Test
  @WithMockUser(username = "fenix")
  @Order(1)
  void saveCustomer() throws Exception {
    Customer requestCustomer = new Customer();
    int random =   this.getRandom(1,999);
    requestCustomer.setCode("CODE" + random);
    requestCustomer.setSocialReason("Social Reason" + random);
    requestCustomer.setActive(true);
    requestCustomer.setEmail("requestCustomer@email.com" + random);
    requestCustomer.setIce("000000000" + random);
    requestCustomer.setTelephone("000000000" + random);
    CustomerClassification classification =   customerClassificationRepository.findOneById(1L);
    requestCustomer.setClassification(classification);
    String jsonRequest = om.writeValueAsString(requestCustomer);
    MvcResult result =
        mockMvc
            .perform(
                post("/api/customer/save")
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
    Customer expectedCustomer = om.readValue(resultContent, Customer.class);
    Assert.assertTrue(expectedCustomer.getId() != null);
    Assert.assertTrue(requestCustomer.getCode().equals(expectedCustomer.getCode()));
  }

  @Test
  @WithMockUser(username = "fenix")
  @Order(2)
  void updateCustomer() throws Exception {

    Customer requestCustomer = customerRepository.findTopByOrderByIdDesc();
    requestCustomer.setNote("updated");
    String jsonRequest = om.writeValueAsString(requestCustomer);
    MvcResult result =
        mockMvc
            .perform(
                put("/api/customer/update")
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
    Customer expectedCustomer = om.readValue(resultContent, Customer.class);
    Assert.assertTrue(expectedCustomer.getNote().contains("updated"));
  }

  @Test
  @WithMockUser(username = "fenix")
  @Order(4)
  void deleteCustomer() throws Exception {
    Customer requestCustomer = customerRepository.findTopByOrderByIdDesc();
    MvcResult result =
        mockMvc
            .perform(
                delete("/api/customer/delete/" + requestCustomer.getId())
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
    Customer expectedCustomer = om.readValue(resultContent, Customer.class);
    log.error( "expectedCustomer" , expectedCustomer.toJson());
    Assert.assertFalse(expectedCustomer.getActive());
  }

  private int getRandom(int min, int max) {
    int random_int = (int) Math.floor(Math.random() * (max - min + 1));
    return random_int;
  }
}
