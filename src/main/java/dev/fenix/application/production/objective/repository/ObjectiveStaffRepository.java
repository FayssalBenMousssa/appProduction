package dev.fenix.application.production.objective.repository;

import dev.fenix.application.business.model.Staff;
import dev.fenix.application.production.objective.model.Objective;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ObjectiveStaffRepository extends JpaRepository<Objective, Long> {
  Page<Objective> findByActiveTrue(Pageable paging);
  int countByActiveTrue();

  Page<Objective> findByStaffAndStartDateAndEndDateAndActiveTrue(Staff staff, Date startDate, Date endDate, Pageable pageable);
  int countByStaffAndStartDateAndEndDateAndActiveTrue(Staff staff, Date startDate, Date endDate);


  Page<Objective> findByStartDateAndEndDateAndActiveTrue(Date startDate, Date endDate, Pageable paging);

  int countByStartDateAndEndDateAndActiveTrue(Date startDate, Date endDate);
}
