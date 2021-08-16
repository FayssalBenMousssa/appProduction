package dev.fenix.application.person.model;

import dev.fenix.application.security.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "pe__person")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@ToString
public class Person {

  @NotNull(message = "firstName is mandatory")
  private String firstName;

  @NotNull(message = "firstName is mandatory")
  private String lastName;

  @Enumerated(EnumType.STRING)
  @Column(length = 10)
  private Gender gender;

  @Column(name = "create_date")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createDate;

  @Column(name = "modify_date")
  private Date modifyDate;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  @Valid
  private User user;

  private Date birthDate;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  public Person(
      Long id,
      @NotBlank(message = "firstName is mandatory") String firstName,
      @NotBlank(message = "lastName is mandatory") String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Person() {}

  @PrePersist
  protected void onCreate() {
    createDate = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    modifyDate = new Date();
  }

  public JSONObject _toJson() {
    JSONObject personJSON = new JSONObject();
    try {
      personJSON.put("id", this.getId());
      personJSON.put("firstName", this.getFirstName());
      personJSON.put("lastName", this.getLastName());
      personJSON.put("fullName", this.getFullName());
      personJSON.put("birthDate", this.getBirthDate());
      personJSON.put("user", user._toJson());

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return personJSON;
  }

  public String getFullName() {
    return this.getFirstName() + " " + this.getLastName();
  }
}
