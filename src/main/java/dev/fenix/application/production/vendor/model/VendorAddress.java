package dev.fenix.application.production.vendor.model;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "vendor__address")
public class VendorAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Please enter the  vendor")
    private String  vendor;

    @NotNull(message = "Please enter the addressOrdinal ")
    private String addressOrdinal;


    @NotNull(message = "Please enter the addressOne")
    private String addressOne;

    @NotNull(message = "Please enter the addressTow")
    private String addressTow;

    @NotNull(message = "Please enter the locality")
    private String locality;
    @NotNull(message = "Please enter the province")
    private String province;
    @NotNull(message = "Please enter the postcode")
    private String postcode;
    @NotNull(message = "Please enter the country")
    private String country;

    @NotNull(message = "Please enter the active")
    private String active;
    public JSONObject toJson() {
        JSONObject addresstJSON = new JSONObject();
        try {
            addresstJSON.put("id", this.getId());
            addresstJSON.put("code", this.getAddressOne());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return addresstJSON;
    }

}
