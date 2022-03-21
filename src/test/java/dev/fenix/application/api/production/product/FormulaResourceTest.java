package dev.fenix.application.api.production.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.fenix.application.business.model.Job;
import dev.fenix.application.business.model.Staff;
import dev.fenix.application.business.repository.JobRepository;
import dev.fenix.application.business.repository.StaffRepository;
import dev.fenix.application.core.model.Note;
import dev.fenix.application.person.model.Gender;
import dev.fenix.application.person.model.Person;
import dev.fenix.application.production.product.model.*;
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
class FormulaResourceTest {
  private static final Logger log = LoggerFactory.getLogger(FormulaResourceTest.class);
  @Autowired private MockMvc mockMvc;
  @Autowired private JobRepository jobRepository;
  @Autowired private StaffRepository staffRepository;
  @Autowired private FormulaRepository formulaRepository;
  @Autowired private ProductRepository productRepository;
  @Autowired private SiUnitRepository siUnitRepository;
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
        .perform(get("/api/product/formula"))
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
  void indexFormula() throws Exception {

    this.mockMvc
        .perform(get("/api/product/formula/index"))
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
  void indexFormulaWithParams() throws Exception {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

   params.add("page", "0");
    params.add("page", "0");
    params.add("size", "2");
    params.add("sort", "id,desc");


    this.mockMvc
        .perform(get("/api/product/formula/index").params(params))
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
  void getFormula() throws Exception {
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
  void saveFormula() throws Exception {
    Formula requestFormula = new Formula();
    Note note = new Note();
    note.setContent("Hello Fayssal content");
    note.setActive(true);
    note.setColor("#00000");

    Product product = productRepository.findOneById(1L);
    FormulaProduct formulaProduct = new FormulaProduct();
    formulaProduct.setQuantity(10);
    formulaProduct.setProduct(product);
    SiUnit siUnit = siUnitRepository.findOneById(1L);
    formulaProduct.setSiUnit(siUnit);


    FormulaProduct formulaProduct_1 = new FormulaProduct();
    formulaProduct_1.setQuantity(10);
    formulaProduct_1.setProduct(product);
    SiUnit siUnit_2 = siUnitRepository.findOneById(2L);
    formulaProduct_1.setSiUnit(siUnit_2);

    requestFormula.getFormulaProducts().add(formulaProduct);
    requestFormula.getFormulaProducts().add(formulaProduct_1);

    Product product_prod = productRepository.findOneById(2L);



    FormulaNote formulaNote = new FormulaNote();
    formulaNote.setNote(note);
    formulaNote.setActive(true);

    FormulaNote formulaNote_2 = new FormulaNote();
    formulaNote_2.setNote(note);
    formulaNote_2.setActive(true);

    requestFormula.getFormulaNotes().add(formulaNote);
    requestFormula.getFormulaNotes().add(formulaNote_2);
    requestFormula.setProduct(product_prod);
    requestFormula.getFormulaProducts().add(formulaProduct_1);

    requestFormula.setName("Formula name Test");
    requestFormula.setCode("Formula  code");
    requestFormula.setObsolete(false);
    requestFormula.setActive(true);

    String jsonRequest = om.writeValueAsString(requestFormula);
    MvcResult result =
        mockMvc
            .perform(
                post("/api/product/formula/save")
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
    Formula expectedFormula = om.readValue(resultContent, Formula.class);
    Assert.assertTrue(expectedFormula.getId() != null);
    Assert.assertTrue(requestFormula.getCode().equals(expectedFormula.getCode()));
  }

  @Test
  @WithMockUser(username = "fenix")
  @Order(2)
  void updateFormula() throws Exception {
    Formula requestFormula = formulaRepository.findTopByOrderByIdDesc();
    Note note = new Note();
    note.setContent("Hello Fayssal content updateFormula");
    note.setActive(true);
    note.setColor("#00000");
    FormulaNote formulaNote = new FormulaNote();
    formulaNote.setNote(note);
    formulaNote.setActive(true);
    requestFormula.getFormulaNotes().add(formulaNote);
    requestFormula.setName("updateFormula");
    String jsonRequest = om.writeValueAsString(requestFormula);
    MvcResult result =
        mockMvc
            .perform(
                put("/api/product/formula/update")
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
    Formula expectedFormula = om.readValue(resultContent, Formula.class);
    Assert.assertTrue(expectedFormula.getName().contains("updateFormula"));
  }

  @Test
  @WithMockUser(username = "fenix")
  @Order(4)
  void deleteFormula() throws Exception {
    Formula requestFormula = formulaRepository.findTopByOrderByIdDesc();
    requestFormula.setActive(false);
    String jsonRequest = om.writeValueAsString(requestFormula);
    MvcResult result =
        mockMvc
            .perform(
                delete("/api/product/formula/delete/" + requestFormula.getId())
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
    Formula expectedFormula = om.readValue(resultContent, Formula.class);
    Assert.assertFalse(expectedFormula.isActive());
  }

  private int getRandom(int min, int max) {
    int random_int = (int) Math.floor(Math.random() * (max - min + 1));
    return random_int;
  }
}
