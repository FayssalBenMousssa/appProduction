package dev.fenix.application.security.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.List;
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


  @Column(name = "color", nullable = false, columnDefinition = "varchar(20) default '#ff0000'")
  private String color ;


  @ManyToOne( cascade = { CascadeType.ALL } )
  @JoinColumn(name = "parent_id")
  @NotFound(action = NotFoundAction.IGNORE)
  private Route parent;

  @OneToMany(mappedBy = "parent")
  @NotFound(action = NotFoundAction.IGNORE)
  @OrderBy("orderNum ASC")
  private List<Route> subRoutes;

  @Column(name = "level", nullable = false, columnDefinition = "int default 0")
  private int level ;


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
      roleJSON.put("level", this.getLevel());
      roleJSON.put("color", this.getColor());

      if (this.getSubRoutes() != null) {
        JSONArray subRoutes  = new JSONArray();
        for (Route rote : this.getSubRoutes()) {
          subRoutes.put(rote._toJson());
        }
        roleJSON.put("sub_routes", subRoutes);
      }

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return roleJSON;
  }
}
