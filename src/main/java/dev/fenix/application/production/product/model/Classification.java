package dev.fenix.application.production.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "prds__classification")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Classification {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "Please enter the name")
  private String name;

  @NotNull(message = "Please enter the code")
  private String code;



  @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
  @JoinColumn(name = "parent_id", referencedColumnName = "id")
  private Classification parent;





  /* fetch = FetchType.LAZY is default in one:many */
  @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "parent")
  @JsonIgnore
  private Set<Classification> children;

  @Column(columnDefinition = "int default 1")
  private long level;

  @Column(columnDefinition = "tinyint(1) default 1")
  private boolean active;

  public Classification(
      Long id,
      @NotNull(message = "Please enter the Name") String name,
      @NotNull(message = "Please enter the Code") String code,
      Classification parent) {
    this.id = id;
    this.name = name;
    this.code = code;
    this.parent = parent;
  }

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_date", updatable = false)
  private Date createDate;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "modify_date")
  private Date modifyDate;


  public JSONObject toJson() {
    JSONObject classificationJSON = new JSONObject();
    try {
      classificationJSON.put("id", this.getId());
      classificationJSON.put("name", this.getName());
      classificationJSON.put("active", this.isActive());
      if (this.getCode() != null) {
        classificationJSON.put("code", this.getCode());
      }

      classificationJSON.put("level", this.getLevel());
      if (this.getParent() != null) {
        JSONObject parent = new JSONObject();
        parent.put("id", this.getParent().getId());
        parent.put("name", this.getParent().getName());
        parent.put("code", this.getParent().getCode());
        parent.put("level", this.getParent().getLevel());
        parent.put("active", this.getParent().isActive());

        if (this.getParent().getParent() != null) {
          JSONObject grandfather = new JSONObject();
          grandfather.put("id", this.getParent().getParent().getId());
          grandfather.put("name", this.getParent().getParent().getName());
          grandfather.put("code", this.getParent().getParent().getCode());
          grandfather.put("level", this.getParent().getParent().getLevel());
          grandfather.put("active", this.getParent().getParent().isActive());
          parent.put("parent", grandfather);
        }

        classificationJSON.put("parent", parent);
      }
      if (this.getChildren() != null) {
        JSONArray children = new JSONArray();
        for (Classification classification : this.getChildren()) {
          if (classification.isActive()) {
            JSONObject child = new JSONObject();
            child.put("id", classification.getId());
            child.put("name", classification.getName());
            child.put("code", classification.getCode());
            child.put("level", classification.getLevel());
            child.put("active", classification.isActive());
            if (classification.getChildren() != null) {
              JSONArray childrenChild = new JSONArray();
              for (Classification childClassification : classification.getChildren()) {
                if (childClassification.isActive()) {
                  JSONObject childOfChild = new JSONObject();
                  childOfChild.put("id", childClassification.getId());
                  childOfChild.put("name", childClassification.getName());
                  childOfChild.put("code", childClassification.getCode());
                  childOfChild.put("level", childClassification.getLevel());
                  childOfChild.put("active", childClassification.isActive());
                  childrenChild.put(childOfChild);
                }
              }
              child.put("children", childrenChild);
            }
            children.put(child);
          }
        }
        classificationJSON.put("children", children);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return classificationJSON;
  }
}
