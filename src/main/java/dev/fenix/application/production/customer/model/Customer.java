package dev.fenix.application.production.customer.model;

import dev.fenix.application.core.model.Address;
import dev.fenix.application.business.model.Company;
import dev.fenix.application.core.model.Contact;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cust__customer")


@DiscriminatorValue("CUSTOMER")
public class Customer extends Company {

  @ManyToOne(
      cascade = {CascadeType.DETACH},
      fetch = FetchType.EAGER)
  ///  @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
  @JoinColumn(name = "classification_id", referencedColumnName = "id")
  private CustomerClassification classification;


  public JSONObject toJson() {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    JSONObject customerJSON = new JSONObject();
    try {
      customerJSON.put("id", this.getId());
      customerJSON.put("code", this.getCode());
      customerJSON.put("socialReason", this.getSocialReason());
      customerJSON.put("ice", this.getIce());


      if (this.getContacts() != null) {
        JSONArray contacts = new JSONArray();
        for (Contact contact : this.getContacts()) {
          if (contact.getActive()) {
            contacts.put(contact.toJson());
          }
        }
        customerJSON.put("contacts", contacts);
      }
      // customerJSON.put("address", this.getAddress().toJson());

      if (this.getAddresses() != null) {
        JSONArray addresses = new JSONArray();
        for (Address address : this.getAddresses()) {
          if (address.isActive()) {
            addresses.put(address.toJson());
          }
        }
        customerJSON.put("addresses", addresses);
      }
      customerJSON.put("telephone", this.getTelephone());
      customerJSON.put("active", this.getActive());
      customerJSON.put("email", this.getEmail());
      if (this.getClassification() != null) {
        customerJSON.put("classification", this.getClassification().toJson());
      }
      customerJSON.put("note", this.getNote());
      if (this.getModifyDate() != null) {
        customerJSON.put("modifyDate", formatter.format(this.getModifyDate()));
      }
      if (this.getCreateDate() != null) {
        customerJSON.put("createDate", formatter.format(this.getCreateDate()));
      }
      // customerJSON.put("mainContact", this.getMainContact().toJson());
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return customerJSON;
  }
}
