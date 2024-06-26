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

  @Column( name = "background_color" ,columnDefinition = "varchar(255) default '#ffffff'")
  private String backgroundColor;

  @Column(name = "icon_color" ,columnDefinition = "varchar(255) default '#ffffff'")
  private String iconColor;

  @Column(columnDefinition = "varchar(255) default 'file'")
  private String icon;


  public JSONObject toJson() {

    JSONObject styleJSON = new JSONObject();
    try {
      styleJSON.put("id", this.getId());
      styleJSON.put("color", this.getColor());
      styleJSON.put("backgroundColor", this.getBackgroundColor());
      styleJSON.put("iconColor", this.getIconColor());
      styleJSON.put("icon", this.getIcon());
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return styleJSON;
  }

}
