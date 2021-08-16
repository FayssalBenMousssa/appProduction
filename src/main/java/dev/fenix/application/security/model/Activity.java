package dev.fenix.application.security.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

/// Activity on this application
@Entity
@Table(name = "sc__activity")
public class Activity {
  // user
  // Access Type  (Browser, mobile)
  // Location (IP address)   (Browser, mobile)
  // Date/Time (Displayed in your time zone)

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  @Valid
  private User user;

  @NotBlank(message = "Location is mandatory")
  private String location;

  @NotBlank(message = "Location is mandatory")
  private String referer;

  @NotBlank(message = "Location is mandatory")
  private String message;

  @NotBlank(message = "Location is mandatory")
  // DEBUG
  // INFO
  // WARN
  // ERROR
  // FATAL
  private String priority;

  @NotBlank(message = "Location is mandatory")
  private String userAgent;

  @Column(name = "create_date")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createDate;

  public Activity() {}

  public Activity(
      @Valid User user,
      @NotBlank(message = "Location is mandatory") String location,
      @NotBlank(message = "Location is mandatory") String referer,
      @NotBlank(message = "Location is mandatory") String message,
      @NotBlank String priority,
      @NotBlank(message = "Location is mandatory") String userAgent,
      Date createDate) {
    this.user = user;
    this.location = location;
    this.priority = priority;
    this.setReferer(referer);
    this.message = message;
    this.userAgent = userAgent;
    this.createDate = createDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getReferer() {
    return referer;
  }

  public void setReferer(String referer) {

    try {
      URL url = new URL(referer);
      this.referer = url.getFile();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getUserAgent() {
    return userAgent;
  }

  public void setUserAgent(String userAgent) {
    this.userAgent = userAgent;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }
  /*
  host
  user-agent
  referer
  accept-language
   */

}
