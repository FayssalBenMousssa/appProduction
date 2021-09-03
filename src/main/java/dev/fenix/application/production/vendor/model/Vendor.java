package dev.fenix.application.production.vendor.model;

import dev.fenix.application.production.product.model.ProductionUnit;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Locale;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "vendor__vendor")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Please enter the code")
    private String code;

    @NotNull(message = "Please enter the socialReason")
    private String socialReason;


    @NotNull(message = "Please enter the address")
    private String address;

    @NotNull(message = "Please enter the telephone")
    private String telephone;




    @NotNull(message = "Please enter the email")
    private String email;


    @NotNull(message = "Please enter the contacts")
    @ManyToOne(
            cascade = {CascadeType.DETACH},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    private VendorContact vendorContact;


    @NotNull(message = "Please enter the contacts")
    @ManyToOne(
            cascade = {CascadeType.DETACH},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "main_contact_id", referencedColumnName = "id")
    private VendorContact vendorMainContact;


    @NotNull(message = "Please enter the classement")
    @ManyToOne(
            cascade = {CascadeType.DETACH},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "classement_id", referencedColumnName = "id")
    private VendorClassification classement;



    @NotNull(message = "Please enter the note")
    private String note;
    @NotNull(message = "Please enter the active")
    private String active;


}
