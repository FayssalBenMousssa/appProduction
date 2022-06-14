package dev.fenix.application.api.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.fenix.application.api.production.product.ProductResource;
import dev.fenix.application.core.model.Period;
import dev.fenix.application.production.product.model.*;
import dev.fenix.application.production.product.repository.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
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

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
  @Autowired private ProductRepository productRepository;
  @Autowired private ProductTypeRepository productTypeRepository;
  @Autowired private PackagingRepository packagingRepository;
  @Autowired private ProductionUnitRepository productionUnitRepository;
  @Autowired private SiUnitRepository siUnitRepository;
  @Autowired private ClassificationRepository classificationRepository;
  @Autowired private CategoryPriceRepository categoryPriceRepository;
  @Autowired private TaxRepository taxRepository;


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
   ProductType type =  productTypeRepository.findTopByOrderByIdDesc();
    params.add("page", "0");
    params.add("size", "2");
    params.add("sort", "id,desc");
    params.add("type", String.valueOf(type.getId()));
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


    @Test
    @WithMockUser(username = "fenix")
    @Order(1)
    void saveProduct() throws Exception {
        ProductType type =  productTypeRepository.findTopByOrderByIdDesc();
        Packaging packaging =  packagingRepository.findTopByOrderByIdDesc();
         ProductionUnit productionUnit =  productionUnitRepository.findTopByOrderByIdDesc();
        List<ProductionUnit> productionUnits = new ArrayList<>();
        productionUnits.add( productionUnit);
        SiUnit siUnit = siUnitRepository.findTopByOrderByIdDesc();
        Classification classification = classificationRepository.findTopByOrderByIdDesc();

        Product requestProduct = new Product();
        requestProduct.setProductType(type);
        requestProduct.setName("Product " + this.getRandom(1,2000) );
        requestProduct.setCode( String.valueOf(this.getRandom(1,2000)) );
        requestProduct.setCodeDes( "Code description " +  String.valueOf(this.getRandom(1,2000)) );
        requestProduct.setPackaging(packaging );
        requestProduct.setProductionUnits(productionUnits);
        requestProduct.setSiUnit(siUnit);
        requestProduct.setClassification(classification);

        ///// Prices

        Price price = new Price();
        Period period = new Period();
        period.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse("2022-03-17"));
        period.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse("2050-03-17"));

        price.setPeriod(period);
        price.setValue(Double.valueOf(this.getRandom(1000,2000)));
        price.setName("Prix de test");
        price.setActive(true);
        price.setTax(taxRepository.findTopByOrderByIdDesc());
        price.setCategory(categoryPriceRepository.findTopByOrderByIdDesc());
        price.setMaxQte(Integer.valueOf(this.getRandom(1000,2000)));


        Price price_1 = new Price();
        Period period_1 = new Period();
        period_1.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse("2022-05-17"));
        period_1.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse("2050-03-17"));
        price_1.setPeriod(period);
        price_1.setValue(Double.valueOf(this.getRandom(0,2000)));
        price_1.setName("Prix de test 1");
        price_1.setActive(true);
        price_1.setTax(taxRepository.findTopByOrderByIdDesc());
        price_1.setCategory(categoryPriceRepository.findTopByOrderByIdDesc());
        price_1.setMaxQte(Integer.valueOf(this.getRandom(0,2000)));

        List<Price> prices = new ArrayList<>();
        prices.add(price);
        prices.add(price_1);

        requestProduct.setPrices(prices);

        String jsonRequest = om.writeValueAsString(requestProduct);
        MvcResult result =
                mockMvc
                        .perform(
                                post("/api/product/save")
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
        Product expectedProduct = om.readValue(resultContent, Product.class);
        Assert.assertTrue(expectedProduct.getId() != null);
        Assert.assertTrue(expectedProduct.getName().equals(requestProduct.getName()));

    }


    @Test
    @WithMockUser(username = "fenix")
    @Order(2)
    void updateProduct() throws Exception {

        Product requestProduct = productRepository.findTopByOrderByIdDesc();
        requestProduct.setName("updated_Product");



        Period period = new Period();
        period.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse("2022-03-17"));
        period.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse("2050-03-17"));

        Price price_1 = new Price();
        Period period_1 = new Period();
        period_1.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse("2022-05-17"));
        period_1.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse("2050-03-17"));
        price_1.setPeriod(period);
        price_1.setValue(Double.valueOf(this.getRandom(0,2000)));
        price_1.setName("Prix de test 1");
        price_1.setActive(true);
        price_1.setTax(taxRepository.findTopByOrderByIdDesc());
        price_1.setCategory(categoryPriceRepository.findTopByOrderByIdDesc());
        price_1.setMaxQte(Integer.valueOf(this.getRandom(0,2000)));

        requestProduct.getPrices().add(price_1);



        String jsonRequest = om.writeValueAsString(requestProduct);
        MvcResult result =
                mockMvc
                        .perform(
                                put("/api/product/update")
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
        Product expectedProduct = om.readValue(resultContent, Product.class);
        Assert.assertTrue(expectedProduct.getId() != null);
        Assert.assertTrue(expectedProduct.getName().equals(requestProduct.getName()));
        Assert.assertTrue(expectedProduct.getName().contains("updated_Product"));

    }



    private int getRandom(int min, int max) {
        int random_int = (int) Math.floor(Math.random() * (max - min + 1));
        return random_int;
    }
}
