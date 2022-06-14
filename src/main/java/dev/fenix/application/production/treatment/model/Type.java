package dev.fenix.application.production.treatment.model;

import dev.fenix.application.business.model.CompanyType;
import dev.fenix.application.core.model.MetaData;
import dev.fenix.application.core.model.Style;
import dev.fenix.application.production.product.model.CategoryPrice;
import dev.fenix.application.production.product.model.ProductType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "trt__doc_type")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Type {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "Please enter the name")
  private String name;

  @NotNull(message = "Please enter the code")
  private String code;

  @NotNull(message = "Please enter the abbreviation")
  private String abbreviation;


  @NotNull(message = "Please enter the code")
  private CompanyType source;

  @NotNull(message = "Please enter the code")
  private CompanyType destination;

  @NotNull(message = "Please enter the type")
  @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
  @JoinColumn(name = "category_id", referencedColumnName = "id")
  private Category category;


  @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER  )
  @JoinColumn(name = "style_id", referencedColumnName = "id")
  private Style style;


  @Column(columnDefinition = "tinyint(1) default 0")
  private boolean articleTax;

  @Column(columnDefinition = "tinyint(1) default 0")
  private boolean articlePrice;

  @Column(columnDefinition = "tinyint(1) default 1")
  private boolean active;

  @Column(columnDefinition = "tinyint(1) default 1")
  private boolean hasPrice;


  @Column(columnDefinition = "tinyint(1) default 1")
  private boolean hasBatchNumber;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_date", updatable = false)
  private Date createDate;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "modify_date")
  private Date modifyDate;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "trt__doc_type_metadata", joinColumns = @JoinColumn(name = "trt__doc_type_id"),inverseJoinColumns = @JoinColumn(name = "metadata_id"))
  private List<MetaData> metaData;


  @NotNull(message = "Please enter one product types")
  @ManyToMany(cascade = CascadeType.DETACH)
  @JoinTable(name = "trt__doc_type_product_types", joinColumns = @JoinColumn(name = "type_id"),inverseJoinColumns = @JoinColumn(name = "product_type_id"))
  private List<ProductType> productTypes;



  @ManyToMany(cascade = CascadeType.DETACH)
  @JoinTable(name = "trt__doc_type_category_price", joinColumns = @JoinColumn(name = "type_id"),inverseJoinColumns = @JoinColumn(name = "category_price_id"))
  private List<CategoryPrice> categoryPrices;


  @Column(columnDefinition="int(1) default 0")
  private int inAccounting;

  @Column(columnDefinition="int(1) default 0")
  private int inPurchases;

  @Column(columnDefinition="int(1) default 0")
  private int inSales;

  @Column(columnDefinition="int(1) default 0")
  private int inProductions;

  @Column(columnDefinition="int(1) default 0")
  private int inStock;


  public JSONObject toJson() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    JSONObject typeJSON = new JSONObject();
    try {
      typeJSON.put("id", this.getId());
      typeJSON.put("name", this.getName());
      typeJSON.put("code", this.getCode());
      typeJSON.put("active", this.isActive());
      typeJSON.put("abbreviation", this.getAbbreviation());
      typeJSON.put("inAccounting", this.getInAccounting());
      typeJSON.put("inPurchases",this.getInPurchases());
      typeJSON.put("inProductions",this.getInProductions());
      typeJSON.put("inStock",this.getInStock());
      typeJSON.put("inSales",this.getInSales());
      typeJSON.put("hasPrice",this.isHasPrice());
      typeJSON.put("hasBatch",this.isHasBatchNumber());

      if (this.getCategory() != null) { typeJSON.put("category", this.getCategory().toJson()); }
      typeJSON.put("source", this.getSource());
      typeJSON.put("destination",this.getDestination());

      if(this.getStyle() != null){ typeJSON.put("style", this.getStyle().toJson()); }

      if (this.getModifyDate() != null) { typeJSON.put("modifyDate", formatter.format(this.getModifyDate())); }
      if (this.getCreateDate() != null) { typeJSON.put("createDate", formatter.format(this.getCreateDate())); }

      if (this.getMetaData() != null && this.getMetaData().size() > 0) {
        JSONArray metadataList = new JSONArray();
        for (MetaData metadata : this.getMetaData()) {
          if (metadata.isActive()) {
            metadataList.put(metadata.toJson());
          }
        }
        typeJSON.put("metaData", metadataList);
      }

      if (this.getCategoryPrices() != null && this.getCategoryPrices().size() > 0) {
        JSONArray categoryPricesList = new JSONArray();
        for (CategoryPrice categoryPrice : this.getCategoryPrices()) {
          if (categoryPrice.isActive()) {
            categoryPricesList.put(categoryPrice.toJson());
          }
        }
        typeJSON.put("categoryPrices", categoryPricesList);
      }

      if (this.getProductTypes() != null && this.getProductTypes().size() > 0) {
        JSONArray productTypesList = new JSONArray();

        for (ProductType productType : this.getProductTypes()) {
          if (productType.isActive()) {
            productTypesList.put(productType.toJson());
          }
        }
        typeJSON.put("productTypes", productTypesList);
      }

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return typeJSON;
  }
}
