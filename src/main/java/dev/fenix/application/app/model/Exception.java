package dev.fenix.application.app.model;

import dev.fenix.application.security.model.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Date;
import java.util.Map;

/// Activity on this application
@Entity
@Getter
@Setter
@Table(name = "app__exception")
public class Exception {

  @Id
  @GeneratedValue(strategy  = GenerationType.IDENTITY )
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  @Valid
  private User user;

  private Date timestamp;
  private String status;

  private String error;

  @Type(type = "text")
  private String exception;

  @Column(length = 100000)
  private String trace;

  @Type(type = "text")
  private String message;

  private String path;

  @Column(length = 100000)
  private String errors;

  public Exception(
      @Valid User user,
      Date timestamp,
      String status,
      String error,
      String exception,
      String trace,
      String message,
      String path,
      String errors) {
    this.user = user;
    this.timestamp = timestamp;
    this.status = status;
    this.error = error;
    this.exception = exception;
    this.trace = trace;
    this.message = message;
    this.path = path;
    this.errors = errors;
  }

  public Exception() {}

  public Exception(Map<String, Object> errors, User user) {
    this.user = user;
    this.timestamp = errors.get("status") != null ? (Date) errors.get("timestamp") : new Date();
    this.status = errors.get("status") != null ? errors.get("status").toString() : null;
    this.error = errors.get("error") != null ? errors.get("error").toString() : null;
    this.exception = errors.get("exception") != null ? errors.get("exception").toString() : null;
    this.trace = errors.get("trace") != null ? errors.get("trace").toString() : null;
    this.message = errors.get("message") != null ? errors.get("message").toString() : null;
    this.path = errors.get("path") != null ? errors.get("path").toString() : null;
    this.errors = errors.get("errors") != null ? errors.get("errors").toString() : null;
  }
}
