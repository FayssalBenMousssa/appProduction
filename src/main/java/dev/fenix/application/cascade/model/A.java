package dev.fenix.application.cascade.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "dev__a")
@Getter
@Setter
@ToString
public class A {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  public A(String name) {
    this.name = name;
  }
}
