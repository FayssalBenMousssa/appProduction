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

            /*
            /api/customer/account/{id}

            AccountingEntry
            _id
            _date
            _piece
            _label
            _debit
            _credit
            _type
            _letter
             */

            if (this.getId() != 0) stockCountJSON.put("id", this.getId());
            if (this.getDate() != null) stockCountJSON.put("date", formatter.format(this.getDate()));
            if (this.getPiece() != null) stockCountJSON.put("piece", this.getPiece());
            if (this.getLabel() != null) stockCountJSON.put("label", this.getLabel());
            if (this.getDebit() != 0) stockCountJSON.put("debit", this.getDebit());
            if (this.getCredit() != 0) stockCountJSON.put("credit", this.getCredit());
            if (this.getType() != null) stockCountJSON.put("type", this.getType());
            if (this.getLetter() != null) stockCountJSON.put("letter", this.getLetter());


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stockCountJSON;
    }



}
