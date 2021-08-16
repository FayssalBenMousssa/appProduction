package dev.fenix.application.business.repository;

import dev.fenix.application.business.model.Staff;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StaffRepository extends PagingAndSortingRepository<Staff, Long> {
  Staff getStaffById(Long id);

  Staff save(Staff staff);

  void delete(Staff staff);
}
