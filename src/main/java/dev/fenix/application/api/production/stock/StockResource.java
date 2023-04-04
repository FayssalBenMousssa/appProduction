package dev.fenix.application.api.production.stock;

import dev.fenix.application.production.stock.model.StockCount;
import dev.fenix.application.production.stock.model.StockMovement;
import dev.fenix.application.production.stock.repository.StockRepository;
import dev.fenix.application.production.stock.service.StockService;
import dev.fenix.application.production.treatment.model.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

@RestController()
@RequestMapping("api/stock")
public class StockResource {

    private static final Logger log = LoggerFactory.getLogger(StockResource.class);
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private StockService stockService;


    private List<Status> statusStock = Arrays.asList(Status.APPROVED , Status.CLOSED );

    @RequestMapping(
            value = "/index",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> index(HttpServletRequest request,
                                   @RequestParam(defaultValue = "0") Integer page,
                                   @RequestParam(defaultValue = "200") Integer size,
                                   @RequestParam(defaultValue = "id,desc") String[] sort,
                                   @RequestParam(required = false) String[] query)
            throws InterruptedException, UnsupportedEncodingException {


        // TimeUnit.SECONDS.sleep(3);


        try {
            Page<StockCount> dataStock = stockService.getAllStocks(page, size, sort, query);

            JSONArray jsonArray = new JSONArray();
            dataStock.forEach((stock) -> {
                //log.info(stock.getNameProduct() + " : " + stock.getQuantity());
                if (stock.getQuantity() != 0)
                    jsonArray.put(stock.toJson());
            });

            JSONObject response = new JSONObject();
            response.put("results", jsonArray);
            response.put("total", dataStock.getTotalElements());

            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e, HttpStatus.NOT_ACCEPTABLE);
        }


    }

    /*
      @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<?> get(HttpServletRequest request, @PathVariable Long id) throws NotFoundException {

        List<StockCount> dataStock = stockRepository.countStockProduct(id , this.statusStock ,  new Date());
        trace(dataStock);
        return new ResponseEntity<>(dataStock, HttpStatus.OK);
      }
    */
    public void trace(List<StockCount> list) {
        list.forEach(pach -> {
            //log.info(pach.getIdProduct() + " -> " + pach.getNameProduct() + " " + pach.getBatchNumber() + " " + pach.getQuantity());
        });
    }


    @RequestMapping(
            value = "/movement",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> movement(
            HttpServletRequest request,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "200") Integer size,
            @RequestParam(defaultValue = "id,desc") String[] sort,
            @RequestParam(required = false) String[] query)
            throws InterruptedException, UnsupportedEncodingException {


        try {
            Page<StockMovement> movementStock = stockService.getAllMovements(page, size, sort, query);

            JSONArray jsonArray = new JSONArray();

            movementStock.forEach((stock) -> {
                jsonArray.put(stock.toJson());
            });


            JSONObject response = new JSONObject();
            response.put("results", jsonArray);
            response.put("total", movementStock.getTotalElements());
            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e, HttpStatus.NOT_ACCEPTABLE);
        }


    }


}
