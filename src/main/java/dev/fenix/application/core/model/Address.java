package dev.fenix.application.core.model;

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
@Table(name = "core__address")
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @NotNull(message = "Please enter the addressOne")
    private String name;
    private String lineOne;
    private String lineTow;
    private long zipCode;

    @NotNull(message = "Please enter the addressTow")
    private String city ;

    @NotNull(message = "Please enter the country")
    private String country ;

    @Column(columnDefinition="tinyint(1) default 1")
    private boolean active;

    public JSONObject toJson() {
        JSONObject addresstJSON = new JSONObject();
        try {
            addresstJSON.put("id", this.getId());
            addresstJSON.put("name", this.getName());
            addresstJSON.put("lineOne", this.getLineOne());
            addresstJSON.put("lineTow", this.getLineTow());
            addresstJSON.put("zipCode", this.getZipCode());
            addresstJSON.put("city", this.getCity());
            addresstJSON.put("country", this.getCountry());
            addresstJSON.put("active", this.isActive());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return addresstJSON;
    }

}
