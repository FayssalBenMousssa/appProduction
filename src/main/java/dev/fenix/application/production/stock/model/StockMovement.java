package dev.fenix.application.production.stock.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface StockMovement {


    int getIdDepot();
    String getNameDepot();

    int getDocumentId();
    int getTypeId();
    String getTypeName();
    String getDocumentCode();
    String getCode();
    String getBatchNumber();
    String getNameProduct();
    Float getQuantity();
    int getIdProduct();
    Date getExpirationDate() ;
    Date getProductionDate() ;
    Date getDateDocument() ;



    default JSONObject toJson() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        JSONObject stockCountJSON = new JSONObject();
        try {
            if (this.getTypeName() != null )  stockCountJSON.put("typeName", this.getTypeName());
            if (this.getTypeId() != 0 )  stockCountJSON.put("idType", this.getTypeId());
            if (this.getNameDepot() != null )  stockCountJSON.put("nameDepot", this.getNameDepot());
            if (this.getIdDepot() != 0 )  stockCountJSON.put("idDepot", this.getIdDepot());
            if (this.getDocumentCode() != null )  stockCountJSON.put("documentCode", this.getDocumentCode());
            if (this.getBatchNumber() != null )  stockCountJSON.put("batchNumber", this.getBatchNumber());
            if (this.getNameProduct() != null )  stockCountJSON.put("nameProduct", this.getNameProduct());
            if (this.getQuantity() != null ) stockCountJSON.put("quantity", this.getQuantity());
            if (this.getIdProduct() != 0 )  stockCountJSON.put("idProduct", this.getIdProduct());
            if (this.getDocumentId() != 0 )  stockCountJSON.put("documentId", this.getDocumentId());
            if (this.getExpirationDate() != null )   stockCountJSON.put("expirationDate", formatter.format(this.getExpirationDate()));
            if (this.getProductionDate() != null )  stockCountJSON.put("productionDate", formatter.format(this.getProductionDate()));
            if (this.getDateDocument() != null )  stockCountJSON.put("dateDocument", formatter.format(this.getDateDocument()));

        }catch (JSONException e) {
            e.printStackTrace();
        }
        return stockCountJSON;
    }

}
