package dev.fenix.application.security.model;

import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Service
@Table(name = "sc__role")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private int id;

  @Column(name = "role")
  private String role;

  @Column(name = "name")
  private String name;

  @ManyToMany(mappedBy = "roles")
  @OrderBy("orderNum ASC")
  private final Set<Route> routes = new HashSet<>();

  public JSONObject _toJson() {
    JSONObject roleJSON = new JSONObject();
    try {
      roleJSON.put("id", this.getId());
      roleJSON.put("role", this.getRole());

      if (this.getRoutes() != null) {
        JSONArray roleRoutes = new JSONArray();
        for (Route route : this.getRoutes()) {
          roleRoutes.put(route._toJson());
        }
        roleJSON.put("routes", roleRoutes);
      }

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return roleJSON;
  }
}
