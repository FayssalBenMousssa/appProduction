package dev.fenix.application.production.objective.model;

import dev.fenix.application.business.model.Staff;
import dev.fenix.application.production.product.model.Product;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "obj__objective")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Objective {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;


  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "product_id")
  private Product product;


  @ManyToOne
  @JoinColumn(name = "staff_id")
  private Staff staff;

  @Column(name = "value")
  private Float value;


  @NotNull(message = "Please enter the startDate")
  @Column(name = "start_date")
  private Date startDate;

  @NotNull(message = "Please enter the endDate")
  @Column(name = "end_date")
  private Date endDate;

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
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    try {
      depotJSON.put("id", this.getId());
      depotJSON.put("active", this.getActive());
      depotJSON.put("value", this.getValue());
      depotJSON.put("startDate", formatter.format(this.getStartDate()));
      depotJSON.put("endDate", formatter.format(this.getEndDate()));
      depotJSON.put("staff",  staff.toSmallJson());
      depotJSON.put("product",  product.toLabelJson());

      depotJSON.put("createDate", this.getCreateDate());
      depotJSON.put("modifyDate", this.getModifyDate());

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return depotJSON;
  }
}
