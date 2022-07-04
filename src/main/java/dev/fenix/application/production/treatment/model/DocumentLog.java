package dev.fenix.application.production.treatment.model;

import com.fasterxml.jackson.annotation.*;
import dev.fenix.application.business.model.Company;
import dev.fenix.application.security.model.User;
import lombok.*;
import org.hibernate.annotations.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "trt__doc_log" )


@ToString
@AllArgsConstructor
@NoArgsConstructor

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class DocumentLog {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "Please enter the action")
  private Action action;

  @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
  @JoinColumn(name = "document_id", referencedColumnName = "id")
  @JsonBackReference(value="document-log")
  private Document document;


  private String userName;

  private String message;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_date", updatable = false)
  private Date createDate;

  public DocumentLog(@NotNull(message = "Please enter the action") Action action, Document document, String message , String userName) {
    this.action = action;
    this.document = document;
this.userName = userName;
    this.message = message;
  }


  public JSONObject toJson() {
    JSONObject documentJSON = new JSONObject();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    try {
      documentJSON.put("id", this.getId());
      documentJSON.put("message", this.getMessage());
      documentJSON.put("userName", this.getUserName());
      documentJSON.put("action", this.getAction());
      if (this.getCreateDate() != null) {
        documentJSON.put("createDate", formatter.format(this.getCreateDate()));
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return documentJSON;
  }


}
