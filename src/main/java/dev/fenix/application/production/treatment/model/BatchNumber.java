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
@Table(name = "trt__doc_product_batch")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BatchNumber {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "Please enter the code")
  private String code;

  @NotNull(message = "Please enter the expiration date")
  @Column(name = "expiration_date" , columnDefinition =" date default '2000-01-01'")
  private Date expirationDate ;

  @NotNull(message = "Please enter the production date")
  @Column(name = "production_date" , columnDefinition =" date default '2000-01-01'")
  private Date productionDate ;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_date", updatable = false)
  private Date createDate;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "modify_date")
  private Date modifyDate;

  public JSONObject toJson() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    JSONObject typeJSON = new JSONObject();
    try {
      typeJSON.put("id", this.getId());
      typeJSON.put("code", this.getCode());
      if (this.getExpirationDate() != null) { typeJSON.put("expirationDate", formatter.format(this.getExpirationDate())); }
      if (this.getProductionDate() != null) { typeJSON.put("productionDate", formatter.format(this.getProductionDate())); }
      if (this.getModifyDate() != null) { typeJSON.put("modifyDate", formatter.format(this.getModifyDate())); }
      if (this.getCreateDate() != null) { typeJSON.put("createDate", formatter.format(this.getCreateDate())); }

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return typeJSON;
  }
}
