package dev.fenix.application.production.supplier.model;

import dev.fenix.application.core.model.Address;
import dev.fenix.application.core.model.Contact;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "supl__supplier")
public class Supplier {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "Please enter the code")
  private String code;

  @NotNull(message = "Please enter the socialReason")
  private String socialReason;


  private String telephone;
  private String email;
  private Boolean active;


  @ManyToMany
  @JoinTable(
      name = "supl__contact",
      joinColumns = @JoinColumn(name = "supplier_id"),
      inverseJoinColumns = @JoinColumn(name = "contact_id"))
  private List<Contact> contacts;

  @ManyToMany
  @JoinTable(
      name = "supl__address",
      joinColumns = @JoinColumn(name = "supplier_id"),
      inverseJoinColumns = @JoinColumn(name = "address_id"))
  private List<Address> addresses;


  @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
  @JoinColumn(name = "classification_id", referencedColumnName = "id")
  private SupplierClassification classification;

  private String note;


  public JSONObject toJson() {
    JSONObject vendorJSON = new JSONObject();
    try {
      vendorJSON.put("id", this.getId());
      vendorJSON.put("code", this.getCode());
      vendorJSON.put("socialReason", this.getSocialReason());
      if (this.getContacts() != null) {
        JSONArray contacts = new JSONArray();
        for (Contact contact : this.getContacts()) {
          contacts.put(contact.toJson());
        }
        vendorJSON.put("contacts", contacts);
      }
      // vendorJSON.put("address", this.getAddress().toJson());

      if (this.getAddresses() != null) {
        JSONArray addresses = new JSONArray();
        for (Address address : this.getAddresses()) {
          addresses.put(address.toJson());
        }
        vendorJSON.put("addresses", addresses);
      }
      vendorJSON.put("telephone", this.getTelephone());
      vendorJSON.put("email", this.getEmail());
      vendorJSON.put("classification", this.getClassification().toJson());
      vendorJSON.put("note", this.getNote());
      // vendorJSON.put("mainContact", this.getMainContact().toJson());
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return vendorJSON;
  }
}
