package dev.fenix.application.security.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/// Activity on this application

@Entity
@Getter
@Setter
@Table(name = "sc__action")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Action {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  private String code;

  private String name;


  @Column(name = "create_date")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createDate;

  private boolean active;

  public JSONObject toJson() {
    JSONObject actionJSON = new JSONObject();
    try {
      actionJSON.put("id", this.getId());
      actionJSON.put("code", this.getCode());
      actionJSON.put("name", this.getName());
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
    return actionJSON;
  }
}
