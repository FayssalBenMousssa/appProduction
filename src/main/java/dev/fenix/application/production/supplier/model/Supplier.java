package dev.fenix.application.production.supplier.model;

import dev.fenix.application.business.model.Company;
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
import java.text.SimpleDateFormat;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "supl__supplier")
@DiscriminatorValue("SUPPLIER")
public class Supplier extends Company {

  @ManyToOne(
      cascade = {CascadeType.DETACH},
      fetch = FetchType.EAGER)
  ///  @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
  @JoinColumn(name = "classification_id", referencedColumnName = "id")
  private SupplierClassification classification;

  public JSONObject toJson() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    JSONObject supplierJSON = new JSONObject();
    try {
      supplierJSON.put("id", this.getId());
      supplierJSON.put("code", this.getCode());
      supplierJSON.put("socialReason", this.getSocialReason());
      supplierJSON.put("ice", this.getIce());
      supplierJSON.put("active", this.getActive());
      if (this.getContacts() != null) {
        JSONArray contacts = new JSONArray();
        for (Contact contact : this.getContacts()) {
          if (contact.getActive()) {
            contacts.put(contact.toJson());
          }
        }
        supplierJSON.put("contacts", contacts);
      }
      // supplierJSON.put("address", this.getAddress().toJson());

      if (this.getAddresses() != null) {
        JSONArray addresses = new JSONArray();
        for (Address address : this.getAddresses()) {
          if (address.isActive()) {
            addresses.put(address.toJson());
          }
        }
        supplierJSON.put("addresses", addresses);
      }
      supplierJSON.put("telephone", this.getTelephone());
      supplierJSON.put("email", this.getEmail());
      if(this.getClassification() != null) {
        supplierJSON.put("classification", this.getClassification().toJson());
      }

      supplierJSON.put("note", this.getNote());
      if (this.getModifyDate() != null) {
        supplierJSON.put("modifyDate", formatter.format(this.getModifyDate()));
      }
      if (this.getCreateDate() != null) {
        supplierJSON.put("createDate", formatter.format(this.getCreateDate()));
      }
      // supplierJSON.put("mainContact", this.getMainContact().toJson());
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return supplierJSON;
  }
}
