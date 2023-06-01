package dev.fenix.application.production.product.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.*;

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




  @Fetch(value = FetchMode.SELECT)
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "product_id")
  private Set<ProductAttachment> attachments;

  @NotNull(message = "Please enter the packaging")
  @ManyToOne(
      cascade = {CascadeType.DETACH},
      fetch = FetchType.EAGER)
  @JoinColumn(name = "packaging_id", referencedColumnName = "id")
  private Packaging packaging;


  @NotNull(message = "Please enter the ProductMgmtMode")
  @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
  @JoinColumn(name = "product_mgmt_mode_id", referencedColumnName = "id")
  private ProductMgmtMode productMgmtMode;


  @NotNull(message = "Please enter the productionUnits")
  @ManyToMany(
      fetch = FetchType.EAGER,
      cascade = {CascadeType.DETACH})
  @JoinTable(
      name = "prds__product_production_units",
      joinColumns = @JoinColumn(name = "product_id"),
      inverseJoinColumns = @JoinColumn(name = "production_unit_id"))
  private List<ProductionUnit> productionUnits;


  @NotNull(message = "Please enter the imageUrl")
  @Column(name="image_url", columnDefinition="default 'b5946dc8-2482-4cc7-83c4-6ce9b98ce5ea'")
  private String imageUrl;


  @NotNull(message = "Please enter the productType")
  @ManyToOne(
      cascade = {CascadeType.DETACH},
      fetch = FetchType.EAGER)
  @JoinColumn(name = "product_type_id", referencedColumnName = "id")
  private ProductType productType;

  @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
  private SiUnit siUnit;

  private String description;

  @Column(columnDefinition = "tinyint(1) default 1")
  private boolean active;

  @LazyCollection(LazyCollectionOption.FALSE)
  @Fetch(value = FetchMode.SUBSELECT)
  @OneToMany(
      cascade = {CascadeType.ALL},
      orphanRemoval = true) // javax.persistent.CascadeType
  @JoinColumn(name = "product_id") // parent's foreign key
  private List<MetaDataValue> metaDataValues = new ArrayList<>();

  @LazyCollection(LazyCollectionOption.FALSE)
  @Fetch(value = FetchMode.SUBSELECT)
  @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true) // javax.persistent.CascadeType
  @JoinColumn(name = "product_id") // parent's foreign key
  @JsonManagedReference(value = "prices")
  private List<Price> prices = new ArrayList<>();



  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_date", updatable = false)
  private Date createDate;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "modify_date")
  private Date modifyDate;

  public JSONObject toJson() {
    JSONObject productJSON = new JSONObject();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    try {
      productJSON.put("id", this.getId());

      if (this.getModifyDate() != null) {
        productJSON.put("modifyDate", formatter.format(this.getModifyDate()));
      }

      if (this.getProductMgmtMode() != null) {
        productJSON.put("productMgmtMode",  this.getProductMgmtMode().toJson());
      }

      if (this.getCreateDate() != null) {
        productJSON.put("createDate", formatter.format(this.getCreateDate()));
      }
      productJSON.put("id", this.getId());
      productJSON.put("name", this.getName());
      if (this.getCode() != null) {
        productJSON.put("code", this.getCode().toUpperCase(Locale.ROOT));
      }

      if (this.getCodeDes() != null) {
        productJSON.put("codeDes", this.getCodeDes());
      }

      if (this.getClassification() != null) {
        productJSON.put("classification", this.getClassification().toJson());
      }

      if (this.getPackaging() != null) {
        productJSON.put("packaging", this.getPackaging().toJson());
      }

      if (this.getImageUrl() != null) {
        productJSON.put("imageUrl", this.getImageUrl());
      }


      if (this.getAttachments() != null && this.getAttachments().size() > 0) {
        JSONArray attachmentsList = new JSONArray();

        for (ProductAttachment productAttachment : this.getAttachments()) {
          if (productAttachment.isActive()) {
            attachmentsList.put(productAttachment.toJson());
          }
        }
        productJSON.put("attachments", attachmentsList);
      }



      if (this.getProductionUnits() != null && this.getProductionUnits().size() > 0) {
        JSONArray productionUnitList = new JSONArray();
        for (ProductionUnit productionUnit : this.getProductionUnits()) {
          if (productionUnit.isActive()) {
            productionUnitList.put(productionUnit.toJson());
          }
        }
        productJSON.put("productionUnits", productionUnitList);
      }

      if (this.getPrices() != null && this.getPrices().size() > 0) {
        JSONArray pricesList = new JSONArray();
        for (Price price : this.getPrices()) {
          if (price.isActive()) {
            pricesList.put(price.toSmallJson());
          }
        }
        productJSON.put("prices", pricesList);
      }

      if (this.getProductType() != null) {
        productJSON.put("productType", this.getProductType().toJson());
      }

      if (this.getSiUnit() != null) {
        productJSON.put("siUnit", this.getSiUnit().toJson());
      }

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


  public JSONObject toSmallJson() {
    JSONObject productJSON = new JSONObject();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    try {
      productJSON.put("id", this.getId());

      if (this.getModifyDate() != null) {
        productJSON.put("modifyDate", formatter.format(this.getModifyDate()));
      }

      if (this.getProductMgmtMode() != null) {
        productJSON.put("productMgmtMode",  this.getProductMgmtMode().toJson());
      }

      if (this.getCreateDate() != null) {
        productJSON.put("createDate", formatter.format(this.getCreateDate()));
      }
      productJSON.put("id", this.getId());
      productJSON.put("name", this.getName());
      if (this.getCode() != null) {
        productJSON.put("code", this.getCode().toUpperCase(Locale.ROOT));
      }

      if (this.getCodeDes() != null) {
        productJSON.put("codeDes", this.getCodeDes());
      }

      if (this.getClassification() != null) {
        productJSON.put("classification", this.getClassification().toJson());
      }

      if (this.getPackaging() != null) {
        productJSON.put("packaging", this.getPackaging().toJson());
      }


      if (this.getAttachments() != null && this.getAttachments().size() > 0) {
        JSONArray attachmentsList = new JSONArray();

        for (ProductAttachment productAttachment : this.getAttachments()) {
          if (productAttachment.isActive()) {
            attachmentsList.put(productAttachment.toJson());
          }
        }
        productJSON.put("attachments", attachmentsList);
      }



      if (this.getProductionUnits() != null && this.getProductionUnits().size() > 0) {
        JSONArray productionUnitList = new JSONArray();
        for (ProductionUnit productionUnit : this.getProductionUnits()) {
          if (productionUnit.isActive()) {
            productionUnitList.put(productionUnit.toJson());
          }
        }
        productJSON.put("productionUnits", productionUnitList);
      }

      if (this.getPrices() != null && this.getPrices().size() > 0) {
        JSONArray pricesList = new JSONArray();
        for (Price price : this.getPrices()) {
          if (price.isActive()) {
            pricesList.put(price.toSmallJson());
          }
        }
        productJSON.put("prices", pricesList);
      }

      if (this.getProductType() != null) {
        productJSON.put("productType", this.getProductType().toJson());
      }

      if (this.getSiUnit() != null) {
        productJSON.put("siUnit", this.getSiUnit().toJson());
      }

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

  public JSONObject toLabelJson() {
    JSONObject productJSON = new JSONObject();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    try {
      productJSON.put("id", this.getId());
      productJSON.put("name", this.getName());

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return productJSON;
  }

}

//// https://www.stackchief.com/blog/One%20To%20Many%20Example%20%7C%20Spring%20Data%20JPA
