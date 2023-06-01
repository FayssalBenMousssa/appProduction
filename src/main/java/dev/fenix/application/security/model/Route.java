package dev.fenix.application.security.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property  = "id", scope = Long.class)
public class Route {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "code")
  private String code;

  @Column(name = "position")
  private String position;

  @Column(name = "route")
  @JsonBackReference
  private String route;

  @Column(name = "icon")
  private String icon;

  @Column(name = "css_class")
  private String cssClass;

  @Column(name = "order_num")
  private int orderNum;

  @Column(name = "color", nullable = false, columnDefinition = "varchar(20) default '#ff0000'")
  private String color;

  @ManyToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "parent_id")
  @NotFound(action = NotFoundAction.IGNORE)
  @JsonBackReference()
  private Route parent;

  @OneToMany(mappedBy = "parent")
  @NotFound(action = NotFoundAction.IGNORE)
  @OrderBy("orderNum ASC")
  @JsonManagedReference
  private List<Route> subRoutes;

  @Column(name = "level", nullable = false, columnDefinition = "int default 0")
  private int level;

  @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @JsonManagedReference(value="role-route")
  @JsonIdentityReference(alwaysAsId = true)
  @JoinTable(
      name = "sc__routes_role",
      joinColumns = @JoinColumn(name = "route_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> rolesRoute;

  public JSONObject toJson( Role role) {

    JSONObject routeJSON = new JSONObject();
    try {
      routeJSON.put("id", this.getId());
      routeJSON.put("name", this.getName());
      routeJSON.put("code", this.getCode());
      routeJSON.put("icon", this.getIcon());
      routeJSON.put("orderNum", this.getOrderNum());
      routeJSON.put("route", this.getRoute());
      routeJSON.put("level", this.getLevel());
      routeJSON.put("color", this.getColor());
      routeJSON.put("position", this.getPosition());
      routeJSON.put("cssClass", this.getCssClass());
      if (this.getSubRoutes() != null) {
        routeJSON.put("sub_routes", jsonSubRoutes(this , role));
      }

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return routeJSON;
  }



  public JSONArray jsonSubRoutes(Route route ,Role role) {
    JSONArray subRoutes = new JSONArray();
    for (Route subRoute : route.getSubRoutes()) {
        for (Role subRole : subRoute.getRolesRoute()) {
          if (role.getId() == subRole.getId()) {
            subRoutes.put(subRoute.toJson(role));
          }
        }
    }
    return subRoutes;
  }
}
