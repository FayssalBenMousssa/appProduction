package dev.fenix.application.production.product.model;

import lombok.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "prds__production_unit")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductionUnit {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "Please enter the name")
  private String name;


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


  @Column(columnDefinition="tinyint(1) default 1")
  private boolean active;

  public JSONObject toJson() {
    JSONObject productionUnitJSON = new JSONObject();
    try {
      productionUnitJSON.put("id", this.getId());
      productionUnitJSON.put("name", this.getName());
      productionUnitJSON.put("active", this.isActive());
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return productionUnitJSON;
  }


}
