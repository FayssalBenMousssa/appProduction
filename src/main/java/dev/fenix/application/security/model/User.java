package dev.fenix.application.security.model;

import dev.fenix.application.person.model.Person;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Type;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "sc__users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotBlank(message = "userName is mandatory")
  @NotNull(message = "userName cannot be null")
  @Size(min = 4, max = 120, message = "message size must be between 2 and 12")
  @Type(type = "text")
  @Column(name = "user_name")
  @ColumnTransformer(forColumn = "user_name", read = "LOWER(user_name)", write = "LOWER(?)")
  private String userName;

  // @NotBlank(message = "userpassword is mandatory")
  // @Pattern(message="Minimum eight characters, at least one letter and one number", regexp =
  // "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$")
  @Size(min = 6, max = 20, message = "message size must be between 6 and 20")
  // @Transient
  @NotNull(message = "user * password cannot be null")
  private String userpassword;

  private String password;

  private boolean active;

  @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @JoinTable(
      name = "sc__user_role",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles;

  @OneToOne(
      mappedBy = "user",
      fetch = FetchType.LAZY,
      cascade = {CascadeType.ALL})
  @Valid
  private Person person;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Setting> settings = new ArrayList<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Activity> activities = new ArrayList<>();

  @NotBlank(message = "email is mandatory")
  @Email(message = "Not email")
  private String email;

  @Column(name = "create_date")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createDate;

  @Column(name = "modify_date")
  private Date modifyDate;

  @Column(name = "reset_password_token")
  private String resetPasswordToken;

  /*
  ///// todo https://www.codejava.net/frameworks/spring-boot/spring-security-limit-login-attempts-example
  @Column(name = "account_locked" , columnDefinition="default '0'"  )
  private boolean accountLocked;

  @Column(name = "failed_attempt")
  private int failedAttempt;

  @Column(name = "lock_time")
  private Date lockTime;*/

  @PrePersist
  protected void onCreate() {
    createDate = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    modifyDate = new Date();
  }

  public void CryptPassword() {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
    this.password = passwordEncoder.encode(this.userpassword);
  }

  public JSONObject _toJson() {
    JSONObject userJSON = new JSONObject();
    try {
      userJSON.put("id", this.getId());
      userJSON.put("email", this.getEmail());
      userJSON.put("UserName", this.getUserName());
      if (this.getRoles() != null) {
        JSONArray userRoles = new JSONArray();
        for (Role role : this.getRoles()) {
          userRoles.put(role._toJson());
        }
        userJSON.put("roles", userRoles);
      }

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return userJSON;
  }
}
