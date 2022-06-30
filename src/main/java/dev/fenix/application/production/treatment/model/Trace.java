package dev.fenix.application.production.treatment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.fenix.application.security.model.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "trt__doc_trace" )
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Trace {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;


  private String message;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_date", updatable = false)
  private Date createDate;



  @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
  @JoinColumn(name = "document_id", referencedColumnName = "id" , insertable = false, updatable = false)
  @JsonBackReference(value="trace-document")
  private Document document;

  public Trace(User user, String message) {
    this.user = user;
    this.message = message;
  }


  public JSONObject toJson() {
    JSONObject documentJSON = new JSONObject();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    try {
      documentJSON.put("id", this.getId());
      documentJSON.put("message", this.getMessage());
      documentJSON.put("person", this.getUser().getPerson().toSmallJson());

      if (this.getCreateDate() != null) {
        documentJSON.put("createDate", formatter.format(this.getCreateDate()));
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return documentJSON;
  }
}
