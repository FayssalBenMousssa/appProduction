package dev.fenix.application.cascade.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dev__factory")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Fac {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private int id;

  @Column(name = "factory_name")
  private String factoryName;

  @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}) // javax.persistent.CascadeType
  @JoinColumn(name = "factory_id") // parent's foreign key
  private List<Pro> products = new ArrayList<>();
}
