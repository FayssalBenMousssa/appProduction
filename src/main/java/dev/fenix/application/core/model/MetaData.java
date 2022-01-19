package dev.fenix.application.core.model;

import lombok.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Locale;

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

  @Column(columnDefinition = "tinyint(1) default 1")
  private boolean active;

  @Column(columnDefinition = "tinyint(1) default 1")
  private boolean required;


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
    JSONObject productJSON = new JSONObject();
    try {
      productJSON.put("id", this.getId());
      productJSON.put("name", this.getName());
      productJSON.put("code", this.getCode());
      productJSON.put("type", this.getType());
      productJSON.put("create_date", this.getCreateDate());
      productJSON.put("modify_date", this.getModifyDate());

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return productJSON;
  }
}
