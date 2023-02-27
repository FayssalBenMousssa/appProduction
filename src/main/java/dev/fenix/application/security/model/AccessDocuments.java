package dev.fenix.application.security.model;

import com.fasterxml.jackson.annotation.*;
import dev.fenix.application.production.treatment.model.Status;
import dev.fenix.application.production.treatment.model.Type;
import lombok.*;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "sc__role_access_document")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AccessDocuments {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY )
  @JoinColumn(name = "type_id", referencedColumnName = "id")
  private Type type;


  @Enumerated(EnumType.STRING)
  @Column(length = 30)
  private Status status;



  public JSONObject toJson() {
    JSONObject roleJSON = new JSONObject();
    try {
      roleJSON.put("id", this.getId());

      if (this.getType()  != null) roleJSON.put("type", this.getType().toJson());


      if (this.getStatus()  != null) roleJSON.put("status", this.getStatus());

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return roleJSON;
  }




}
