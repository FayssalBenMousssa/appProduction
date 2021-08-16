package dev.fenix.application.core.model;

import javax.persistence.*;

@Table(name = "core__tag")
@Entity
public class Tag {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "parent_id")
  private Tag tag;

  @Column(name = "name", nullable = false, unique = true)
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Tag getTag() {
    return tag;
  }

  public void setTag(Tag tag) {
    this.tag = tag;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
