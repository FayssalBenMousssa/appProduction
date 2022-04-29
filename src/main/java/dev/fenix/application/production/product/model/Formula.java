package dev.fenix.application.production.product.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

@Entity
@Getter
@Setter
@Table(name = "prds__formula")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties
public class Formula {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;




  @NotNull(message = "Please enter the code")
  private String code;

  private String name;

  @NotNull(message = "Please enter the product")
  @ManyToOne(
      cascade = {CascadeType.DETACH},
      fetch = FetchType.EAGER)
  @JoinColumn(name = "product_id", referencedColumnName = "id")
  private Product product;

  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true) // javax.persistent.CascadeType
  @Fetch(value = FetchMode.SUBSELECT)
  @JoinColumn(name = "formula_note_id") // parent's foreign key
  private List<FormulaNote> formulaNotes = new ArrayList<>();

  @LazyCollection(LazyCollectionOption.FALSE)
  @Fetch(value = FetchMode.SUBSELECT)
  @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true) // javax.persistent.CascadeType
  @JoinColumn(name = "formula_product_id") // parent's foreign key
  private List<FormulaProduct> formulaProducts = new ArrayList<>();

  @Column(columnDefinition = "tinyint(1) default 1")
  private boolean obsolete;

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
    JSONObject formulaJSON = new JSONObject();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    try {
      formulaJSON.put("id", this.getId());
      formulaJSON.put("code", this.getCode());
      formulaJSON.put("name", this.getName());
      formulaJSON.put("obsolete", this.isObsolete());
      formulaJSON.put("product", this.getProduct().toJson());

      if (this.getModifyDate() != null) {
        formulaJSON.put("modifyDate", formatter.format(this.getModifyDate()));
      }
      if (this.getCreateDate() != null) {
        formulaJSON.put("createDate", formatter.format(this.getCreateDate()));
      }

      if (this.getFormulaProducts() != null) {
        JSONArray formulaProductsList = new JSONArray();
        for (FormulaProduct formulaProduct : this.getFormulaProducts()) {
          if (formulaProduct != null && formulaProduct.isActive()) {
            formulaProductsList.put(formulaProduct.toJson());
          }
        }
        formulaJSON.put("formulaProducts", formulaProductsList);
      }

      if (this.getFormulaNotes() != null) {
        JSONArray formulaNotesList = new JSONArray();
        for (FormulaNote formulaNote : this.getFormulaNotes()) {
          if (formulaNote != null && formulaNote.isActive()) {
            formulaNotesList.put(formulaNote.toJson());
          }
        }
        formulaJSON.put("formulaNotes", formulaNotesList);
      }

      formulaJSON.put("active", this.isActive());
      if (this.getCode() != null) {
        formulaJSON.put("code", this.getCode());
      }

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return formulaJSON;
  }
}
