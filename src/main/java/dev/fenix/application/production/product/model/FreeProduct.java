package dev.fenix.application.production.product.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "prds__free_product")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FreeProduct {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;



  @NotNull(message = "Please enter the value")
   private Double  value;


  private Long qte;

  @NotNull(message = "Please enter the category")
  @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
  @JoinColumn(name = "category_id", referencedColumnName = "id")
  private CategoryPrice category;


  @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
  @JoinColumn(name = "product_id", referencedColumnName = "id")
  @JsonBackReference(value = "freeProducts")
  private Product product;

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
    JSONObject taxJSON = new JSONObject();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    try {
      taxJSON.put("id", this.getId());
      taxJSON.put("qte", this.getQte());
      taxJSON.put("value", this.getValue());
      taxJSON.put("category", this.getCategory().toJson());
      if (this.getModifyDate() != null) {
        taxJSON.put("modifyDate", formatter.format(this.getModifyDate()));
      }
      if (this.getCreateDate() != null) {
        taxJSON.put("createDate", formatter.format(this.getCreateDate()));
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return taxJSON;
  }

}
