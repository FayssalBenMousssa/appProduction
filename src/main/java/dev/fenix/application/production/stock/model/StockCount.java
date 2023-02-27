package dev.fenix.application.production.stock.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface StockCount {


    String getBatchNumber();
    String getNameProduct();
    Float getQuantity();
    Integer getIdProduct();
    Integer getIdDepot();
    String getNameDepot();
    Date getExpirationDate() ;
    Date getProductionDate() ;
    Date dateStock();

    Long getIdUnit();
    String getNameUnit();

    /*
    *
    *   "unit.id as idUnit," +
            "unit.name as nameUnit" +
    *
    * */

     default JSONObject toJson() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        JSONObject stockCountJSON = new JSONObject();
        try {
            if (this.getBatchNumber() != null )  stockCountJSON.put("batchNumber", this.getBatchNumber());
            if (this.getNameProduct() != null )  stockCountJSON.put("nameProduct", this.getNameProduct());
            if (this.getQuantity() != null ) stockCountJSON.put("quantity", this.getQuantity());
            if (this.getIdProduct() != null )  stockCountJSON.put("idProduct", this.getIdProduct());
            if (this.getIdDepot() != null ) stockCountJSON.put("idDepot", this.getIdDepot());
            if (this.getNameDepot() != null )  stockCountJSON.put("nameDepot", this.getNameDepot());
            if (this.getExpirationDate() != null )   stockCountJSON.put("expirationDate", formatter.format(this.getExpirationDate()));
            if (this.getProductionDate() != null )  stockCountJSON.put("productionDate", formatter.format(this.getProductionDate()));
            if (this.getIdUnit() != null )  stockCountJSON.put("idUnit",this.getIdUnit()  );
            if (this.getNameUnit() != null )  stockCountJSON.put("nameUnit", getNameUnit());
//            if (this.dateStock() != null )  stockCountJSON.put("dateStock", this.dateStock());

        }catch (JSONException e) {
            e.printStackTrace();
        }
        return stockCountJSON;
    }

}
