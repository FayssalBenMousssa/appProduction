package dev.fenix.application.cascade.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "dev__product")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Pro {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private int id;

  @Column(name = "product_name")
  private String productName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "factory_id" , nullable = false)
  private Fac factory;
}
