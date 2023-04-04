package dev.fenix.application.app.model;

import dev.fenix.application.security.model.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

/// Activity on this application
@Entity
@Getter
@Setter
@Table(name = "app__intervention")
public class Intervention {

  @Id
  @GeneratedValue(strategy  = GenerationType.IDENTITY )
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  @Valid
  private User user;

  @NotNull(message = "Please enter the priority")
  private Priority priority;

  private String note;
  @NotNull(message = "Please enter the url")
  private String url;
  private String message;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_date", updatable = false)
  private Date createDate;


  public JSONObject toJson() {
    JSONObject interventionJSON = new JSONObject();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    try {
      interventionJSON.put("id", this.getId());
      interventionJSON.put("user", this.getUser().getUserName());

      if(this.getUrl() != null)
       interventionJSON.put("url", this.getUrl());
     if(this.getMessage() != null)
       interventionJSON.put("message", this.getMessage());
     if(this.getNote() !=  null)
       interventionJSON.put("note", this.getNote());
      if(this.getPriority() !=  null)
        interventionJSON.put("priority", this.getPriority());

      if (this.getCreateDate() != null) {
        interventionJSON.put("createDate", formatter.format(this.getCreateDate()));
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return interventionJSON;
  }

}
