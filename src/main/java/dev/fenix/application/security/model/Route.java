package dev.fenix.application.security.model;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sc__routes")
@Getter
@Setter
public class Route {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "route")
  private String route;

  @Column(name = "icon")
  private String icon;

  @Column(name = "orderNum")
  private int orderNum;

  @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @JoinTable(
      name = "sc__routes_role",
      joinColumns = @JoinColumn(name = "route_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles;

  public JSONObject _toJson() {

    JSONObject roleJSON = new JSONObject();
    try {
      roleJSON.put("id", this.getId());
      roleJSON.put("name", this.getName());
      roleJSON.put("icon", this.getIcon());
      roleJSON.put("orderNum", this.getOrderNum());
      roleJSON.put("route", this.getRoute());

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return roleJSON;
  }
}
