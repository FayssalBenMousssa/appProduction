package dev.fenix.application.business.repository;

import dev.fenix.application.business.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.PagingAndSortingRepository;

@EnableJpaRepositories
public interface TeamRepository extends PagingAndSortingRepository<Team, Long> {
  Team getTeamById(Team id);

  Team save(Team team);

  void delete(Team team);

  @Query("select distinct team from Team team left join team.people people join team.leader leader")
  Page<Team> findTeamPerson(Pageable pageable);
}
