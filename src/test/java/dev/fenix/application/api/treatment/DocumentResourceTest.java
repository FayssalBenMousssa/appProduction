package dev.fenix.application.api.treatment;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.fenix.application.business.repository.CompanyRepository;
import dev.fenix.application.production.product.repository.ProductRepository;
import dev.fenix.application.production.product.repository.SiUnitRepository;
import dev.fenix.application.production.treatment.model.*;
import dev.fenix.application.production.treatment.repository.DocumentRepository;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
class DocumentResourceTest {
  private static final Logger log = LoggerFactory.getLogger(DocumentResourceTest.class);
  @Autowired private MockMvc mockMvc;
  @Autowired private TypeRepository typeRepository;
  @Autowired private DocumentRepository documentRepository;
  @Autowired private ProductRepository productRepository;
  @Autowired private SiUnitRepository siUnitRepository;
  @Autowired private CompanyRepository companyRepository;

  @Autowired private WebApplicationContext context;
  ObjectMapper om = new ObjectMapper();

  @Before
  private void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  @WithMockUser(username = "fenix")
  public void httpRequestDocumentTest() throws Exception {
    this.mockMvc
        .perform(get("/api/document"))
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
  void indexNoDocumentNoParams() throws Exception {

    this.mockMvc
        .perform(get("/api/document/index"))
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
  void indexDocumentWithParams() throws Exception {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();


    params.add("page", "0");
    params.add("size", "50");
    params.add("sort", "id,desc");
   // params.add("type", "10128");
  // params.add("category", "1");

    this.mockMvc
        .perform(get("/api/document/index").params(params))
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
  @Order(5)
  @WithMockUser(
          username = "fenix",
          roles = {"USER", "ADMIN"})
  void indexDocumentWithParamsType() throws Exception {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

    params.add("page", "0");
    params.add("size", "50");
    params.add("sort", "id,desc");


   Type type = typeRepository.findTopByOrderByIdDesc();
    params.add("type", String.valueOf(10091L));
    this.mockMvc
            .perform(get("/api/document/index").params(params))
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


  void indexDocumentWithParamsCategory() throws Exception {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

    params.add("page", "0");
    params.add("size", "50");
    params.add("sort", "id,desc");

    params.add("category", "1");

    this.mockMvc
            .perform(get("/api/document/index").params(params))
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
  void getDocument() throws Exception {
    Document requestDocument = documentRepository.findTopByOrderByIdDesc();
    MvcResult result =
        this.mockMvc
            .perform(get("/api/document/get/" + requestDocument.getId()))
            .andDo(print())
            .andDo(
                document(
                    "{methodName}",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint())))
            .andExpect(status().isOk())
            .andReturn();
    String resultContent = result.getResponse().getContentAsString();
    Document expectedDocument = om.readValue(resultContent, Document.class);
    Assert.assertTrue(expectedDocument.getId() != null);

    log.info(String.valueOf(expectedDocument.getId()));
    log.info(String.valueOf(requestDocument.getId()));

    Assert.assertTrue(expectedDocument.getId().equals(requestDocument.getId()));
  }

  @Test
  @WithMockUser(username = "fenix")
  @Order(1)
  void saveDocument() throws Exception {


    Document requestDocument = new Document();

    Type type = typeRepository.findOneById(10091L);

    Type type_1 = new Type();
    type_1.setId(type.getId());
    int random = getRandom(10000, 10000000);

    requestDocument.setType(type);
    requestDocument.setCode(type.getAbbreviation() + "-" + random);
    requestDocument.setActive(true);
    requestDocument.setName("Name" + random);
    requestDocument.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2022-03-17"));
    requestDocument.setStatus(Status.INITIATED);
    List<DocumentDataValue> metaDataValues = new ArrayList<>();
    requestDocument.setSource( companyRepository.findTopByOrderByIdDesc());
    requestDocument.setDestination( companyRepository.findTopByOrderByIdDesc());


    type.getMetaData()
        .forEach(
            metaData -> {
              DocumentDataValue documentDataValue = new DocumentDataValue();

              documentDataValue.setMetaData(metaData);
              documentDataValue.setValue("value " + random);
              documentDataValue.setActive(true);
              metaDataValues.add(documentDataValue);
            });

    requestDocument.setDocumentDataValues(metaDataValues);

    List<DocumentProduct> documentProductList = new ArrayList<>();

    DocumentProduct documentProductList_1 = new DocumentProduct();
    documentProductList_1.setProduct(productRepository.findTopByOrderByIdDesc());
    documentProductList_1.setQuantity(this.getRandom(1, 1000));
    documentProductList_1.setSiUnit(siUnitRepository.findTopByOrderByIdDesc());
    documentProductList_1.setActive(true);

    DocumentProduct documentProductList_2 = new DocumentProduct();
    documentProductList_2.setProduct(productRepository.findTopByOrderByIdDesc());
    documentProductList_2.setQuantity(this.getRandom(1, 1000));
    documentProductList_2.setSiUnit(siUnitRepository.findTopByOrderByIdDesc());
    documentProductList_2.setActive(true);

    documentProductList.add(documentProductList_1);
    documentProductList.add(documentProductList_2);

    requestDocument.setDocumentProduct(documentProductList);

    String jsonRequest = om.writeValueAsString(requestDocument);
    String resultContent = new String();

      //
    MvcResult result  =
        mockMvc
            .perform(
                post("/api/document/save")
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
        resultContent = result.getResponse().getContentAsString();



    Document expectedDocument = om.readValue(resultContent, Document.class);
    Assert.assertTrue(expectedDocument.getId() != null);

  }

  @Test
  @WithMockUser(username = "fenix")
  @Order(2)
  void updateDocument() throws Exception {
    Document requestDocument = documentRepository.findTopByOrderByIdDesc();
    requestDocument.setName("Updated_" + requestDocument.getName());
    requestDocument.setStatus(Status.IN_PROGRESS);

    String jsonRequest = om.writeValueAsString(requestDocument);

    /* MvcResult result =
        mockMvc
            .perform(
                put("/api/document/update")
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
    Document expectedDocument = om.readValue(resultContent, Document.class);
    Assert.assertTrue(expectedDocument.getId() != null);
    Assert.assertTrue(expectedDocument.getCode().equals(requestDocument.getCode()));
    Assert.assertTrue(expectedDocument.getName().contains("Updated_"));
    */

  }

  @Test
  @WithMockUser(username = "fenix")
  @Order(3)
  void deleteDocument() throws Exception {
    Document requestDocument = documentRepository.findTopByOrderByIdDesc();
    requestDocument.setActive(false);
    requestDocument.setName("Updated_");
    String jsonRequest = om.writeValueAsString(requestDocument);
    MvcResult result =
        mockMvc
            .perform(
                delete("/api/document/delete/" + requestDocument.getId())
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
    Document expectedDocument = om.readValue(resultContent, Document.class);
    Assert.assertFalse(expectedDocument.getActive());
  }
  /*
    @Test
    @WithMockUser(username = "fenix")
    @Order(4)
    void deleteFormula() throws Exception {
      Formula requestFormula = formulaRepository.findTopByOrderByIdDesc();
      requestFormula.setActive(false);
      String jsonRequest = om.writeValueAsString(requestFormula);
      MvcResult result =
          mockMvc.perform(delete("/api/product/formula/delete/" + requestFormula.getId())
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
  */
  private int getRandom(int min, int max) {
    int random_int = (int) Math.floor(Math.random() * (max - min + 1));
    return random_int;
  }
}
