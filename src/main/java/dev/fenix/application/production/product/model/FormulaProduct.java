package dev.fenix.application.production.product.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "prds__formula_product")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FormulaProduct {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(
      cascade = {CascadeType.DETACH},
      fetch = FetchType.EAGER)
  @JoinColumn(name = "product_id", referencedColumnName = "id")
  private Product product;

  // @NotNull(message = "Please enter the formula_note_id")
  @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
  @JoinColumn(name = "formula_product_id", referencedColumnName = "id")
  @JsonBackReference
  private Formula formulaProduct;

  // @NotNull(message = "Please enter the formula_note_id")
  @ManyToOne(
      cascade = {CascadeType.DETACH},
      fetch = FetchType.EAGER)
  @JoinColumn(name = "si_unit_id", referencedColumnName = "id")
  private SiUnit siUnit;

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

  private float quantity;

  public JSONObject toJson() {
    JSONObject formulaProductJSON = new JSONObject();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    try {
      formulaProductJSON.put("id", this.getId());
      formulaProductJSON.put("quantity", this.getQuantity());
      formulaProductJSON.put("siUnit", this.getSiUnit().toJson());

      if (this.getModifyDate() != null) {
        formulaProductJSON.put("modifyDate", formatter.format(this.getModifyDate()));
      }
      if (this.getCreateDate() != null) {
        formulaProductJSON.put("createDate", formatter.format(this.getCreateDate()));
      }
      formulaProductJSON.put("active", this.isActive());
      if (this.getProduct() != null) {
        formulaProductJSON.put("product", this.getProduct().toJson());
      }

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return formulaProductJSON;
  }
}
