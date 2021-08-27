package dev.fenix.application.cascade.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Type;

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

  @Column
  @Type(type = "text")
  @ColumnTransformer(read = "UPPER(creditCardNumber)" , write = "LOWER(?)")

  private String creditCardNumber;


  public A(String name, String creditCardNumber) {
    this.name = name;
    this.creditCardNumber = creditCardNumber;
  }
}
