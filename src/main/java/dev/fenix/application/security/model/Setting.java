package dev.fenix.application.security.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "sc__setting")
@Getter
@Setter
public class Setting {
  @Id @GeneratedValue private Long id;

  @Column(name = "name", nullable = false)
  private String settingName;

  @Column(name = "value", nullable = false)
  private String settingValue;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  @JsonBackReference(value = "user-setting")
  private User user;
}
