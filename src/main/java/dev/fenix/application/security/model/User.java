package dev.fenix.application.security.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.fenix.application.business.model.Enterprise;
import dev.fenix.application.person.model.Person;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.ColumnTransformer;
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
import java.util.*;

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
    @Size(min = 4, max = 120, message = "message size must be between 4 and 12")
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


    @Valid
    @JsonBackReference(value = "user-account")
    @OneToOne(mappedBy = "userAccount", cascade = {CascadeType.ALL} , fetch = FetchType.EAGER)
    private Person person;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "user-setting")
    private List<Setting> settings = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "user-activity")
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

    @ManyToMany
    @JoinTable(name = "bz__enterprise_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "enterprises_id"))
    private Set<Enterprise> enterprises = new LinkedHashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "log_in_enterprise_id" )
    @ColumnDefault("1")
    private Enterprise logInEnterprise;

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
            userJSON.put("active", this.isActive());
            if (this.getLogInEnterprise() != null) {
                userJSON.put("logInEnterprise", this.getLogInEnterprise().toSmallJson());
            }


            if (this.getEnterprises() != null) {
                JSONArray enterpriseJSon = new JSONArray();
                for (Enterprise enterprise : this.getEnterprises()) {
                    enterpriseJSon.put(enterprise.toSmallJson());
                }
                userJSON.put("enterprises", enterpriseJSon);
            }

            if (this.getSettings() != null) {
                JSONArray settingsJSon = new JSONArray();
                for (Setting setting : this.getSettings()) {
                    settingsJSon.put(setting.toJson());
                }
                userJSON.put("settings", settingsJSon);
            }
            if (this.getRoles() != null) {
                JSONArray userRoles = new JSONArray();
                for (Role role : this.getRoles()) {
                    userRoles.put(role.toJson());
                }
                userJSON.put("roles", userRoles);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return userJSON;
    }

    public JSONObject toSmallJson() {
        JSONObject userJSON = new JSONObject();
        try {
            userJSON.put("id", this.getId());
            userJSON.put("email", this.getEmail());
            userJSON.put("UserName", this.getUserName());
            userJSON.put("active", this.isActive());
            if (this.getEnterprises() != null) {
                JSONArray enterpriseJSon = new JSONArray();
                for (Enterprise enterprise : this.getEnterprises()) {
                    enterpriseJSon.put(enterprise.toSmallJson());
                }
                userJSON.put("enterprises", enterpriseJSon);
            }

            if (this.getRoles() != null) {
                JSONArray userRoles = new JSONArray();
                for (Role role : this.getRoles()) {
                    userRoles.put(role.toSmallJsonUser());
                }
                userJSON.put("roles", userRoles);
            }
            if (this.getSettings() != null) {
                JSONArray settingsJSon = new JSONArray();
                for (Setting setting : this.getSettings()) {
                    settingsJSon.put(setting.toJson());
                }
                userJSON.put("settings", settingsJSon);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return userJSON;
    }

    public boolean hasEnterprise(Enterprise enterprise) {

        for (Enterprise e : this.getEnterprises()
        ) {
            if (e.getId() == enterprise.getId()) {
                return true;
            }

        }
        return false;


    }

    public JSONObject loginJson() {
        JSONObject userJSON = new JSONObject();
        try {
            userJSON.put("id", this.getId());
            userJSON.put("email", this.getEmail());
            userJSON.put("UserName", this.getUserName());
            userJSON.put("active", this.isActive());
            if (this.getEnterprises() != null) {
                JSONArray enterpriseJSon = new JSONArray();
                for (Enterprise enterprise : this.getEnterprises()) {
                    enterpriseJSon.put(enterprise.toSmallJson());
                }
                userJSON.put("enterprises", enterpriseJSon);
            }
            if (this.getSettings() != null) {
                JSONArray settingsJSon = new JSONArray();
                for (Setting setting : this.getSettings()) {
                    settingsJSon.put(setting.toJson());
                }
                userJSON.put("settings", settingsJSon);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return userJSON;
    }
}
