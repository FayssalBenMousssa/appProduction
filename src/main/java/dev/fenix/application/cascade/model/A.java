package dev.fenix.application.cascade.model;

import dev.fenix.application.production.product.model.MetaDataValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "dev__a")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class A {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  @Column
  @Type(type = "text")
  @ColumnTransformer(read = "UPPER(creditCardNumber)" , write = "LOWER(?)")

  private String creditCardNumber;



  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
  private List<B> items;

  public A(String name, String creditCardNumber) {
    this.name = name;
    this.creditCardNumber = creditCardNumber;
  }
}
