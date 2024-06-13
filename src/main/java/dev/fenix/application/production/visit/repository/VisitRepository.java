package dev.fenix.application.production.visit.repository;

import dev.fenix.application.business.model.Staff;
import dev.fenix.application.production.visit.model.Visit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    Iterable<Visit> findByActiveTrue();
    int countByActiveTrue();
    Page<Visit> findByActiveTrue(Pageable paging);

    List<Visit> findByActiveTrueAndStaff(Staff staff);



    Page<Visit> findByScheduledVisitDateBetweenAndActiveTrue(Date scheduledVisitDateStart, Date scheduledVisitDateEnd, Pageable pageable);

    int countByScheduledVisitDateBetweenAndActiveTrue(Date startDate, Date endDate);

    Page<Visit> findByCustomer_ActiveTrueAndScheduledVisitDateBetweenAndStaff(Date scheduledVisitDateStart, Date scheduledVisitDateEnd, Staff staff, Pageable pageable);


    int countByCustomer_ActiveTrueAndScheduledVisitDateBetweenAndStaff(Date startDate, Date endDate, Staff staff);
}