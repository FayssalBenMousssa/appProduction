package dev.fenix.application.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "core__contact")
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "Please enter the  name")
  private String name;

  private String job;
  private String telephone;
  private String email;
  private String note;
  private Boolean active;

  public JSONObject toJson() {
    JSONObject vendorJSON = new JSONObject();
    try {
      vendorJSON.put("id", this.getId());
      vendorJSON.put("name", this.getName());
      vendorJSON.put("job", this.getJob());
      vendorJSON.put("telephone", this.getTelephone());

      vendorJSON.put("email", this.getEmail());
      vendorJSON.put("note", this.getNote());
      vendorJSON.put("active", this.getActive());

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return vendorJSON;
  }
}
