package dev.fenix.application.business.model;

import dev.fenix.application.person.model.Person;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bz__team")
@Getter
@Setter
public class Team {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Size(max = 20, min = 3, message = "{user.name.invalid}")
  @NotEmpty(message = "Please enter name")
  private String name;

  @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
  @JoinColumn(name = "leader_id")
  @NotNull(message = "Please enter the leader")
  private Person leader;

  @Fetch(FetchMode.JOIN)
  @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
  @JoinTable(
      name = "bz__team_person",
      joinColumns = {@JoinColumn(name = "team_id")},
      inverseJoinColumns = {@JoinColumn(name = "person_id")})
  private Set<Person> people = new HashSet<>();

  public Team() {}
}
