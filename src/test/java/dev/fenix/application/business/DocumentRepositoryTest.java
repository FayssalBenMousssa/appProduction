package dev.fenix.application.business;


import dev.fenix.application.business.repository.StaffRepository;
import dev.fenix.application.production.treatment.model.Document;
import dev.fenix.application.production.treatment.repository.DocumentRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class DocumentRepositoryTest {
    @Autowired
    TestEntityManager entityManager;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    private static final Logger log = LoggerFactory.getLogger(DocumentRepositoryTest.class);


    @Test
    public void injectedComponentsAreNotNull(){
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(documentRepository).isNotNull();
    }

    @Test
    public void testCountByActiveTrue(){
        int count =  documentRepository.countByActiveTrue( );
        assertThat(count).isGreaterThan(0);
        log.info(String.valueOf(count));
        Assert.assertTrue("count :" + count ,count > 0) ;

    }

    @Test
    public void testFindALLByActiveTrue(){

        Pageable secondPageWithFiveElements = PageRequest.of(1, 1);
        Page<Document> page =  documentRepository.findAll(secondPageWithFiveElements);
        assertThat(page).isNotNull();

        Assert.assertTrue("getSize :" + page.getSize() ,page.getSize() > 0) ;

    }



  /*  @Test
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


    @Test
    @Rollback(false)
    public void testSaveFactoryAndProduct(){


        Pro product_a = new Pro();
        product_a.setProductName("Product a");
        Pro product_b = new Pro();
        product_b.setProductName("Product b");

        List<Pro> products = new ArrayList<>();
        products.add(product_a);
        products.add(product_b);

        Fac factory = new Fac();
        factory.setFactoryName("F1");
        factory.setProducts(products);

        Fac factory_saved = factoryRepository.save(factory);

        assertThat(factory_saved).isNotNull();


       *//* A savedInDb = entityManager.persist(a);
        A getFromDb = aRepository.getOne(savedInDb.getId());
        assertThat(factory_saved).isEqualTo(savedInDb); *//*
    }*/

}
