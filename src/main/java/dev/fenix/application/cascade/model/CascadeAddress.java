package dev.fenix.application.cascade.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "cascade__address")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CascadeAddress {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String street;
  private int houseNumber;
  private String city;
  private int zipCode;

  @ManyToOne(fetch = FetchType.LAZY)
  private CascadePerson person;
}
