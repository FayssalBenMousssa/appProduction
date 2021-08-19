package dev.fenix.application.production.product.model;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONException;
import org.json.JSONObject;

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

  @NotNull(message = "Please enter the productLine")
  @ManyToOne(
      cascade = {CascadeType.DETACH},
      fetch = FetchType.EAGER)
  @JoinColumn(name = "product_line_id", referencedColumnName = "id")
  private ProductLine productLine;

  @NotNull(message = "Please enter the classification")
  @ManyToOne(
      cascade = {CascadeType.DETACH},
      fetch = FetchType.EAGER)
  @JoinColumn(name = "classification_id", referencedColumnName = "id")
  private Classification classification;

  @NotNull(message = "Please enter the packaging")
  @ManyToOne(
      cascade = {CascadeType.DETACH},
      fetch = FetchType.EAGER)
  @JoinColumn(name = "packaging_id", referencedColumnName = "id")
  private Packaging packaging;

  @NotNull(message = "Please enter the productionUnit")
  @ManyToOne(
      cascade = {CascadeType.DETACH},
      fetch = FetchType.EAGER)
  @JoinColumn(name = "production_unit_id", referencedColumnName = "id")
  private ProductionUnit productionUnit;

  public Product() {}

  public JSONObject toJson() {
    JSONObject productJSON = new JSONObject();
    try {
      productJSON.put("id", this.getId());
      productJSON.put("name", this.getName());
      productJSON.put("code", this.getCode());
      productJSON.put("codeDes", this.getCodeDes());
      productJSON.put("productLine", this.getProductLine().toJson());
      productJSON.put("classification", this.getClassification().toJson());
      productJSON.put("packaging", this.getPackaging().toJson());
      productJSON.put("productionUnit", this.getProductionUnit().toJson());

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return productJSON;
  }
}
