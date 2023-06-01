package dev.fenix.application.business.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.fenix.application.core.model.Address;
import dev.fenix.application.core.model.Contact;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Table(name = "bz__company")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "company_type")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Company {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @NotNull(message = "Please enter the code")
  private String code;

  @NotNull(message = "Please enter the socialReason")
  @Column(name = "social_reason")
  private String socialReason;

  private String telephone;
  private String email;
  private String ice;
  private Boolean active;

  @Column(name = "company_type", insertable = false, updatable = false)
  private String companyType;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
      name = "bz__company__contact",
      joinColumns = @JoinColumn(name = "id"),
      inverseJoinColumns = @JoinColumn(name = "contact_id"))
  private List<Contact> contacts;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
      name = "bz__company__address",
      joinColumns = @JoinColumn(name = "id"),
      inverseJoinColumns = @JoinColumn(name = "address_id"))
  private List<Address> addresses;

  private String note;

  @Column(name = "create_date")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date createDate;

  @Column(name = "modify_date")
  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date modifyDate;

  public JSONObject toJson() {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    JSONObject companyJSON = new JSONObject();
    try {
      companyJSON.put("id", this.getId());
      companyJSON.put("code", this.getCode());
      companyJSON.put("socialReason", this.getSocialReason());
      companyJSON.put("ice", this.getIce());
      companyJSON.put("companyType", this.getCompanyType());

      if (this.getContacts() != null) {
        JSONArray contacts = new JSONArray();
        for (Contact contact : this.getContacts()) {
          if (contact != null && contact.getActive()) {
            contacts.put(contact.toJson());
          }
        }
        companyJSON.put("contacts", contacts);
      }
      // companyJSON.put("address", this.getAddress().toJson());

      if (this.getAddresses() != null) {
        JSONArray addresses = new JSONArray();
        for (Address address : this.getAddresses()) {
          if (address.isActive()) {
            addresses.put(address.toJson());
          }
        }
        companyJSON.put("addresses", addresses);
      }
      companyJSON.put("telephone", this.getTelephone());
      companyJSON.put("active", this.getActive());
      companyJSON.put("email", this.getEmail());
      companyJSON.put("note", this.getNote());
      if (this.getModifyDate() != null) {
        companyJSON.put("modifyDate", formatter.format(this.getModifyDate()));
      }
      if (this.getCreateDate() != null) {
        companyJSON.put("createDate", formatter.format(this.getCreateDate()));
      }
      // companyJSON.put("mainContact", this.getMainContact().toJson());
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return companyJSON;
  }
}
