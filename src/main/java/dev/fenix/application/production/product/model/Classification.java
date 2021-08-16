package dev.fenix.application.production.product.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "pro__classification")
public class Classification {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "Please enter the name")
  private String name;


  @NotNull(message = "Please enter the codeDes")
  private String codeDes;


  @ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "parent_id", referencedColumnName = "id")
  private Classification parent;

  /* fetch = FetchType.LAZY is default in one:many */
  @OneToMany(cascade = { CascadeType.ALL }, mappedBy="parent")
  private Set<Classification> children;


  public Classification(Long id, @NotNull(message = "Please enter the name") String name, @NotNull(message = "Please enter the codeDes") String codeDes) {
    this.id = id;
    this.name = name;
    this.codeDes = codeDes;
  }

  public Classification() {}
}
