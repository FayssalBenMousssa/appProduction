package dev.fenix.application.production.logistic.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "logistic__vehicle")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "Please enter the name")
  private String name;

  @NotNull(message = "Please enter the code")
  private String code;



  @NotNull(message = "Please enter the active")
  private Boolean active;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_date", updatable = false)
  private Date createDate;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "modify_date")
  private Date modifyDate;

  public JSONObject toJson() {
    JSONObject depotJSON = new JSONObject();
    try {
      depotJSON.put("id", this.getId());
      depotJSON.put("name", this.getName());
      depotJSON.put("code", this.getCode());
      depotJSON.put("active", this.getActive());
      depotJSON.put("createDate", this.getCreateDate());
      depotJSON.put("modifyDate", this.getModifyDate());
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return depotJSON;
  }
}
