package dev.fenix.application.api.production.product;

import dev.fenix.application.Application;
import dev.fenix.application.core.model.MetaData;
import dev.fenix.application.production.product.model.*;
import dev.fenix.application.production.product.repository.ProductRepository;
import dev.fenix.application.production.product.repository.ProductTypeRepository;
import dev.fenix.application.production.product.service.ProductService;
import javassist.NotFoundException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController()
@RequestMapping("/api/product")
public class ProductResource {
  private static final Logger log = LoggerFactory.getLogger(ProductResource.class);

  @Autowired private ProductRepository productRepository;
  @Autowired private ProductTypeRepository productTypeRepository;

  @Autowired private ProductService productService;

  @RequestMapping(
      value = {"/", ""},
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public String index() {

    log.trace("ProductResource.index/ method accessed");
    return JSONObject.quote("Api :" + this.getClass().getSimpleName());
  }

  @RequestMapping(
      value = "/index",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> index(
      HttpServletRequest request,
      @RequestParam (required = false)  Long type,
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "200") Integer size,
      @RequestParam(defaultValue = "name,asc") String[] sort,
      @RequestParam(required = false) String[] query) throws InterruptedException {

    log.trace("ProductResource.index method accessed");

    JSONArray jArray = new JSONArray();
    Iterable<Product> products = productService.getAllProducts(page, size, sort, query ,type );
    for (Product product : products) {
      jArray.put(product.toJson());
    }


    JSONObject response = new JSONObject();

    try {
      response.put("results" ,jArray);
      response.put("total_type" , productService.getCount());
      response.put("count" , jArray.length());
      response.put("total" , productService.getCountAll());
      return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
  }

  @RequestMapping(
      value = "/get/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> get(HttpServletRequest request, @PathVariable Long id) throws NotFoundException {
    log.trace("ProductResource.get method accessed");
    Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product  not found"));
    return new ResponseEntity<>(product.toJson().toString(), HttpStatus.OK);
  }

  @RequestMapping(
      value = "/save",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> save(@Valid @RequestBody Product product, HttpServletRequest request) {
    log.trace("ProductResource.save method accessed");


/*


    Product testProduct = new Product();

    Packaging packaging = new Packaging() ;
    packaging.setId(19L);

    ProductType type = new ProductType();
    type.setId(3L);

    ProductionUnit pu = new ProductionUnit();
    pu.setId(2L);

    Classification classification = new Classification();
    classification.setId(38L);

    SiUnit siUnit = new SiUnit();
    siUnit.setId(8L);

    testProduct.setActive(true);
    testProduct.setProductType(type);
    testProduct.setPackaging(packaging);
    testProduct.setCodeDes("xxxxx");
    testProduct.setName("Test product");
    testProduct.setCode("Test");
    testProduct.setClassification(classification);
    testProduct.setProductionUnit(pu);
    testProduct.setSiUnit(siUnit);

    MetaDataValue metaDataValue = new MetaDataValue();
    metaDataValue.setValue("xxxxxx");

    MetaDataValue metaDataValue_2 = new MetaDataValue();
    metaDataValue_2.setValue("xxxxxx x");

    MetaData metaData = new MetaData();
    metaData.setId(1L);

    metaDataValue_2.setMetaData(metaData);
    metaDataValue.setMetaData(metaData);


    List<MetaDataValue> metaDataValues = new ArrayList<MetaDataValue>() ;
    metaDataValues.add(metaDataValue);
    metaDataValues.add(metaDataValue_2);

    testProduct.setMetaDataValues(metaDataValues);

*/



    try {
      product.setActive(true);

     // log.info(product.toString());




      Product savedProduct = productRepository.save(product);
      return new ResponseEntity<>(savedProduct.toJson().toString(), HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>("Not saved", HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(
      value = "/update",
      method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> update(@Valid @RequestBody Product product, HttpServletRequest request) {
    log.trace("ProductResource.update method accessed");
    try {
      product.setActive(true);
      Product updatedProduct = productRepository.save(product);
      return new ResponseEntity<>(updatedProduct.toJson().toString(), HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>("Not saved", HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(
      value = "/delete/{id}",
      method = RequestMethod.DELETE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    log.trace("ProductResource.delete method accessed");
    Product product = productRepository.getOne(id);
    try {
      product.setActive(false);
      Product savedProduct = productRepository.save(product);
      return ResponseEntity.ok(savedProduct.toJson().toString());
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>("not deleted", HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(
      value = {"/info", ""},
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity info() {
    log.trace("ProductResource.info method accessed");
    try {
      JSONObject information = new JSONObject();
      SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
      Date date = new Date();
      information.put("date", formatter.format(date));
      List<ProductType> listProductType = productTypeRepository.findAll();
      for (ProductType type : listProductType) {
        information.put(
            type.getName(),
            productRepository.countByActiveTrueAndProductType(
                productTypeRepository.findOneById(type.getId())));
      }
      return new ResponseEntity<>(information.toString(), HttpStatus.OK);
    } catch (JSONException e) {
      e.printStackTrace();
      return new ResponseEntity<>("BAD_REQUEST", HttpStatus.BAD_REQUEST);
    }
  }
}
