package dev.fenix.application.production.product.model;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Locale;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "prds__classification")
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
  private Set<Classification> children;

  @Column(columnDefinition="int default 1")
  private long level;


  @Column(columnDefinition="tinyint(1) default 1")
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

  public Classification() {}

  public JSONObject toJson() {
    JSONObject classificationJSON = new JSONObject();
    try {
      classificationJSON.put("id", this.getId());
      classificationJSON.put("name", this.getName());
      classificationJSON.put("code", this.getCode().toUpperCase(Locale.ROOT));
      classificationJSON.put("level", this.getLevel());
      if (this.getParent() != null) {
        JSONObject parent = new JSONObject();
        parent.put("id", this.getParent().getId());
        parent.put("name", this.getParent().getName());
        parent.put("code", this.getParent().getCode().toUpperCase(Locale.ROOT));
        parent.put("level", this.getParent().getLevel());
        classificationJSON.put("parent", parent);
      }
      if (this.getChildren() != null) {
        JSONArray children = new JSONArray();
        for (Classification classification : this.getChildren()) {
          JSONObject child = new JSONObject();
          child.put("id", classification.getId());
          child.put("name", classification.getName());
          child.put("code", classification.getCode().toUpperCase(Locale.ROOT));
          child.put("level", classification.getLevel());
          children.put(child);
        }
        classificationJSON.put("children", children);
      }

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return classificationJSON;
  }
}
