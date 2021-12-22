package dev.fenix.application.production.product.model;

import lombok.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Locale;

@Entity
@Getter
@Setter
@Table(name = "prds__product")
@AllArgsConstructor
@NoArgsConstructor
@ToString
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

  @NotNull(message = "Please enter the productType")
  @ManyToOne(
      cascade = {CascadeType.DETACH},
      fetch = FetchType.EAGER)
  @JoinColumn(name = "product_type_id", referencedColumnName = "id")
  private ProductType productType;

  @ManyToOne(
      cascade = {CascadeType.DETACH},
      fetch = FetchType.EAGER)
  private SiUnit siUnit;

  @Column(columnDefinition = "tinyint(1) default 1")
  private boolean active;


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

  public JSONObject toJson() {
    JSONObject productJSON = new JSONObject();
    try {
      productJSON.put("id", this.getId());
      productJSON.put("name", this.getName());
      productJSON.put("code", this.getCode().toUpperCase(Locale.ROOT));
      productJSON.put("codeDes", this.getCodeDes());
      productJSON.put("classification", this.getClassification().toJson());
      productJSON.put("packaging", this.getPackaging().toJson());
      productJSON.put("productionUnit", this.getProductionUnit().toJson());
      productJSON.put("productType", this.getProductType().toJson());
      productJSON.put("siUnit", this.getSiUnit().toJson());
      productJSON.put("createDate", this.getCreateDate());
      productJSON.put("modifyDate", this.getModifyDate());

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return productJSON;
  }
}
