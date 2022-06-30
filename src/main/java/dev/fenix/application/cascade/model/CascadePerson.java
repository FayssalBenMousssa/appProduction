package dev.fenix.application.cascade.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cascade__person")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CascadePerson {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String name;

  @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
  private List<CascadeAddress> addresses;
  }

