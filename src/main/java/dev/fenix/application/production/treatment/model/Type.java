package dev.fenix.application.production.treatment.model;

import dev.fenix.application.business.model.CompanyType;
import dev.fenix.application.core.model.MetaData;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "trt__doc_type")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Type {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "Please enter the name")
  private String name;

  @NotNull(message = "Please enter the code")
  private String code;

  @NotNull(message = "Please enter the abbreviation")
  private String abbreviation;


  @NotNull(message = "Please enter the code")
  private CompanyType source;

  @NotNull(message = "Please enter the code")
  private CompanyType destination;

  @NotNull(message = "Please enter the type")
  @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
  @JoinColumn(name = "category_id", referencedColumnName = "id")
  private Category category;


  private String color;

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

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "trt__doc_type_metadata",
          joinColumns = @JoinColumn(name = "trt__doc_type_id"),
          inverseJoinColumns = @JoinColumn(name = "metadata_id"))
  private List<MetaData> metaData;

  public JSONObject toJson() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    JSONObject typeJSON = new JSONObject();
    try {
      typeJSON.put("id", this.getId());
      typeJSON.put("name", this.getName());
      typeJSON.put("code", this.getCode());
      typeJSON.put("active", this.isActive());
      typeJSON.put("abbreviation", this.getAbbreviation());
      if (this.getCategory() != null) {
        typeJSON.put("category", this.getCategory().toJson());
      }


      typeJSON.put("source", this.getSource());
      typeJSON.put("destination",this.getDestination());
      typeJSON.put("color", this.getColor());
      if (this.getModifyDate() != null) {
        typeJSON.put("modifyDate", formatter.format(this.getModifyDate()));
      }
      if (this.getCreateDate() != null) {
        typeJSON.put("createDate", formatter.format(this.getCreateDate()));
      }

      if (this.getMetaData() != null && this.getMetaData().size() > 0) {
        JSONArray metadataList = new JSONArray();
        for (MetaData metadata : this.getMetaData()) {

          if (metadata.isActive()) {
            metadataList.put(metadata.toJson());
          }
        }
        typeJSON.put("metaData", metadataList);
      }

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return typeJSON;
  }
}
