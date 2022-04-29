package dev.fenix.application.production.treatment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.fenix.application.core.model.MetaData;
import lombok.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "trt__doc_data_value")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DocumentDataValue {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "Please enter the value")
  private String value;

  @NotNull(message = "Please enter the metaData")
  @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
  @JoinColumn(name = "meta_data_id", referencedColumnName = "id")
  private MetaData metaData;

  @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
  @JoinColumn(name = "document_id", referencedColumnName = "id")
  @JsonBackReference
  private Document document;

  @Column(columnDefinition = "tinyint(1) default 1")
  private boolean active;

  @Column(name = "create_date")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createDate;

  @Column(name = "modify_date")
  private Date modifyDate;

  @PrePersist
  protected void onCreate() {
    createDate = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    modifyDate = new Date();
  }

  public JSONObject toJson() {
    JSONObject metaDataValueJSON = new JSONObject();
    try {
      metaDataValueJSON.put("id", this.getId());
      metaDataValueJSON.put("value", this.getValue());
      metaDataValueJSON.put("metaData", this.getMetaData().toJson());
      metaDataValueJSON.put("active", this.isActive());

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return metaDataValueJSON;
  }
}
