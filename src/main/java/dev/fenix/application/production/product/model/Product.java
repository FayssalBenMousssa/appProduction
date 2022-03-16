package dev.fenix.application.production.product.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
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

  private String description;

  @Column(columnDefinition = "tinyint(1) default 1")
  private boolean active;





 // @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  @OneToMany(fetch = FetchType.EAGER, cascade ={CascadeType.ALL} , orphanRemoval = true)//javax.persistent.CascadeType
  @JoinColumn(name = "product_id") //parent's foreign key
  private List<MetaDataValue> metaDataValues;



    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date" ,  updatable = false)
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;



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

      if (this.getMetaDataValues() != null) {
        JSONArray metaDataList = new JSONArray();
        for (MetaDataValue metaData : this.getMetaDataValues()) {
          metaDataList.put(metaData.toJson());
        }
        productJSON.put("metaDataValues", metaDataList);
      }

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return productJSON;
  }
}


//// https://www.stackchief.com/blog/One%20To%20Many%20Example%20%7C%20Spring%20Data%20JPA
