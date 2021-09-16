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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vnd__vendor")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Please enter the code")
    private String code;

    @NotNull(message = "Please enter the socialReason")
    private String socialReason;

    @NotNull(message = "Please enter the Address")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;


    @NotNull(message = "Please enter the telephone")
    private String telephone;


    private String email;


    @NotNull(message = "Please enter the contacts")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "main_contact_id", referencedColumnName = "id")
    private VendorContact mainContact;

    @NotNull(message = "Please enter the classement")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "classification_id", referencedColumnName = "id")
    private VendorClassification classification;


    private String note;


    private Boolean active;

    public JSONObject toJson() {
        JSONObject vendorJSON = new JSONObject();
        try {
            vendorJSON.put("id", this.getId());



        } catch (JSONException e) {
            e.printStackTrace();
        }
        return vendorJSON;
    }

}
