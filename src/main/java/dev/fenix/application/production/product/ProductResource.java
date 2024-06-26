package dev.fenix.application.production.product;

import dev.fenix.application.app.model.ResponseData;
import dev.fenix.application.configuration.database.DBContextHolder;
import dev.fenix.application.configuration.database.DBEnum;
import dev.fenix.application.production.customer.model.Customer;
import dev.fenix.application.production.customer.repository.CustomerRepository;
import dev.fenix.application.production.product.model.Price;
import dev.fenix.application.production.product.model.Product;
import dev.fenix.application.production.product.model.ProductAttachment;
import dev.fenix.application.production.product.model.ProductType;
import dev.fenix.application.production.product.repository.PriceRepository;
import dev.fenix.application.production.product.repository.ProductAttachmentRepository;
import dev.fenix.application.production.product.repository.ProductRepository;
import dev.fenix.application.production.product.repository.ProductTypeRepository;
import dev.fenix.application.production.product.service.AttachmentService;
import dev.fenix.application.production.product.service.ProductService;
import dev.fenix.application.security.model.User;
import dev.fenix.application.security.repository.UserRepository;
import javassist.NotFoundException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController()
@RequestMapping("/api/product")
public class ProductResource {
  private static final Logger log = LoggerFactory.getLogger(ProductResource.class);

  @Autowired private ProductRepository productRepository;

  @Autowired private PriceRepository priceRepository;

  @Autowired private CustomerRepository customerRepository;
  @Autowired private ProductTypeRepository productTypeRepository;
  @Autowired private ProductService productService;

  @Autowired private AttachmentService attachmentService;
  @Autowired private ProductAttachmentRepository attachmentRepository;


  @Autowired private   UserRepository userRepository;
  @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file , @RequestParam("id") String id ,@RequestParam(required = false) String attachmentType) throws Exception {
    ProductAttachment attachment;
    Product product = productRepository.getOne(Long.valueOf(id));

    attachment = attachmentService.saveAttachment(file , product , attachmentType);
    String downloadURL  = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/product/download/").path(attachment.getId()).toUriString();
/// && (file.getContentType() == "image/png" || file.getContentType() == "image/jpeg" ||  file.getContentType() == "image/jpg"   )
   //// documentfile.getContentType());
    if (attachmentType.equals("productImage")   && (file.getContentType().equals("image/png") || file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/jpg") )) {
      product.setImageUrl(attachment.getId());
      productRepository.save(product);
    }

    ResponseData reponse = new ResponseData(attachment.getFileName(), downloadURL, file.getContentType(), file.getSize() , attachment.getId());
    return    ResponseEntity.ok(reponse);
  }

  @RequestMapping(value = "/upload/delete/{id}",  method = RequestMethod.DELETE )
  public ResponseEntity uploadDelete( @RequestParam("id") String id)  {
    try {
      ProductAttachment  attachment  = attachmentRepository.getOne(id) ;



      attachment.setActive(false);
      attachment =  attachmentRepository.save(attachment);
      return    ResponseEntity.ok(attachment.getId());
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

  }

  @GetMapping("/download/{fileId}")
  public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
    ProductAttachment attachment;
    attachment = attachmentService.getAttachment(fileId);

   // document"=====================================");
   // documentattachment.getFileName());
   // documentattachment.getFileType());
   // documentString.valueOf(attachment.getData().length));
   // document"=====================================");

    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(attachment.getFileType()))
        .header(
            HttpHeaders.CONTENT_DISPOSITION,
            "attachement;filename \"" + attachment.getFileName() + "\"")
        .body(new ByteArrayResource(attachment.getData()));
  }


  @GetMapping("/images/{fileId}")
  public ResponseEntity<byte[]> publicImage(@PathVariable String fileId) throws Exception {
    DBContextHolder.setCurrentDb(DBEnum.CANELIA);
    ProductAttachment attachment;
    attachment = attachmentService.getAttachment(fileId);
    String filename = attachment.getFileName();
    String extension = filename.substring(filename.lastIndexOf(".") + 1);
    MediaType mediaType = MediaType.parseMediaType("image/" + extension);
    return ResponseEntity.ok()
            .contentType(mediaType)
            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + filename)
            .body(attachment.getData());
  }

  @RequestMapping(
      value = {"/", ""},
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public String index() {
    //log.trace("ProductResource.index/ method accessed");
    return JSONObject.quote("Api :" + this.getClass().getSimpleName());
  }

  @RequestMapping(
      value = "/index",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> index(
      HttpServletRequest request,
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "1000") Integer size,
      @RequestParam(defaultValue = "name,asc") String[] sort,
      @RequestParam(required = false) String[] query)
      throws InterruptedException {

    //log.trace("ProductResource.index method accessed");

    JSONArray jArray = new JSONArray();

    List<Product> products = productService.getAllProducts(page, size, sort, query);

    for (Product product : products) {
      jArray.put(product.toJson());
    }

    JSONObject response = new JSONObject();


    log.info(this.getCurrentUser().getUserName() + " has  " + jArray.length() + " products");

    try {
      response.put("results", jArray);
      response.put("total_type", productService.getCount());
      response.put("count", jArray.length());
      response.put("total", productService.getCountAll());
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
  public ResponseEntity<String> get(HttpServletRequest request, @PathVariable Long id ,
  @RequestParam(required = false) Long customerId

  ) throws NotFoundException {
    //log.trace("ProductResource.get method accessed");

    Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product  not found"));

    if (customerId != null && customerId != 0) {
     Customer customer = customerRepository.findOneById(customerId);
     List<Price> prices = priceRepository.findByProductAndCustomersAndActiveTrue(product,customer);
    if (!prices.isEmpty()) {
      product.setPrices(prices);
    }else {
      prices = priceRepository.findByProductAndActiveTrueAndCustomersEmpty(product);
      product.setPrices(prices);
    }



    }




    return new ResponseEntity<>(product.toJson().toString(), HttpStatus.OK);
  }

  @RequestMapping(
      value = "/save",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> save(@Valid @RequestBody Product product, HttpServletRequest request) {
    //log.trace("ProductResource.save method accessed");
    try {
      product.setActive(true);
      Product savedProduct = productRepository.save(product);
      return new ResponseEntity<>(savedProduct.toJson().toString(), HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(
      value = "/update",
      method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> update(@Valid @RequestBody Product product, HttpServletRequest request) {
    //log.trace("ProductResource.update method accessed");
    try {
      product.setActive(true);
      Product updatedProduct = productRepository.save(product);
      return new ResponseEntity<>(updatedProduct.toJson().toString(), HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(
      value = "/delete/{id}",
      method = RequestMethod.DELETE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    //log.trace("ProductResource.delete method accessed");
    Product product = productRepository.getOne(id);
    try {
      product.setActive(false);
      Product savedProduct = productRepository.save(product);
      return ResponseEntity.ok(savedProduct.toJson().toString());
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(
      value = {"/info", ""},
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity info() {
    //log.trace("ProductResource.info method accessed");
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

  private User getCurrentUser() {
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = userDetails.getUsername();
    User user = userRepository.findOneByUserName(username);
    return user;
  }
}
