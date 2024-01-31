package dev.fenix.application.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;

@Table(name = "core__address_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AddressType {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "code", nullable = false, unique = true)
  private String code;

  @Column(name = "name", nullable = false, unique = true)
  private String name;

  public JSONObject toJson() {
    JSONObject addresstJSON = new JSONObject();
    try {
      addresstJSON.put("id", this.getId());
      addresstJSON.put("name", this.getName());
      addresstJSON.put("code", this.getCode());
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return addresstJSON;
  }

}
