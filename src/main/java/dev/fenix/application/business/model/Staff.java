package dev.fenix.application.business.model;

import dev.fenix.application.person.model.Person;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;

@Entity
@Table(name = "bz__staff")
@Getter
@Setter
public class Staff {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne()
  @NotNull(message = "{staff.job.null}")
  @JoinColumn(name = "job_id", nullable = false)
  private Job job;

  @ManyToOne()
  @NotNull(message = "{staff.personnel.null}")
  @JoinColumn(name = "personnel_id", nullable = false)
  private Person personnel;

  @NotNull(message = "{staff.start_date.null}")
  @PastOrPresent(message = "{staff.start_date.invalid}")
  private Date startDate;

  private Date endDate;

  public Staff() {}
}
