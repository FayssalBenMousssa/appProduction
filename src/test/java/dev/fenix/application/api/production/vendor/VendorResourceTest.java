package dev.fenix.application.api.production.vendor;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.fenix.application.production.vendor.model.Address;
import dev.fenix.application.production.vendor.model.Vendor;
import dev.fenix.application.production.vendor.model.VendorClassification;
import dev.fenix.application.production.vendor.model.VendorContact;
import dev.fenix.application.production.vendor.repository.VendorClassificationRepository;
import dev.fenix.application.production.vendor.repository.VendorRepository;
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
class VendorResourceTest {
  private static final Logger log = LoggerFactory.getLogger(VendorResourceTest.class);
  @Autowired private MockMvc mockMvc;
  @Autowired private VendorRepository vendorRepository;
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
        .perform(get("/api/vendor/index"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser(username = "fenix")
  void save() throws Exception {
    log.info("{methodName}");
    Address address = new Address(null,"address facturetion" ,"line One","line tow adress",55555l,"fes","Morocco",true );
    VendorContact contact = new VendorContact(null,"fayssal benmoussa" , "admin","0644495470","fayssal@gmail.com" ," texte note" , true);
    VendorClassification classification = new VendorClassification(null, "New one" ,"code" ,  true);
    Vendor requestVendor = new Vendor(null ,"MC Carton" , "Social Reason " + this.getRandom(0, 1000)  ,address , "0644495470" , "email@gmail.com" ,contact ,classification,"Text Note" ,true  );



    String jsonRequest = om.writeValueAsString(requestVendor);


    MvcResult result =
        mockMvc
            .perform(
                post("/api/vendor/save")
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

    Vendor expectedVendor = om.readValue(resultContent, Vendor.class);
    Assert.assertTrue(expectedVendor.getId() != null);
    // Assert.assertTrue(expectedVendor.getSocialReason().equals(requestVendor.getSocialReason()));
  }
/*
  @Test
  @WithMockUser(username = "fenix")
  void update() throws Exception {
    VendorClassification requestClassification =
            vendorRepository.findTopByOrderByIdDesc();
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
*/
  private int getRandom(int min, int max) {
    int random_int = (int) Math.floor(Math.random() * (max - min + 1));
    return random_int;
  }
}


