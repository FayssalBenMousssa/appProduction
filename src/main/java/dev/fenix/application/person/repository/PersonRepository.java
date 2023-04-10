package dev.fenix.application.person.repository;

import dev.fenix.application.person.model.Person;
import dev.fenix.application.security.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

 // @Query(" from Person as person join person.user  where person.id = ?1")
  Person getPersonById(Long id);

  Person save(Person person);

  void delete(Person person);

  @Query("SELECT p FROM Person p ")
  Optional<Person> findActivePersons(Boolean active);

 @Query("SELECT person FROM Person person ")
 List<Person> findPersons(Boolean active);

  User getUserAccountById(Integer id);

  Page<Person> findAllByOrderByIdDesc(Pageable pageable);
}
