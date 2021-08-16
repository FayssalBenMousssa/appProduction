package dev.fenix.application.production.product.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "pro__packaging")
public class Packaging {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "Please enter the name")
  private String name;

  public Packaging(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Packaging() {}
}
