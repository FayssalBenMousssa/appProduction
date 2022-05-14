package dev.fenix.application.core.model;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
@Getter
@Setter

@Table(name = "core__style")
@Entity
public class Style {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(columnDefinition = "varchar(255) default '#D6D6D6'")
  private String color;

  @Column(columnDefinition = "varchar(255) default 'arrow-right'")
  private String icon;


  public JSONObject toJson() {

    JSONObject styleJSON = new JSONObject();
    try {
      styleJSON.put("id", this.getId());
      styleJSON.put("color", this.getColor());
      styleJSON.put("icon", this.getIcon());
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return styleJSON;
  }

}
