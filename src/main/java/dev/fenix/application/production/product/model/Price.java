package dev.fenix.application.production.product.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.fenix.application.core.model.Period;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Entity
@Getter
@Setter
@Table(name = "prds__price")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Price {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "Please enter the name")
  private String name;

  @NotNull(message = "Please enter the category")
  @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
  @JoinColumn(name = "category_id", referencedColumnName = "id")
  private CategoryPrice category;

  @NotNull(message = "Please enter the Period")
  @OneToOne(cascade = {CascadeType.ALL} )
  @JoinColumn(name = "period_id", referencedColumnName = "id")
  private Period period;

  @NotNull(message = "Please enter the Tax")
  @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
  @JoinColumn(name = "tax_id", referencedColumnName = "id")
  private Tax tax;


  @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
  @JoinColumn(name = "product_id", referencedColumnName = "id")
  @JsonBackReference
  private Product product;

  /*
    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
  @JoinColumn(name = "document_id", referencedColumnName = "id")
  @JsonBackReference
  private Document document;
   */

  @NotNull(message = "Please enter the value")
  private Double  value;

  @NotNull(message = "Please enter the maxQte")
  private int  maxQte;



  @Column(columnDefinition = "tinyint(1) default 1")
  private boolean active;


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
      productJSON.put("name", this.getName());
      productJSON.put("category", this.getCategory().toJson());
      productJSON.put("period", this.getPeriod().toJson());
      productJSON.put("tax", this.getTax().toJson());

      productJSON.put("value", this.getValue());
      productJSON.put("maxQte", this.getMaxQte());
      productJSON.put("active", this.isActive());

      if (this.getModifyDate() != null) {
        productJSON.put("modifyDate", formatter.format(this.getModifyDate()));
      }
      if (this.getCreateDate() != null) {
        productJSON.put("createDate", formatter.format(this.getCreateDate()));
      }



    } catch (JSONException e) {
      e.printStackTrace();
    }
    return productJSON;
  }
}

//// https://www.stackchief.com/blog/One%20To%20Many%20Example%20%7C%20Spring%20Data%20JPA
