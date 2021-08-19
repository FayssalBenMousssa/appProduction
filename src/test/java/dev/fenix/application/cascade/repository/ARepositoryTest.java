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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)


public class ARepositoryTest {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    private ARepository aRepository;
    @Autowired private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;

    @Test
    public void injectedComponentsAreNotNull(){
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(aRepository).isNotNull();
    }

    @Test
    @Rollback(value = false)
    public void testSaveA(){
        A a = new A(UUID.randomUUID().toString());

        A savedInDb = entityManager.persist(a);

        A getFromDb = aRepository.getOne(savedInDb.getId());
        assertThat(getFromDb).isEqualTo(savedInDb);
    }


}
