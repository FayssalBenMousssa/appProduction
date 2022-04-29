package dev.fenix.application.api.production.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
class ProductResourceTest {

  @Autowired private ProductResource controller;

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
  @Order(5)
  @WithMockUser(
          username = "fenix",
          roles = {"USER", "ADMIN"})
  void indexProductWithParams() throws Exception {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();


    params.add("page", "0");
    params.add("size", "2");
    params.add("sort", "id,desc");
    params.add("type", "1");


    this.mockMvc
            .perform(get("/api/product/index").params(params))
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


}
