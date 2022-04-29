package dev.fenix.application.production.customer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cust__classification")
public class CustomerClassification {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "Please enter the name")
  private String name;

  @NotNull(message = "Please enter the code")
  private String code;

  private Boolean active;

  public JSONObject toJson() {
    JSONObject vendorClassificationJSON = new JSONObject();
    try {
      vendorClassificationJSON.put("id", this.getId());
      vendorClassificationJSON.put("name", this.getName());
      vendorClassificationJSON.put("active", this.getActive());
      vendorClassificationJSON.put("code", this.getCode());

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return vendorClassificationJSON;
  }
}
