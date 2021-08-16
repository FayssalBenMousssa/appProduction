package dev.fenix.application.app.model;

import dev.fenix.application.security.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/// Notification on this application
//// https://mysql.tutorials24x7.com/blog/guide-to-design-database-for-notifications-in-mysql
@Entity
@Table(name = "app__notification")
@Getter
@Setter
public class Notification {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  @Valid
  private User user;

  @NotBlank(message = "title is mandatory")
  private String title;

  @NotBlank(message = "title is mandatory")
  @Column(name = "notification_source")
  private String source;

  @NotBlank(message = "content is mandatory")
  private String content;

  private boolean isRead;
  private boolean isTrash;

  @Column(name = "in_app")
  private Boolean inApp;

  @Column(name = "on_email")
  private Boolean onEmail;

  public Notification(
      @NotBlank(message = "title is mandatory") String title,
      @NotBlank(message = "title is mandatory") String source,
      @NotBlank(message = "content is mandatory") String content,
      boolean isRead,
      boolean isTrash,
      Boolean inApp,
      Boolean onEmail) {

    this.title = title;
    this.source = source;
    this.content = content;
    this.isRead = isRead;
    this.isTrash = isTrash;
    this.inApp = inApp;
    this.onEmail = onEmail;
  }

  public Notification() {}
}
