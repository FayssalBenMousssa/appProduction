package dev.fenix.application.business.model;

import dev.fenix.application.person.model.Person;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "bz__staff")
@Getter
@Setter
public class Staff {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne()
  @NotNull(message = "{staff.job.null}")
  @JoinColumn(name = "job_id", nullable = false)
  private Job job;


  @NotNull(message = "{staff.personnel.null}")
  @JoinColumn(name = "personnel_id", nullable = false)
  @ManyToOne(fetch = FetchType.EAGER, cascade ={CascadeType.ALL} )
  private Person person;

  private Boolean active;
  @NotNull(message = "{staff.start_date.null}")
 // @PastOrPresent(message = "{staff.start_date.invalid}")
  private Date startDate;

  private Date endDate;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_date" ,  updatable = false)
  private Date createDate;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "modify_date")
  private Date modifyDate;


  public Staff() {}

  public JSONObject toJson() {
    JSONObject staffJSON = new JSONObject();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    try {
      staffJSON.put("id", this.getId());
      staffJSON.put("person", this.getPerson().toJson());
      staffJSON.put("job", this.getJob().toJson());


      if (this.getStartDate() != null) {
        staffJSON.put("startDate",   formatter.format(this.getStartDate()))  ;
      }
      if (this.getEndDate() != null) {
        staffJSON.put("endDate",   formatter.format(this.getEndDate()))  ;
      }
      if (this.getModifyDate() != null) {
        staffJSON.put("modifyDate",   formatter.format(this.getModifyDate()))  ;
      }
      if (this.getCreateDate() != null) {
        staffJSON.put("createDate", formatter.format(this.getCreateDate()));
      }
      staffJSON.put("active", this.getActive());

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return staffJSON;
  }


}
