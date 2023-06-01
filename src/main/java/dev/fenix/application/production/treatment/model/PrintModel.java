package dev.fenix.application.production.treatment.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Table(name = "doc__print_model" )


@ToString
@AllArgsConstructor
@NoArgsConstructor

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PrintModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotBlank
  private String name;

  @Column(name ="in_letter_head",columnDefinition = "tinyint(1) default 1")
  private boolean inLetterHead;

  @Column(name = "has_price",columnDefinition = "tinyint(1) default 1")
  private boolean hasPrice;

  @Column(name = "has_batch",columnDefinition = "tinyint(1) default 1")
  private boolean hasBatch;





}
