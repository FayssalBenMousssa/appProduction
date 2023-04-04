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
@Table(name = "payment__method")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethod {
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
    JSONObject paymentMethod = new JSONObject();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssZ");
    try {
      paymentMethod.put("id", this.getId());
      paymentMethod.put("name", this.getName());
      paymentMethod.put("code", this.getCode());
      paymentMethod.put("active", this.getActive());



      if (this.getModifyDate() != null) {
        paymentMethod.put("modifyDate", formatter.format(this.getModifyDate()));
      }
      if (this.getCreateDate() != null) {
        paymentMethod.put("createDate", formatter.format(this.getCreateDate()));
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return paymentMethod;
  }
}
