package dev.fenix.application.production.product.model;

import lombok.*;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "prds__production_unit")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductionUnit {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "Please enter the name")
  private String name;



  @Column(columnDefinition="tinyint(1) default 1")
  private boolean active;

  public JSONObject toJson() {
    JSONObject productionUnitJSON = new JSONObject();
    try {
      productionUnitJSON.put("id", this.getId());
      productionUnitJSON.put("name", this.getName());
      productionUnitJSON.put("active", this.isActive());
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return productionUnitJSON;
  }


}
