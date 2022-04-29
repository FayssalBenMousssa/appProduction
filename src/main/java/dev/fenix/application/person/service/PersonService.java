package dev.fenix.application.person.service;

import dev.fenix.application.person.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service("PersonService")
public class PersonService {

  private static final Logger log = LoggerFactory.getLogger(PersonService.class);

  @PersistenceContext(type = PersistenceContextType.EXTENDED)
  private EntityManager entityManager;

  public List<Person> getPersons() {
    // log.info(" -> getPersons");
    List<Person> people = new ArrayList();
    List<Object[]> results =
        this.entityManager
            .createNativeQuery("SELECT p.id, p.first_name, p.last_name FROM pe__person p")
            .getResultList();
    results.stream()
        .forEach(
            (record) -> {
              Long id = ((BigInteger) record[0]).longValue();
              String firstName = (String) record[1];
              String lastName = (String) record[2];
              people.add(new Person(id, firstName, lastName));
            });
    for (int i = 0; i < people.size(); i++) {
      log.info(people.get(i).getFullName());
    }

    return people;
  }
}
