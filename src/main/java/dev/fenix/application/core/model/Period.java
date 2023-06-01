package dev.fenix.application.core.model;

import lombok.*;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "core__period")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Period {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "Please enter the startDate")
  @Column(name = "start_date")
  private Date startDate;

  @Column(name = "end_date")
  private Date endDate;

  public JSONObject toJson() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    JSONObject CategoryJSON = new JSONObject();
    try {
      CategoryJSON.put("id", this.getId());
      if (this.getStartDate() != null) {
        CategoryJSON.put("startDate", formatter.format(this.getStartDate()));
      }
      if (this.getEndDate() != null) {
        CategoryJSON.put("endDate", formatter.format(this.getEndDate()));
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return CategoryJSON;
  }
}
