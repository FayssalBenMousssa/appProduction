package dev.fenix.application.cascade.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "dev__b")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class B {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private String name;
}
