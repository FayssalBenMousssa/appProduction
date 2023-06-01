package dev.fenix.application.production.customer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import dev.fenix.application.business.model.Staff;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cust__customer_staff")



public class CustomerStaff  {
  private static final Logger log = LoggerFactory.getLogger(CustomerStaff.class);


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne()
  @JoinColumn(name = "customer_id")
  @JsonBackReference(value = "customer_staff")
  private Customer customer;

  @ManyToOne()
  @JoinColumn(name = "staff_id", nullable = false)
  private Staff staff;


  @NotNull(message = "Please enter the startDate")
  @Column(name = "start_date")
  private Date startDate;

  @Column(name = "end_date")
  private Date endDate;


  private boolean active;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  @Column(name = "create_date", updatable = false)
  private Date createDate;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  @Column(name = "modify_date")
  private Date modifyDate;


  public JSONObject toJson() {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    JSONObject customerStaffJSON = new JSONObject();
    try {
      customerStaffJSON.put("id", this.getId());
      customerStaffJSON.put("startDate", formatter.format(this.getStartDate()));
      customerStaffJSON.put("staff",  staff.toSmallJson());
      customerStaffJSON.put("active", this.isActive());
      if (this.getEndDate() != null) {
        customerStaffJSON.put("endDate", formatter.format(this.getEndDate()));
      }
      if (this.getModifyDate() != null) {
        customerStaffJSON.put("modifyDate", formatter.format(this.getModifyDate()));
      }
      if (this.getCreateDate() != null) {
        customerStaffJSON.put("createDate", formatter.format(this.getCreateDate()));
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return customerStaffJSON;
  }



}
