package dev.fenix.application.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "sc__role")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @Column(name = "role")
  private String role;

  @Column(name = "name")
  private String name;


  @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER )
  @JoinTable(
          name = "sc__role_action",
          joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
          inverseJoinColumns = {@JoinColumn(name = "action_id", referencedColumnName = "id")})
  private List<Action> actions = new ArrayList<>();


  @OneToMany(cascade = CascadeType.ALL  ,orphanRemoval = true )
  @LazyCollection(LazyCollectionOption.FALSE)
  @JoinColumn(name = "role_id")
  private List<AccessDocuments> accessDocuments = new ArrayList<>();




  @ManyToMany(mappedBy = "rolesRoute")
  @OrderBy("orderNum ASC")
  // @JsonBackReference(value="role-route")
  // @JsonIdentityReference(alwaysAsId = true)
  @JsonIgnore
  private final List<Route> routes = new ArrayList<Route>();




  public JSONObject toJson() {
    JSONObject roleJSON = new JSONObject();
    try {
      roleJSON.put("id", this.getId());
      roleJSON.put("role", this.getRole());
      roleJSON.put("name", this.getName());

      if (this.getRoutes() != null) {
        JSONArray roleRoutes = new JSONArray();
        for (Route route : this.getRoutes()) {
          if (route.getLevel() == 0) roleRoutes.put(route.toJson(this));
        }
        roleJSON.put("routes", roleRoutes);
      }


       if (this.getActions() != null) {
        JSONArray roleActions = new JSONArray();
        for (Action action : this.getActions()) {
          roleActions.put(action.toJson());
        }
        roleJSON.put("actions", roleActions);
      }

      if (this.getAccessDocuments() != null) {
        JSONArray roleAccessDocuments = new JSONArray();
        for (AccessDocuments roleDocumentType : this.getAccessDocuments()) {
          roleAccessDocuments.put(roleDocumentType.toJson());
        }
        roleJSON.put("accessDocuments", roleAccessDocuments);
      }

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return roleJSON;
  }

  public JSONObject toSmallJson() {
    JSONObject roleJSON = new JSONObject();
    try {
      roleJSON.put("id", this.getId());
      roleJSON.put("role", this.getRole());
      roleJSON.put("name", this.getName());

      if (this.getActions() != null) {
        JSONArray roleActions = new JSONArray();
        for (Action action : this.getActions()) {
          roleActions.put(action.toJson());
        }
        roleJSON.put("actions", roleActions);
      }

      if (this.getAccessDocuments() != null) {
        JSONArray roleAccessDocuments = new JSONArray();
        for (AccessDocuments roleDocumentType : this.getAccessDocuments()) {
          roleAccessDocuments.put(roleDocumentType.toJson());
        }
        roleJSON.put("accessDocuments", roleAccessDocuments);
      }

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return roleJSON;
  }

  public JSONObject toSmallJsonUser() {
    JSONObject roleJSON = new JSONObject();
    try {
      roleJSON.put("id", this.getId());
      roleJSON.put("role", this.getRole());
      roleJSON.put("name", this.getName());

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return roleJSON;
  }
}
