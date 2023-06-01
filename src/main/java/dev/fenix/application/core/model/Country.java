package dev.fenix.application.core.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "core__country")
public class Country {

  @javax.persistence.Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", insertable = false, updatable = false)
  private Long id;

  @Column(name = "name", nullable = false, length = 250)
  @NotNull(message = "Please enter Country name")
  private String name;

  @Column(name = "code", nullable = false, length = 250)
  @NotNull(message = "Please enter code")
  private String code;

  @Column(name = "iso_code", nullable = false, length = 250)
  @NotNull(message = "Please enter isoCode")
  private String isoCode;

  @Column(name = "capital", nullable = false, length = 250)
  @NotNull(message = "Please enter Capital")
  private String capital;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "id_country") // we need to duplicate the physical information
  private Set<City> Cities = new HashSet<City>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getIsoCode() {
    return isoCode;
  }

  public void setIsoCode(String isoCode) {
    this.isoCode = isoCode;
  }

  public String getCapital() {
    return capital;
  }

  public void setCapital(String capital) {
    this.capital = capital;
  }

  public Set<City> getCities() {
    return Cities;
  }

  public void setCities(Set<City> cities) {
    Cities = cities;
  }

  @Override
  public String toString() {
    return "Country{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", code='"
        + code
        + '\''
        + ", isoCode='"
        + isoCode
        + '\''
        + ", capital='"
        + capital
        + '\''
        + '}';
  }
}
