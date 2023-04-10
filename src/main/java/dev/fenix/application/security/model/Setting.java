package dev.fenix.application.security.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;

@Entity
@Table(name = "sc__setting")
@Getter
@Setter
public class Setting {
  @Id @GeneratedValue private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "value", nullable = false)
  private String value;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  @JsonBackReference(value = "user-setting")
  private User user;



  public JSONObject  toJson() {
    JSONObject settingJSON = new JSONObject();
    try {
      settingJSON.put("id", this.getId());
      settingJSON.put("name", this.getName());
      settingJSON.put("value", this.getValue());


    } catch (JSONException e) {
      e.printStackTrace();
    }
    return settingJSON;
  }


}
