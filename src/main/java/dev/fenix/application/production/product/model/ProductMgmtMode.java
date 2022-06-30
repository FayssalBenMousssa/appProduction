package dev.fenix.application.production.product.model;

import lombok.*;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;

@Entity
@Getter
@Setter
@Table(name = "prds__mgmt_mode")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductMgmtMode {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "Please enter the name")
  private String name;





  public JSONObject toJson() {
    JSONObject taxJSON = new JSONObject();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    try {
      taxJSON.put("id", this.getId());
      taxJSON.put("name", this.getName());
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return taxJSON;
  }

}
