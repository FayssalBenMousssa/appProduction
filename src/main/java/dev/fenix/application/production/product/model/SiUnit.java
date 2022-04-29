package dev.fenix.application.production.product.model;

import lombok.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "prds__si_unit")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SiUnit {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "Please enter the name")
  private String name;

  @NotNull(message = "Please enter the quantityName")
  private String quantityName;

  @NotNull(message = "Please enter the symbol")
  private String symbol;

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
    JSONObject productUnitJSON = new JSONObject();
    try {
      productUnitJSON.put("id", this.getId());
      productUnitJSON.put("name", this.getName());
      productUnitJSON.put("quantityName", this.getQuantityName());
      productUnitJSON.put("symbol", this.getSymbol());
      productUnitJSON.put("active", this.isActive());

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return productUnitJSON;
  }
}
