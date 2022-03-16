package dev.fenix.application.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import java.awt.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "core__notes")
@AllArgsConstructor
@NoArgsConstructor
public class Note {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "note", nullable = false)
  private String note;

  @Column(name = "color", nullable = true)
  private Color color;

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
    JSONObject formulaJSON = new JSONObject();
    try {
      formulaJSON.put("id", this.getId());
      formulaJSON.put("createDate", this.getCreateDate());
      formulaJSON.put("modifyDate", this.getModifyDate());

      formulaJSON.put("active", this.isActive());


    } catch (JSONException e) {
      e.printStackTrace();
    }
    return formulaJSON;
  }

}
