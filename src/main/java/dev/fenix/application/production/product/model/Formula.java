package dev.fenix.application.production.product.model;

import lombok.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "prds__formula")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Formula {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "Please enter the code")
  private String code;



  private String note;


  @Column(columnDefinition = "tinyint(1) default 1")
  private boolean Obsolete;

  @Column(columnDefinition = "tinyint(1) default 1")
  private boolean active;


  @Column(name = "create_date")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createDate;
  @Column(name = "modify_date")
  private Date modifyDate;


  @PrePersist
  protected void onCreate() {
    createDate = new Date();
  }
  @PreUpdate
  protected void onUpdate() {
    modifyDate = new Date();
  }

  public JSONObject toJson() {
    JSONObject classificationJSON = new JSONObject();
    try {
      classificationJSON.put("id", this.getId());

      classificationJSON.put("active", this.isActive());
      if (this.getCode() != null) {
        classificationJSON.put("code", this.getCode());
      }


    } catch (JSONException e) {
      e.printStackTrace();
    }
    return classificationJSON;
  }
}
