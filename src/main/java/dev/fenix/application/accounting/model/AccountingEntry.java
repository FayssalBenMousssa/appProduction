package dev.fenix.application.accounting.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface AccountingEntry {


    int getId();

    Date getDate();

    String getPiece();

    String getLabel();

    Float getDebit();

    Float getCredit();

    String getType();

    String getLetter();


    default JSONObject toJson() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        JSONObject stockCountJSON = new JSONObject();
        try {


            stockCountJSON.put("id", this.getId());
            stockCountJSON.put("date", formatter.format(this.getDate()));
            stockCountJSON.put("piece", this.getPiece());
            stockCountJSON.put("label", this.getLabel());
            stockCountJSON.put("debit", this.getDebit());
            stockCountJSON.put("credit", this.getCredit());
            stockCountJSON.put("type", this.getType());
            stockCountJSON.put("letter", this.getLetter());


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stockCountJSON;
    }


}
