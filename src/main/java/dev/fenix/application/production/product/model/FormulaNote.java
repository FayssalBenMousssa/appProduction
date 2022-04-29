package dev.fenix.application.production.product.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.fenix.application.core.model.Note;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import java.text.SimpleDateFormat;
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

  @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
  @JoinColumn(name = "note_id", referencedColumnName = "id")
  private Note note;

  @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
  @JoinColumn(name = "formula_note_id", referencedColumnName = "id")
  @JsonBackReference
  private Formula formulaNote;

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
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    try {
      formulaNoteJSON.put("id", this.getId());
      if (this.getModifyDate() != null) {
        formulaNoteJSON.put("modifyDate", formatter.format(this.getModifyDate()));
      }
      if (this.getCreateDate() != null) {
        formulaNoteJSON.put("createDate", formatter.format(this.getCreateDate()));
      }
      formulaNoteJSON.put("active", this.isActive());
      if (this.getNote() != null) {
        formulaNoteJSON.put("note", this.getNote().toJson());
      }

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return formulaNoteJSON;
  }
}
