package dev.fenix.application.core.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "core__city")
public class City {

  @javax.persistence.Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", insertable = false, updatable = false)
  private Long id;

  @Column(name = "name", nullable = false, length = 250)
  @NotNull(message = "Please enter the name")
  private String name;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @NotNull(message = "Please enter the country")
  @JoinColumn(name = "id_country")
  private Country country;

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

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }
}
