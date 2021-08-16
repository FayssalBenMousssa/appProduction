package dev.fenix.application.business.service;

import dev.fenix.application.Application;
import dev.fenix.application.business.model.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service("JobService")
public class JobService {

  private static final Logger log = LoggerFactory.getLogger(Application.class);

  @PersistenceContext(type = PersistenceContextType.EXTENDED)
  private EntityManager entityManager;

  public List<Job> getJobs() {

    List<Job> jobs = new ArrayList();
    List<Object[]> results =
        this.entityManager.createNativeQuery("SELECT j.id, j.name FROM bz__job j").getResultList();
    results.stream()
        .forEach(
            (record) -> {
              Long id = ((BigInteger) record[0]).longValue();
              String name = (String) record[1];
              jobs.add(new Job(id, name));
            });

    return jobs;
  }
}
