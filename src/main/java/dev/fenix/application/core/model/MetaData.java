package dev.fenix.application.core.model;

import lombok.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "core__metadata")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class MetaData {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotBlank(message = "code is mandatory")
  private String code;

  @NotBlank(message = "Name is mandatory")
  private String name;

  @NotBlank(message = "type is mandatory")
  private String type;


  private String position;

  @Column(columnDefinition = "tinyint(1) default 1")
  private boolean active;

  @Column(columnDefinition = "tinyint(1) default 1")
  private boolean required;




  private String dataSource;
  private String defaultValue;

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
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    JSONObject metadataJson = new JSONObject();
    try {
      metadataJson.put("id", this.getId());
      metadataJson.put("position", this.getPosition());
      metadataJson.put("name", this.getName());
      metadataJson.put("code", this.getCode());
      metadataJson.put("type", this.getType());
      metadataJson.put("required", this.isRequired());
      if (this.getDefaultValue() != null) {
        metadataJson.put("defaultValue", this.getDefaultValue());
      }
      metadataJson.put("active", this.isActive());
      if (this.getDataSource() != null) {
        metadataJson.put("dataSource",  this.getDataSource() );
      }

      if (this.getModifyDate() != null) {
        metadataJson.put("modifyDate", formatter.format(this.getModifyDate()));
      }
      if (this.getCreateDate() != null) {
        metadataJson.put("createDate", formatter.format(this.getCreateDate()));
      }

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return metadataJson;
  }
}
