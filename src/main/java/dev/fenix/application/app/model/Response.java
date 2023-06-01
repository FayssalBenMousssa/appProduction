package dev.fenix.application.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.fenix.application.security.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "app__intervention__response")
@AllArgsConstructor
@NoArgsConstructor
public class Response {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "content", nullable = false)
  private String content;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "intervention_id")
  @JsonBackReference(value = "intervention-responses")
  private Intervention intervention;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  @Valid
  private User user;

  boolean active;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_date", updatable = false)
  private Date createDate;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "modify_date")
  private Date modifyDate;

  public JSONObject toJson() {
    JSONObject noteJSON = new JSONObject();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    try {
      noteJSON.put("id", this.getId());
      noteJSON.put("content", this.getContent());
      noteJSON.put("active", this.isActive());
      if(this.getUser() != null){
        noteJSON.put("user", this.getUser().toSmallJson());
      }


      if (this.getModifyDate() != null) {
        noteJSON.put("modifyDate", formatter.format(this.getModifyDate()));
      }
      if (this.getCreateDate() != null) {
        noteJSON.put("createDate", formatter.format(this.getCreateDate()));
      }

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return noteJSON;
  }
}
