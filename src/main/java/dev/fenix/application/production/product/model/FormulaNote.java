package dev.fenix.application.production.product.model;

import dev.fenix.application.core.model.Note;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "prds__formula_note")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FormulaNote {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(
      cascade = {CascadeType.DETACH},
      fetch = FetchType.EAGER)
  @JoinColumn(name = "note_id", referencedColumnName = "id")
  private Note note;

  @ManyToOne(
      cascade = {CascadeType.DETACH},
      fetch = FetchType.EAGER)
  @JoinColumn(name = "formula_id", referencedColumnName = "id")
  private Formula formula;

  @Column(columnDefinition = "tinyint(1) default 1")
  private boolean active;


  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_date", updatable = false)
  private Date createDate;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "modify_date")
  private Date modifyDate;

  public JSONObject toJson() {
    JSONObject formulaNoteJSON = new JSONObject();
    try {
      formulaNoteJSON.put("id", this.getId());
      formulaNoteJSON.put("active", this.isActive());
      formulaNoteJSON.put("createDate", this.getCreateDate());
      formulaNoteJSON.put("modifyDate", this.getModifyDate());

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return formulaNoteJSON;
  }
}
