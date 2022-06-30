package dev.fenix.application.production.treatment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.fenix.application.production.product.model.Product;
import dev.fenix.application.production.product.model.SiUnit;
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
@Table(name = "trt__doc_product")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DocumentProduct {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
  @JoinColumn(name = "product_id", referencedColumnName = "id")
  private Product product;

  @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
  @JoinColumn(name = "batch_id", referencedColumnName = "id")
  private BatchNumber batch;

  @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
  @JoinColumn(name = "document_id", referencedColumnName = "id")
  @JsonBackReference(value="document-product")
  private Document document;


  private Double  tax;
  private Double  price;
  private Double  discount;


  @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
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

        if (this.getPrice() != null) {
            formulaProductJSON.put("price", this.getPrice());
        }

      if (this.getBatch() != null) {
        formulaProductJSON.put("batch", this.getBatch().toJson());
      }

      if (this.getPrice() != null) {
        formulaProductJSON.put("tax", this.getTax());
      }

      if (this.getPrice() != null) {
        formulaProductJSON.put("discount", this.getDiscount());
      }

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
