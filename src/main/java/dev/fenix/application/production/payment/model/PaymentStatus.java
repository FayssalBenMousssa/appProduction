package dev.fenix.application.production.payment.model;

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
@Table(name = "payment__status")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PaymentStatus {
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
    JSONObject paymentStatus = new JSONObject();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssZ");
    try {
      paymentStatus.put("id", this.getId());
      paymentStatus.put("name", this.getName());
      paymentStatus.put("code", this.getCode());
      paymentStatus.put("active", this.getActive());



      if (this.getModifyDate() != null) {
        paymentStatus.put("modifyDate", formatter.format(this.getModifyDate()));
      }
      if (this.getCreateDate() != null) {
        paymentStatus.put("createDate", formatter.format(this.getCreateDate()));
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return paymentStatus;
  }
}
