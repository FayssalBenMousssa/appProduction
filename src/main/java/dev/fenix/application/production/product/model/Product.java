package dev.fenix.application.production.product.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "prds__product")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "Please enter the name")
  private String name;

  @NotNull(message = "Please enter the code")
  private String code;

  @NotNull(message = "Please enter the code")
  private String codeDes;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "product_line_id", referencedColumnName = "id")
  private ProductLine productLine;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "classification_id", referencedColumnName = "id")
  private Classification classification;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "packaging_id", referencedColumnName = "id")
  private Packaging packaging;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "production_unit_id", referencedColumnName = "id")
  private ProductionUnit productionUnit;

  public Product() {}
}
