package dev.fenix.application.production.vendor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "vnd__address")
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @NotNull(message = "Please enter the addressOne")
    private String name;

    @NotNull(message = "Please enter the addressOne")
    private String lineOne;


    private String lineTow;
    private long zipCode;

    @NotNull(message = "Please enter the addressTow")
    private String city ;

    @NotNull(message = "Please enter the country")
    private String country ;



    private boolean active;

    public JSONObject toJson() {
        JSONObject addresstJSON = new JSONObject();
        try {
            addresstJSON.put("id", this.getId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return addresstJSON;
    }

}
