package dev.fenix.application.production.product.model;


import dev.fenix.application.core.model.Metadata;
import lombok.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "prds__type")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductType {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "Please enter the name")
  private String name;

  @Column(columnDefinition="tinyint(1) default 1")
  private boolean active;

  private String icon;

  public ProductType(Long id, String name) {
    this.id = id;
    this.name = name;
  }


  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
          name = "prds__type_metadata",
          joinColumns = @JoinColumn(name = "product_type_id"),
          inverseJoinColumns = @JoinColumn(name = "metadata_id"))
  private List<Metadata> metadataList;

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
    JSONObject ProductTypeJSON = new JSONObject();
    try {
      ProductTypeJSON.put("id", this.getId());
      ProductTypeJSON.put("name", this.getName());
      ProductTypeJSON.put("icon", this.getIcon());

      if (this.getMetadataList().size() > 0 ){
        JSONArray metadatalist = new JSONArray();
        for (Metadata metadata : this.getMetadataList()) {
          if(metadata.isActive()) {
            metadatalist.put(metadata.toJson());
          }

        }
        ProductTypeJSON.put("meta_data", metadatalist);
      }
      
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return ProductTypeJSON;
  }
}
