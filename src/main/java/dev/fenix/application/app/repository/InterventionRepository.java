package dev.fenix.application.app.repository;

import dev.fenix.application.app.model.Intervention;
import dev.fenix.application.security.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InterventionRepository extends PagingAndSortingRepository<Intervention, Long> {

  Intervention getOne(Long id);

  Page<Intervention> findAll(Pageable paging);

  Page<Intervention> findByUser(User user, Pageable pageable);

}
