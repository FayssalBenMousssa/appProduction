package dev.fenix.application.security.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Service
@Table(name = "sc__role")
@ToString
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
  private final List<Route> routes = new ArrayList<>();

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

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return roleJSON;
  }
}
