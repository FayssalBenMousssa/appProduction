package dev.fenix.application.api.production.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.mail.iap.Response;
import dev.fenix.application.production.product.model.Packaging;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PackagingResourceTest {

  @Autowired private PackagingResource controller;

  @Autowired private MockMvc mockMvc;
  @Autowired private WebApplicationContext context;

  ObjectMapper om = new ObjectMapper();

  @Before
  private void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  public void contextLoads() throws Exception {
    assertThat(controller).isNotNull();
  }

  @Test
  void index() {}

  @Test
  void testIndex() {}

  @Test
  @WithMockUser(username = "fenix", roles = {"USER", "ADMIN"})
  void save() throws Exception {
    Packaging requestPackaging = new Packaging();


    int random_int = (int)Math.floor(Math.random()*(100000000-0+1));
    requestPackaging.setName("Packaging " + random_int);

    String jsonRequest = om.writeValueAsString(requestPackaging);
    MvcResult result =
        mockMvc
            .perform(
                post("/api/product/packaging/save")
                    .content(jsonRequest)
                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andDo(print())
            .andExpect(status().isOk())
            .andReturn() ;
      String resultContent = result.getResponse().getContentAsString();
      Packaging expectedPackaging = om.readValue(resultContent, Packaging.class);
      Assert.assertTrue(expectedPackaging.getId() != null);
      Assert.assertTrue(expectedPackaging.getName().equals(requestPackaging.getName()));

  }

  @Test
  void update() {}

  @Test
  void delete() {}
}
