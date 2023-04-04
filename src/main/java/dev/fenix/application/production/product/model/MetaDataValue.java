package dev.fenix.application.production.product.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.fenix.application.core.model.MetaData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "prds__meta_data_value")
@AllArgsConstructor
@NoArgsConstructor

public class MetaDataValue {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "Please enter the value")
  private String value;

  @NotNull(message = "Please enter the metaData")
  @ManyToOne(
      cascade = {CascadeType.DETACH},
      fetch = FetchType.EAGER)
  @JoinColumn(name = "meta_data_id", referencedColumnName = "id")
  private MetaData metadata;



  @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
  @JoinColumn(name = "product_id", referencedColumnName = "id")
  @JsonBackReference
  private Product product;

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
    JSONObject metaDataValueJSON = new JSONObject();
    try {
      metaDataValueJSON.put("id", this.getId());
      metaDataValueJSON.put("value", this.getValue());
      metaDataValueJSON.put("metadata", this.getMetadata().toJson());
      metaDataValueJSON.put("active", this.isActive());

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return metaDataValueJSON;
  }
}
