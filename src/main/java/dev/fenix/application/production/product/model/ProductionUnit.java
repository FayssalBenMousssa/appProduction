package dev.fenix.application.production.product.model;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "prds__unit")
public class ProductionUnit {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "Please enter the name")
  private String name;

  public ProductionUnit(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  @Column(columnDefinition="tinyint(1) default 1")
  private boolean active;

  public JSONObject toJson() {
    JSONObject personJSON = new JSONObject();
    try {
      personJSON.put("id", this.getId());
      personJSON.put("name", this.getName());
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return personJSON;
  }

  public ProductionUnit() {}
}
