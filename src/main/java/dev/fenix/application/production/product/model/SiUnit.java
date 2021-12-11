package dev.fenix.application.production.product.model;

import lombok.*;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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


  @Column(columnDefinition="tinyint(1) default 1")
  private boolean active;

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
