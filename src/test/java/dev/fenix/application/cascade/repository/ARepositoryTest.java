package dev.fenix.application.cascade.repository;

import dev.fenix.application.cascade.model.A;
import dev.fenix.application.cascade.model.B;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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
    @Rollback(false)
    public void testSaveA(){
        A a = new A(UUID.randomUUID().toString() , "1aAssqsqAsA23456789");
        B b1 = new B();
        b1.setName("1aAssqsqAsA23456789");
        B b2 = new B();
        b2.setName("1aAssqsqAsdA23456789");
        List<B> bs = new ArrayList<>();
        bs.add(b1);
        bs.add(b2);
        a.setItems(bs);






        A savedInDb = entityManager.persist(a);
        A getFromDb = aRepository.getOne(savedInDb.getId());
        assertThat(getFromDb).isEqualTo(savedInDb);
    }


}
