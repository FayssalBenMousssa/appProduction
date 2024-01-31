package dev.fenix.application.person.repository;

import dev.fenix.application.person.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

@EnableJpaRepositories
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

 // @Query(" from Person as person join person.user  where person.id = ?1")
  Person getPersonById(Long id);



 @Query("SELECT person FROM Person person ")
 List<Person> findPersons(Boolean active);

  Page<Person> findAllByOrderByIdDesc(Pageable pageable);

 Person findByUserAccount_UserNameAndUserAccount_ActiveTrue(String userName);

 Person findByGlobalId(int globalId);



}
