package dev.fenix.application.production.product.model;

import dev.fenix.application.core.model.Note;
import dev.fenix.application.security.model.Activity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "prds__formula")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Formula {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "Please enter the code")
  private String code;

  private String note;

  @OneToMany(mappedBy = "formula", cascade = CascadeType.ALL)
  private List<FormulaNote> formulaNotes = new ArrayList<>();

  @Column(columnDefinition = "tinyint(1) default 1")
  private boolean Obsolete;

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
    JSONObject formulaJSON = new JSONObject();
    try {
      formulaJSON.put("id", this.getId());
      formulaJSON.put("createDate", this.getCreateDate());
      formulaJSON.put("modifyDate", this.getModifyDate());

      formulaJSON.put("active", this.isActive());
      if (this.getCode() != null) {
        formulaJSON.put("code", this.getCode());
      }

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return formulaJSON;
  }
}
