package dev.fenix.application.cascade.repository;

import dev.fenix.application.cascade.model.A;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.r2dbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
@ActiveProfiles("test")
@Transactional
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)


public class ARepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired

    private ARepository aRepository;

    @Test
    public void testSaveA(){


        A a = new A("Hello");
        A savedInDb = entityManager.persist(a);
        A getFromDb = aRepository.getOne(savedInDb.getId());

       System.out.printf("-----------------------> " + getFromDb.getName());
       System.out.printf("-----------------------> " + savedInDb.getName());

        assertThat(getFromDb).isEqualTo(savedInDb);

    }
}
