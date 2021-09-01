package dev.fenix.application.production.product.model;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Locale;

@Entity
@Getter
@Setter
@Table(name = "prds__line")
public class ProductLine {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "Please enter the name")
  private String name;

  @NotNull(message = "Please enter the codeDes")
  private String code;


  public ProductLine(Long id, @NotNull(message = "Please enter the name") String name) {
    this.id = id;
    this.name = name;
  }

  @Column(columnDefinition="tinyint(1) default 1")
  private boolean active;

  public ProductLine() {}

  public JSONObject toJson() {
    JSONObject personJSON = new JSONObject();
    try {
      personJSON.put("id", this.getId());
      personJSON.put("name", this.getName());
      personJSON.put("code", this.getCode().toUpperCase(Locale.ROOT));
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return personJSON;
  }
}
