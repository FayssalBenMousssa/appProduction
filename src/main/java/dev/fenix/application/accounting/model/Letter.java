package dev.fenix.application.accounting.model;

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
@Table(name = "accounting__letter")
public class Letter {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @NotNull(message = "Please enter the code")
  private String code;


  public JSONObject toJson() {
    JSONObject vendorClassificationJSON = new JSONObject();
    try {
      vendorClassificationJSON.put("id", this.getId());
      vendorClassificationJSON.put("letter", this.getCode());

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return vendorClassificationJSON;
  }
}
