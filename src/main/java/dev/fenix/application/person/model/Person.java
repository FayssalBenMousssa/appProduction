package dev.fenix.application.person.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.fenix.application.business.model.Staff;
import dev.fenix.application.security.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pe__person")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @NotNull(message = "firstName is mandatory")
  @Column(name = "first_name")
  private String firstName;

  @NotNull(message = "firstName is mandatory")
  @Column(name = "last_name")
  private String lastName;

  @Enumerated(EnumType.STRING)
  @Column(length = 10)
  private Gender gender;



  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  @Valid
  private User userAccount;

  @Column(name = "birth_date")
  private Date birthDate;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_date", updatable = false)
  private Date createDate;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "modify_date")
  private Date modifyDate;

  @OneToMany(mappedBy = "person", cascade = CascadeType.ALL )
  private List<Staff> staffs = new ArrayList<>();

  @Column(name = "global_id")
  private int globalId;

  public Person(
      Long id,
      @NotBlank(message = "firstName is mandatory") String firstName,
      @NotBlank(message = "lastName is mandatory") String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Person() {}

  public JSONObject toJson() {
    JSONObject personJSON = new JSONObject();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    try {
      personJSON.put("id", this.getId());
      personJSON.put("firstName", this.getFirstName());
      personJSON.put("lastName", this.getLastName());
      personJSON.put("fullName", this.getFullName());
      personJSON.put("global_id", this.getGlobalId());

      if (this.getStaffs() != null && this.getStaffs().size() > 0){
        JSONArray staffsJSon = new JSONArray();
        for (Staff staff : this.getStaffs()) {
          staffsJSon.put(staff.toSmallNoPersonJson());
        }
        personJSON.put("staffs", staffsJSon);
      }

      if (this.getGender() != null) {
        personJSON.put("gender", this.getGender());
      }

      if (this.getBirthDate() != null) {
        personJSON.put("birthDate", formatter.format(this.getBirthDate()));
      }
      if (this.getModifyDate() != null) {
        personJSON.put("modifyDate", formatter.format(this.getModifyDate()));
      }
      if (this.getCreateDate() != null) {
        personJSON.put("createDate", formatter.format(this.getCreateDate()));
      }

      if (this.getUserAccount() != null) {
        personJSON.put("user", this.getUserAccount()._toJson());
      }

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return personJSON;
  }

  public String getFullName() {
    return this.getLastName() + " " + this.getFirstName();
  }

  public JSONObject toSmallJsonUser() {
    JSONObject personJSON = new JSONObject();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    try {
      personJSON.put("id", this.getId());
      personJSON.put("firstName", this.getFirstName());
      personJSON.put("lastName", this.getLastName());
      personJSON.put("fullName", this.getFullName());
      personJSON.put("global_id", this.getGlobalId());
      if (this.getUserAccount() != null) {
        personJSON.put("user", this.getUserAccount().toSmallJson());
      }
      if (this.getGender() != null) {
        personJSON.put("gender", this.getGender());
      }

      if (this.getBirthDate() != null) {
        personJSON.put("birthDate", formatter.format(this.getBirthDate()));
      }
      if (this.getModifyDate() != null) {
        personJSON.put("modifyDate", formatter.format(this.getModifyDate()));
      }
      if (this.getCreateDate() != null) {
        personJSON.put("createDate", formatter.format(this.getCreateDate()));
      }

      if (this.getStaffs() != null && this.getStaffs().size() > 0){
        JSONArray staffsJSon = new JSONArray();
        for (Staff staff : this.getStaffs()) {
          staffsJSon.put(staff.toSmallJson());
        }
        personJSON.put("staffs", staffsJSon);
      }

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return personJSON;
  }
  public JSONObject toSmallJson() {
    JSONObject personJSON = new JSONObject();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    try {
      personJSON.put("id", this.getId());
      personJSON.put("firstName", this.getFirstName());
      personJSON.put("lastName", this.getLastName());
      personJSON.put("fullName", this.getFullName());
      personJSON.put("global_id", this.getGlobalId());
      if (this.getGender() != null) {
        personJSON.put("gender", this.getGender());
      }

      if (this.getStaffs() != null && this.getStaffs().size() > 0){
        JSONArray staffsJSon = new JSONArray();
        for (Staff staff : this.getStaffs()) {
          staffsJSon.put(staff.toSmallNoPersonJson());
        }
        personJSON.put("staffs", staffsJSon);
      }
      if (this.getBirthDate() != null) {
        personJSON.put("birthDate", formatter.format(this.getBirthDate()));
      }
      if (this.getModifyDate() != null) {
        personJSON.put("modifyDate", formatter.format(this.getModifyDate()));
      }
      if (this.getCreateDate() != null) {
        personJSON.put("createDate", formatter.format(this.getCreateDate()));
      }


    } catch (JSONException e) {
      e.printStackTrace();
    }
    return personJSON;
  }

    public Object loginJson() {
      JSONObject personJSON = new JSONObject();
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
      try {
        personJSON.put("id", this.getId());
        personJSON.put("firstName", this.getFirstName());
        personJSON.put("lastName", this.getLastName());
        personJSON.put("fullName", this.getFullName());
        personJSON.put("global_id", this.getGlobalId());
        if (this.getUserAccount() != null) {
          personJSON.put("user", this.getUserAccount().loginJson());
        }
        if (this.getStaffs() != null && this.getStaffs().size() > 0){
          JSONArray staffsJSon = new JSONArray();
          for (Staff staff : this.getStaffs()) {
            staffsJSon.put(staff.toSmallNoPersonJson());
          }
          personJSON.put("staffs", staffsJSon);
        }

        if (this.getGender() != null) {
          personJSON.put("gender", this.getGender());
        }

        if (this.getBirthDate() != null) {
          personJSON.put("birthDate", formatter.format(this.getBirthDate()));
        }
        if (this.getModifyDate() != null) {
          personJSON.put("modifyDate", formatter.format(this.getModifyDate()));
        }
        if (this.getCreateDate() != null) {
          personJSON.put("createDate", formatter.format(this.getCreateDate()));
        }


      } catch (JSONException e) {
        e.printStackTrace();
      }
      return personJSON;
    }
}
