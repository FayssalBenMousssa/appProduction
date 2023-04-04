package dev.fenix.application.production.product.model;

import dev.fenix.application.production.logistic.model.Depot;
import lombok.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

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


  //bidirectional many-to-one association to depots
  @OneToMany(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
  @JoinColumn(name = "production_unit_id")
  private List<Depot> depots;


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

  @Column(columnDefinition = "tinyint(1) default 1")
  private boolean active;

  public JSONObject toJson() {
    JSONObject productionUnitJSON = new JSONObject();
    try {
      productionUnitJSON.put("id", this.getId());
      productionUnitJSON.put("name", this.getName());

      if (this.getDepots() != null && this.getDepots().size() > 0) {
        JSONArray depotsList = new JSONArray();

        for (Depot depot : this.getDepots()) {

         if (depot != null && depot.getName() != null)
          depotsList.put(depot.toJson());

        }
        productionUnitJSON.put("depots", depotsList);
      }
      productionUnitJSON.put("active", this.isActive());
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return productionUnitJSON;
  }
}
