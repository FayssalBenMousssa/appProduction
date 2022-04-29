package dev.fenix.application.business.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
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
@Table(name = "bz__job")
public class Job {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "Please enter the name")
  private String name;

  private Boolean active;

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

  public Job() {}

  public JSONObject toJson() {
    JSONObject jobJSON = new JSONObject();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    try {
      jobJSON.put("id", this.getId());
      jobJSON.put("name", this.getName());
      if (this.getModifyDate() != null) {
        jobJSON.put("modifyDate", formatter.format(this.getModifyDate()));
      }
      if (this.getCreateDate() != null) {
        jobJSON.put("createDate", formatter.format(this.getCreateDate()));
      }
      jobJSON.put("active", this.getActive());

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return jobJSON;
  }
}
