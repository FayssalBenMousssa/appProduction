package dev.fenix.application.production.product.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "prds__line")
public class ProductLine {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "Please enter the name")
  private String name;


  @NotNull(message = "Please enter the codeDes")
  private String code;

  public ProductLine(Long id, @NotNull(message = "Please enter the name") String name, @NotNull(message = "Please enter the codeDes") String codeDes) {
    this.id = id;
    this.name = name;
    this.code = code;
  }

  public ProductLine() {}
}
