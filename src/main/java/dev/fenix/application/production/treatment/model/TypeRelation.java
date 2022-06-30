package dev.fenix.application.production.treatment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "trt__type_relation" )
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class TypeRelation {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
  @JoinColumn(name = "type_id", referencedColumnName = "id")
  @JsonBackReference
  private Type type;




  @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
  @JoinColumn(name = "type_related_id", referencedColumnName = "id")
  private Type related;

  @Enumerated(EnumType.STRING)
  @Column(length = 30)
  private Operation operation;


  public JSONObject toJson() {
    JSONObject documentJSON = new JSONObject();

    try {
      documentJSON.put("id", this.getId());
      documentJSON.put("operation", this.getOperation());

      if(this.getRelated() != null) {
        documentJSON.put("related", this.getRelated().toJson());
      }

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return documentJSON;
  }
}
