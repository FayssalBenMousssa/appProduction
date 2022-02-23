package dev.fenix.application.cascade.model;

import dev.fenix.application.production.product.model.MetaDataValue;
import dev.fenix.application.production.product.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

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

  //@NotNull(message = "Please enter the product")
  @ManyToOne
  @JoinColumn(name = "cart_id" )

  private A cart;


}
