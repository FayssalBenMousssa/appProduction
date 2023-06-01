package dev.fenix.application.production.customer.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.fenix.application.business.model.Company;
import dev.fenix.application.core.model.Address;
import dev.fenix.application.core.model.Contact;
import dev.fenix.application.production.product.model.CategoryPrice;
import dev.fenix.application.production.product.model.Price;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cust__customer")


@DiscriminatorValue("CUSTOMER")
public class Customer extends Company {
  private static final Logger log = LoggerFactory.getLogger(Customer.class);

  @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
  @JoinColumn(name = "classification_id", referencedColumnName = "id")
  private CustomerClassification classification;


  @ManyToMany(cascade = CascadeType.ALL ,  fetch = FetchType.EAGER)
  @JoinTable(name = "cust__customer_price", joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "price_id"))
  private List<Price> prices;


  //@OneToMany(mappedBy="customer" ,cascade = CascadeType.PERSIST ,  fetch = FetchType.EAGER)

  @LazyCollection(LazyCollectionOption.FALSE)
  @Fetch(value = FetchMode.SUBSELECT)
  @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
  @JoinColumn(name = "customer_id") // parent's foreign key
  @JsonManagedReference(value = "customer_staff")

  private Set<CustomerStaff> customerStaff;




  @LazyCollection(LazyCollectionOption.FALSE)
  @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
  @JoinColumn(name = "category_price_id", referencedColumnName = "id")
  private CategoryPrice categoryPrice;


  public JSONObject toJson() {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    JSONObject customerJSON = new JSONObject();
    try {
      customerJSON.put("id", this.getId());
      customerJSON.put("code", this.getCode());
      customerJSON.put("socialReason", this.getSocialReason());
      customerJSON.put("ice", this.getIce());

      if (this.getCategoryPrice() != null)
      customerJSON.put( "categoryPrice" , this.getCategoryPrice().toJson());

      if (this.getContacts() != null) {
        JSONArray contacts = new JSONArray();
        for (Contact contact : this.getContacts()) {
          if (contact.getActive()) {
            contacts.put(contact.toJson());
          }
        }
        customerJSON.put("contacts", contacts);
      }


      if (this.getCustomerStaff() != null) {
        JSONArray customerStaffJson = new JSONArray();
        for (CustomerStaff customerStaff : this.getCustomerStaff()) {
          if (customerStaff.isActive()) {
            customerStaffJson.put(customerStaff.toJson());
          }
        }
        customerJSON.put("customerStaff", customerStaffJson);
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
      if (this.getPrices() != null) {
        JSONArray prices = new JSONArray();
        for (Price price : this.getPrices()) {
          if (price.isActive())
            prices.put(price.toJson());
        }
        customerJSON.put("prices", prices);
      }
      if (this.getClassification() != null) {
        customerJSON.put("classification", this.getClassification().toJson());
      }
      customerJSON.put("telephone", this.getTelephone());
      customerJSON.put("active", this.getActive());
      customerJSON.put("email", this.getEmail());
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


  public JSONObject toSmallJson() {



    JSONObject customerJSON = new JSONObject();
    try {
      customerJSON.put("id", this.getId());
      customerJSON.put("code", this.getCode());
      customerJSON.put("socialReason", this.getSocialReason());
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }



    return customerJSON;
  }
}
