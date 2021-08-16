package dev.fenix.application.person.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "pe__metadata")
@Inheritance(strategy = InheritanceType.JOINED)
public class Metadata {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotBlank(message = "firstName is mandatory")
  private String name;

  @Column(name = "create_date")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createDate;

  @Column(name = "modify_date")
  private Date modifyDate;

  @PrePersist
  protected void onCreate() {
    createDate = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    modifyDate = new Date();
  }
}
