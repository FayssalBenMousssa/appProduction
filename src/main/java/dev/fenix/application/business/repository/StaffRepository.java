package dev.fenix.application.business.repository;

import dev.fenix.application.business.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StaffRepository extends PagingAndSortingRepository<Staff, Long> {
  Staff getStaffById(Long id);

  Staff save(Staff staff);

  void delete(Staff staff);

  Iterable<Staff> findByActiveTrue();

  Staff getOne(Long id);

  Staff findTopByOrderByIdDesc();

  int countByActiveTrue();
  // Page<Supplier>
  Page<Staff> findAllByPersonFirstNameContainsOrPersonLastNameContainsAndActiveTrue(String firstName ,String lastName, Pageable paging);

  Page<Staff> findByActiveTrue(Pageable paging);

  int countByPersonFirstNameContainsOrPersonLastNameContainsAndActiveTrue(String firstName ,String lastName );
}
