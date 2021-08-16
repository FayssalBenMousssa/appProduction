package dev.fenix.application.business.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "bz__job")
public class Job {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "Please enter the name")
  private String name;

  public Job(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Job() {}
}
